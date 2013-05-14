
import Controller.ApplicationController;
import Exception.*;
import View.SearchPanel;
import java.awt.event.*;
import javax.swing.JPanel;
import View.ListingPanel;
import View.AddPanel;
import View.SearchRegPanel;
import View.SearchUnit;
import View.ThreadConnection;
import java.awt.*;
import java.util.logging.Level;
import javax.swing.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class MainFrame extends javax.swing.JFrame {

   
    
    private JTextField fieldUser;
    private JPasswordField fieldPassword;
    private JPanel panLabelsLog,panFieldsLog,panelLog;
    private JLabel labUser, labPassword, labConnected;
    private ApplicationController app;
    private ThreadConnection thread;
    
    public MainFrame() {
        initComponents();
        
        
        
        
        Dimension d = new Dimension(900,700);
        this.setMinimumSize(d);
        this.setSize(d);
        app = new ApplicationController();
        
        labConnected = new JLabel();
        thread = new ThreadConnection(labConnected);
        thread.start();
        panelHome.add(labConnected);
        labConnected.setLocation(0,620);
        labConnected.setSize(100,10);
        MenuBarListener menuListener = new MenuBarListener();
        menuSchPers.addActionListener(menuListener);
        menuSchUnit.addActionListener(menuListener);
        menuSchReg.addActionListener(menuListener);
        menuExit.addActionListener(menuListener);
        menuList.addActionListener(menuListener);
        menuAddReg.addActionListener(menuListener);
        menuConnect.addActionListener(menuListener);
        menuDisc.addActionListener(menuListener);
        
        
    }

    
    public void ChangePanel (JPanel newPanel)
    {
        newPanel.setBounds(this.getBounds());
        newPanel.add(labConnected);
        this.getContentPane().removeAll();        
        this.getContentPane().add(newPanel);
        this.validate();
        this.repaint();
    }
    public void Loggin(){
        panelLog = new JPanel();
        panLabelsLog = new JPanel();
        panFieldsLog = new JPanel();
        fieldUser = new JTextField();
        labUser = new JLabel("Identifiant:",SwingConstants.RIGHT);
        fieldPassword = new JPasswordField();
        labPassword = new JLabel("Password:",SwingConstants.RIGHT);
        
        panelLog.setLayout(new BorderLayout(5,5));
        panLabelsLog.setLayout(new GridLayout(0,1,2,2));
        panFieldsLog.setLayout(new GridLayout(0,1,2,2));
        panLabelsLog.add(labUser);
        panFieldsLog.add(fieldUser);
        panLabelsLog.add(labPassword);
        panFieldsLog.add(fieldPassword);
        panelLog.add(panLabelsLog,BorderLayout.WEST);
        panelLog.add(panFieldsLog,BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this,panelLog, "Identification",JOptionPane.PLAIN_MESSAGE);
        
        String password="";
                     int i;
                     for(i=0;i<fieldPassword.getPassword().length;i++){
                            password+=fieldPassword.getPassword()[i];
                        }
        try{
        app.Loggin(fieldUser.getText(), password);
        }
        catch(ConnectionException e){
            app.WriteLog(e.getMessage(),Level.FINER,e);
            JOptionPane.showMessageDialog(this,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Disconnect(){
        try{
            app.Disconnect();
            this.ChangePanel(panelHome);
        }
        catch(DisconnectException e){
            app.WriteLog(e.getMessage(),Level.FINER,e);
            
            JOptionPane.showMessageDialog(this,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setLabConnect(boolean state){
        if(state){
            labConnected.setForeground(Color.RED);
            labConnected.setText("Déconnecté");
        }
        else{
            labConnected.setForeground(Color.GREEN);
            labConnected.setText("Connecté");
        }
    }
    
    private class MenuBarListener implements ActionListener
    {
       

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==menuConnect){
                MainFrame.this.Loggin();
            }
            if(ae.getSource()==menuDisc){
                MainFrame.this.Disconnect();
            }
            if(ae.getSource()==menuAddReg)
            {
                AddPanel addPan;
                if(app.getConnectionState())
                    Loggin();
                // Utilisation d'un If avec condition inverse plutôt que d'un else afin que l'affichage des panel se fasse si le loggin s'est
                // correctement déroulé
                if(!app.getConnectionState()){ 
                    addPan = new AddPanel();
                    MainFrame.this.ChangePanel(addPan);
                }
            }
            if(ae.getSource()==menuSchPers)
            {
                SearchPanel searchPersonne;
                if(app.getConnectionState())
                    Loggin();
                // Utilisation d'un If avec condition inverse plutôt que d'un else afin que l'affichage des panel se fasse si le loggin s'est
                // correctement déroulé
                if(!app.getConnectionState()){
                    searchPersonne = new SearchPanel();
                    MainFrame.this.ChangePanel(searchPersonne);
                }
                
            }
            
            if(ae.getSource()==menuSchUnit)
            {
                SearchUnit searchUnitPan;
                if(app.getConnectionState())
                    Loggin();
                // Utilisation d'un If avec condition inverse plutôt que d'un else afin que l'affichage des panel se fasse si le loggin s'est
                // correctement déroulé
                if(!app.getConnectionState()){
                    searchUnitPan = new SearchUnit();
                    MainFrame.this.ChangePanel(searchUnitPan);
                }
            }
            
            if(ae.getSource()==menuSchReg)
            {
                SearchRegPanel searchRegPan;
                if(app.getConnectionState())
                    Loggin();
                // Utilisation d'un If avec condition inverse plutôt que d'un else afin que l'affichage des panel se fasse si le loggin s'est
                // correctement déroulé
                if(!app.getConnectionState()){
                    searchRegPan = new SearchRegPanel();
                    MainFrame.this.ChangePanel(searchRegPan);
                }
            }
            
            if(ae.getSource()==menuExit)
            {
                System.exit(0);
            } 
            
            if(ae.getSource()==menuList)
            {
                ListingPanel listPanel;
                if(app.getConnectionState())
                    Loggin();
                // Utilisation d'un If avec condition inverse plutôt que d'un else afin que l'affichage des panel se fasse si le loggin s'est
                // correctement déroulé
                if(!app.getConnectionState()){
                    listPanel = new ListingPanel();
                    MainFrame.this.ChangePanel(listPanel);
                }
            }
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHome = new javax.swing.JPanel();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        menuConnect = new javax.swing.JMenuItem();
        menuDisc = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        menuAction = new javax.swing.JMenu();
        menuAddReg = new javax.swing.JMenuItem();
        menuSearch = new javax.swing.JMenu();
        menuSchReg = new javax.swing.JMenuItem();
        menuSchPers = new javax.swing.JMenuItem();
        menuSchUnit = new javax.swing.JMenuItem();
        menuList = new javax.swing.JMenuItem();
        AboutMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 953, Short.MAX_VALUE)
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        getContentPane().add(panelHome, java.awt.BorderLayout.CENTER);

        FileMenu.setText("Fichier");

        menuConnect.setText("Connexion");
        FileMenu.add(menuConnect);

        menuDisc.setText("Déconnexion");
        FileMenu.add(menuDisc);

        menuExit.setText("Quitter");
        FileMenu.add(menuExit);

        MenuBar.add(FileMenu);

        menuAction.setText("Action");

        menuAddReg.setText("Ajouter nouvelle demande");
        menuAction.add(menuAddReg);

        menuSearch.setText("Recherches");

        menuSchReg.setText("Demande d'inscription");
        menuSearch.add(menuSchReg);

        menuSchPers.setText("Personne");
        menuSearch.add(menuSchPers);

        menuSchUnit.setText("Unité");
        menuSearch.add(menuSchUnit);

        menuAction.add(menuSearch);

        menuList.setText("Listing");
        menuAction.add(menuList);

        MenuBar.add(menuAction);

        AboutMenu.setText("About");
        MenuBar.add(AboutMenu);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
                
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AboutMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu menuAction;
    private javax.swing.JMenuItem menuAddReg;
    private javax.swing.JMenuItem menuConnect;
    private javax.swing.JMenuItem menuDisc;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuList;
    private javax.swing.JMenuItem menuSchPers;
    private javax.swing.JMenuItem menuSchReg;
    private javax.swing.JMenuItem menuSchUnit;
    private javax.swing.JMenu menuSearch;
    private javax.swing.JPanel panelHome;
    // End of variables declaration//GEN-END:variables
}
