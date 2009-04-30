

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.util.ArrayList;
import java.util.Observable;
import lpr.minikazaa.minikazaaclient.Answer;
import lpr.minikazaa.minikazaaclient.SearchField;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 9-ott-2008
 * @file OrdinarynodeFoundList.java
 */
public class OrdinarynodeFoundList extends Observable {
    private int id;
    private ArrayList<SearchField> found;
    
    public OrdinarynodeFoundList(int n) {
        this.id = n;
        found = new ArrayList();
    }

    public void add(Answer k) {
        ArrayList <OrdinarynodeFiles> list = k.getFilesList();

        for(OrdinarynodeFiles of : list){
            ArrayList <MKFileDescriptor> answer_files = of.getFileList();
            for(MKFileDescriptor files : answer_files){
                SearchField field = new SearchField(files,of.getOwner());

                found.add(field);
            }
        }



        this.setChanged();
        this.notifyObservers();
    }


    
    public ArrayList <SearchField> getFoundList(){
        return this.found;
    }
    
    public int getId(){return this.id;}
    
   
}
