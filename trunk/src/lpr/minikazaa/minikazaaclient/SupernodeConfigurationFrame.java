/*
 * SupernodeConfigurationFrame.java
 *
 * Created on 15 settembre 2008, 10.19
 */
package lpr.minikazaa.minikazaaclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import lpr.minikazaa.util.StringManipulationUtil;

/**
 *
 * @author  giovine
 */
public class SupernodeConfigurationFrame extends javax.swing.JFrame {

    private Integer mycheck;
    /** Creates new form SupernodeConfigurationFrame */
    public SupernodeConfigurationFrame(Integer check) {
        initComponents();
        mycheck = check;
        //Adding events on the frame.
        close_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        System.exit(0);
                    }
                });

        clean_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        user_name_tf.setText("");
                        bootstrap_address_tf.setText("");
                        max_conn_tf.setText("");
                        port_tf.setText("");
                        ttl_tf.setText("");
                    }
                });

        //Small buttons that clean text field
        clean_user_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        user_name_tf.setText("");
                    }
                });

        clean_port_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        port_tf.setText("");
                    }
                });
        clean_bsa_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        bootstrap_address_tf.setText("");
                    }
                });
        clean_max_conn_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        max_conn_tf.setText("");
                    }
                });
        clean_ttl_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        ttl_tf.setText("");
                    }
                });

        ok_bt.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        //Check parameters

                        //User name must be under 20 characters.
                        String user_name_str = user_name_tf.getText();
                        if (user_name_str.length() == 0) {
                            String warning = "You must insert an user name.";
                            SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                            war.setLocationRelativeTo(null);
                            war.setVisible(true);
                            return;
                        }
                        if (user_name_str.length() > 20) {
                            String warning = "User name must be less than 20 chars.";
                            SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                            war.setLocationRelativeTo(null);
                            war.setVisible(true);
                            return;
                        }

                        //Port must be over 1024, default 5520
                        int port;
                        if (port_tf.getText().equals("")) {
                            port = 5520;
                        } else {
                            port = Integer.parseInt(port_tf.getText());
                            if (port <= 1024) {
                                String warning = "Port must be over 1024";
                                SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                                war.setLocationRelativeTo(null);
                                war.setVisible(true);
                                return;
                            }
                        }

                        //Checking bootstrap address
                        String bs_address = bootstrap_address_tf.getText();
                        if (StringManipulationUtil.isInetAddress(bs_address)) {
                        } else {
                            String warning = "Unvalid host, please change bootstrap address.";
                            SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                            war.setLocationRelativeTo(null);
                            war.setVisible(true);
                            return;
                        }

                        //Checking maximum numerber of connection permitted
                        int max_conn;

                        try {
                            max_conn = Integer.parseInt(max_conn_tf.getText());

                            if (max_conn <= 0) {
                                String warning = "Number of connection must be more than 0.";
                                SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                                war.setLocationRelativeTo(null);
                                war.setVisible(true);
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            String warning = "Number of connection must be integer.";
                            SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                            war.setLocationRelativeTo(null);
                            war.setVisible(true);
                            return;
                        }

                        //Checking time to live, min 100.
                        int ttl;

                        if (ttl_tf.getText().equals("")) {
                            ttl = 100;
                        } else {
                            try {
                                ttl = Integer.parseInt(ttl_tf.getText());

                                if (ttl < 100) {
                                    String warning = "Number of connection must be integer.";
                                    SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                                    war.setLocationRelativeTo(null);
                                    war.setVisible(true);
                                    return;
                                }
                            } catch (NumberFormatException ex) {
                                String warning = "Time to live must be integer.";
                                SupernodeConfigurationWarningFrame war = new SupernodeConfigurationWarningFrame(warning);
                                war.setLocationRelativeTo(null);
                                war.setVisible(true);
                                return;
                            }
                        }
                        
                        //End check.
                        
                        //Creating file Cnfiguration.
                        NodeConfig config = new NodeConfig();
                        config.setUserName(user_name_str);
                        config.setPort(port);
                        config.setBootStrapAddress(bs_address);
                        config.setMaxConnection(max_conn);
                        config.setTimeToLeave(ttl);
                        config.setIsSN(true);
                        XMLEncoder config_xml;
                        try {

                             config_xml = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("config.xml")));
                             config_xml.writeObject((Object)config);
                             config_xml.flush();
                             config_xml.close();
                        } catch (FileNotFoundException ex) {                            
                        }
                        //End writing configuration file.
                        mycheck = new Integer(1);
                        dispose();
        
                    }
                });

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user_name_tf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        port_tf = new javax.swing.JTextField();
        ok_bt = new javax.swing.JButton();
        clean_bt = new javax.swing.JButton();
        close_bt = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bootstrap_address_tf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        max_conn_tf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ttl_tf = new javax.swing.JTextField();
        clean_user_bt = new javax.swing.JButton();
        clean_port_bt = new javax.swing.JButton();
        clean_bsa_bt = new javax.swing.JButton();
        clean_max_conn_bt = new javax.swing.JButton();
        clean_ttl_bt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Supernode Configuration");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel1.setText("Supernode Configuration:");

        jLabel2.setText("User name (< 20 char):");

        jLabel3.setText("Port (default 5520):");

        ok_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/ok_icon.png"))); // NOI18N
        ok_bt.setText("Ok");
        ok_bt.setMaximumSize(new java.awt.Dimension(75, 36));
        ok_bt.setMinimumSize(new java.awt.Dimension(75, 36));

        clean_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/clena_icon.png"))); // NOI18N
        clean_bt.setText("Clean");

        close_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/close_icon.png"))); // NOI18N
        close_bt.setText("Close");

        jLabel4.setText("BootStrap Address:");

        jLabel5.setText("Maximum connection:");

        jLabel6.setText("Time to leave (default 100):");

        clean_user_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/mini_clean_icon.png"))); // NOI18N

        clean_port_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/mini_clean_icon.png"))); // NOI18N

        clean_bsa_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/mini_clean_icon.png"))); // NOI18N

        clean_max_conn_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/mini_clean_icon.png"))); // NOI18N

        clean_ttl_bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpr/minikazaa/icons/mini_clean_icon.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bootstrap_address_tf)
                            .addComponent(user_name_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(port_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(max_conn_tf)
                            .addComponent(ttl_tf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clean_user_bt)
                            .addComponent(clean_port_bt)
                            .addComponent(clean_bsa_bt)
                            .addComponent(clean_max_conn_bt)
                            .addComponent(clean_ttl_bt))
                        .addGap(27, 27, 27))))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(ok_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clean_bt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close_bt)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clean_user_bt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clean_port_bt))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(port_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bootstrap_address_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(clean_bsa_bt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(max_conn_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(clean_max_conn_bt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clean_ttl_bt)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ttl_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok_bt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clean_bt)
                    .addComponent(close_bt))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bootstrap_address_tf;
    private javax.swing.JButton clean_bsa_bt;
    private javax.swing.JButton clean_bt;
    private javax.swing.JButton clean_max_conn_bt;
    private javax.swing.JButton clean_port_bt;
    private javax.swing.JButton clean_ttl_bt;
    private javax.swing.JButton clean_user_bt;
    private javax.swing.JButton close_bt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField max_conn_tf;
    private javax.swing.JButton ok_bt;
    private javax.swing.JTextField port_tf;
    private javax.swing.JTextField ttl_tf;
    private javax.swing.JTextField user_name_tf;
    // End of variables declaration//GEN-END:variables

}
