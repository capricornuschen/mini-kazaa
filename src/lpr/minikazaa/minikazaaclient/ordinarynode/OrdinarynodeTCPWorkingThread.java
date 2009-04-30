
package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.minikazaaclient.Download;
import lpr.minikazaa.minikazaaclient.DownloadRequest;
import lpr.minikazaa.minikazaaclient.DownloadResponse;
import lpr.minikazaa.minikazaaclient.Query;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file OrdinarynodeTCPWorkingThread.java
 */
public class OrdinarynodeTCPWorkingThread implements Runnable {

    Socket in_sock;
    OrdinarynodeQuestionsList my_found_list;
    OrdinarynodeDownloadMonitor  my_dl_monitor;
    OrdinarynodeFiles my_files;
    
    public OrdinarynodeTCPWorkingThread(
            Socket incoming,
            OrdinarynodeQuestionsList list,
            OrdinarynodeDownloadMonitor dl_monitor,
            OrdinarynodeFiles files) {
        
        this.my_found_list = list;
        this.in_sock = incoming;
        this.my_dl_monitor = dl_monitor;
        this.my_files = files;
    }

    public void run() {
        ObjectInputStream input_object = null;
        Query peer_query = null;
        DownloadRequest peer_request = null;
        DownloadResponse peer_response = null;
        Object incoming_obj;

        try {
            input_object = new ObjectInputStream(this.in_sock.getInputStream());
            incoming_obj = input_object.readObject();

            if (incoming_obj instanceof Query) {
                
                peer_query = (Query) incoming_obj;

                System.out.println("DEBUG: risposta alla query: "+peer_query.getBodyQ());
                //Controlla se la query non è corrotta.
                if ((peer_query.getBodyA() != null) &&
                        (peer_query.getBodyF() == null) ) {

                    System.out.println("DEBUG: la query "+peer_query.getBodyQ()+" Ã¨ corretta.");
                    System.out.println("DEBUG: risposta alla query: "+peer_query.getBodyA().toString());
                    
                    //Questa query è corretta
                    this.my_found_list.add(peer_query.getBodyA());
                }
            } else if (incoming_obj instanceof DownloadRequest) {
                //Devo spedire il file richiesto
                DownloadRequest request = (DownloadRequest) incoming_obj;

                MKFileDescriptor file_to_send = this.my_files.getFileList(request.getFile());

                Socket send_sock = new Socket(
                        request.getSource().getIaNode(),
                        request.getSource().getDoor());

                ObjectOutputStream output = new ObjectOutputStream(send_sock.getOutputStream());

                DownloadResponse init_response = new DownloadResponse(null, file_to_send.getMd5());

                output.writeObject(init_response);

                File file_pointer = new File(file_to_send.getPath());

                FileInputStream in_file = new FileInputStream(file_pointer);

                byte[] buffer = new byte[4096];

                while (true) {
                    try {
                        int letti = in_file.read(buffer);
                        if (letti > 0) {
                            DownloadResponse filepart = new DownloadResponse(buffer, null);
                            output.writeObject(filepart);
                        } else {
                            break;
                        }
                    } catch (IOException e) {
                        break;
                    }
                }

                DownloadResponse stop_sending = new DownloadResponse(null, file_to_send.getMd5());
                output.writeObject(stop_sending);

                in_file.close();
                output.flush();
                output.close();

                send_sock.close();

                
            } else if (incoming_obj instanceof DownloadResponse){
                DownloadResponse response = (DownloadResponse) incoming_obj;

                System.out.println("Downloading file.");

                //Inizio dell'invio di un file.
                if (response.getPart() == null) {
                    Download file_dl = this.my_dl_monitor.getDownload(response.getFile());
                    System.out.println("DEBUG: path download: "+file_dl.getDownloaderPath() + file_dl.getFile().getFileName());
                    File file = new File(file_dl.getDownloaderPath() + file_dl.getFile().getFileName());
                    FileOutputStream file_downloading = new FileOutputStream(file);
                    while (true) {
                        Object read_part = input_object.readObject();

                        if (read_part instanceof DownloadResponse) {
                            DownloadResponse part = (DownloadResponse) read_part;
                            if (part.getFile() == null) {
                                //Posso inserire la parte sia nel monitor che nel file effettivo.
                                byte[] buffer = part.getPart();

                                if (buffer.length > 0) {
                                    System.out.println("DEBUG: Byte ricevuti "+buffer.length);
                                    file_downloading.write(buffer, 0, buffer.length);
                                    part.setFile(file_dl.getFile().getMd5());
                                    this.my_dl_monitor.addBytes(part);
                                }


                            }
                            else{
                                break;
                            }
                        }
                    }

                    file_downloading.flush();
                    file_downloading.close();
                    System.out.println("Downloading completed.");

                }

            }



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
