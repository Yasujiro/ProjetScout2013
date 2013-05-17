
import Controller.ApplicationController;
import Exception.*;
import View.AboutPanel;
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
        //Création du thread, lancement de celui-ci.
        thread = new ThreadConnection(labConnected);
        thread.start();
        
        panelHome.add(labConnected);
        labConnected.setLocation(0,620);
        labConnected.setSize(100,10);
        // Ajout de l'écouteur au différents menus.
        MenuBarListener menuListener = new MenuBarListener();
        menuSchPers.addActionListener(menuListener);
        menuSchUnit.addActionListener(menuListener);
        menuSchReg.addActionListener(menuListener);
        menuExit.addActionListener(menuListener);
        menuList.addActionListener(menuListener);
        menuAddReg.addActionListener(menuListener);
        menuConnect.addActionListener(menuListener);
        menuDisc.addActionListener(menuListener);
        menuAPropos.addActionListener(menuListener);
        menuHome.addActionListener(menuListener);
        
    }

    /*
     * La méthode ChangePanel permet de changer le panel courrant de la fenêtre     
     */
    public void ChangePanel (JPanel newPanel)
    {
        newPanel.setBounds(this.getBounds());
        newPanel.add(labConnected);
        this.getContentPane().removeAll();        
        this.getContentPane().add(newPanel);
        this.validate();
        this.repaint();
    }
    /*
     * Méthode permettant de gérer la connexion à la base de donnée.
     */
    public void Loggin(){
        
        /*
         * Création des JPanel qui seviront à la construction du JOptionPane.
         * Une fois c'est pannel créés on leur ajoute leurs composant (label, champs texte,etc)
         */
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
        
        //Affichage du JOptionPane contenant le panel formé plus haut
        JOptionPane.showMessageDialog(this,panelLog, "Identification",JOptionPane.PLAIN_MESSAGE);
        
        // On transforme le tableau de char récupéré via le JPasswordField en une chaîne de caractère.
        String password="";
                     int i;
                     for(i=0;i<fieldPassword.getPassword().length;i++){
                            password+=fieldPassword.getPassword()[i];
                        }
        
        // Appel de la méthode de la couche inférieur.
        try{
        app.Loggin(fieldUser.getText(), password);
        }
        catch(ConnectionException e){
            app.WriteLog(e.getMessage(),Level.FINER,e);
            JOptionPane.showMessageDialog(this,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
     * Cette méthode permet de gérer la déconnexion à la BD.
     */
    public void Disconnect(){
        //Appel de la méthode de la couche inférieure.
        try{
            
            app.Disconnect();
            this.ChangePanel(panelHome);
        }
        catch(DisconnectException e){
            app.WriteLog(e.getMessage(),Level.FINER,e);
            
            JOptionPane.showMessageDialog(this,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /*
     * Classe interne permettant de gérer l'action des différents menus.
     */
    private class MenuBarListener implements ActionListener
    {
       

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==menuHome){
                MainFrame.this.ChangePanel(panelHome);
            }
            if(ae.getSource()==menuConnect){
                MainFrame.this.Loggin();
            }
            if(ae.getSource()==menuDisc){
                MainFrame.this.Disconnect();
            }
            if(ae.getSource()==menuAddReg)
            {
                AddPanel addPan;
                //Si connexion fermée on appel la fonction Loggin.
                if(app.getConnectionState())
                    Loggin();
                /* 
                 * Utilisation d'un If avec condition inverse plutôt que d'un else afin que l'affichage des panel 
                 * se fasse si le loggin s'est correctement déroulé
                */ 
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
                if(!app.getConnectionState()){
                    listPanel = new ListingPanel();
                    MainFrame.this.ChangePanel(listPanel);
                }
            }
            if (ae.getSource()==menuAPropos)
            {
                
                AboutPanel abPanel;
                abPanel = new AboutPanel();
                MainFrame.this.ChangePanel(abPanel);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        menuHome = new javax.swing.JMenuItem();
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
        aboutMenu = new javax.swing.JMenu();
        menuAPropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/header3.jpg"))); // NOI18N

        jLabel2.setText("<html><b>Le logiciel va vous permettre de :</b> <br><br> - <b>Ajouter des demandes</b> via le menu \"Action - Ajouter demande\" <br> -<b> Rechercher</b> des demandes, personnes ou unités via les menus \"Action - Recherche <br> -<b> Lister</b> l'ensemble des demandes via l'option \"Action - Listing\"<br> La modification des demandes s'effectue à la site d'une recherche de demande.<br>Quant à la suppression, elle s'effectue après le listing.</html>");

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHomeLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        getContentPane().add(panelHome, java.awt.BorderLayout.CENTER);

        FileMenu.setText("Fichier");

        menuHome.setText("Accueil");
        FileMenu.add(menuHome);

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

        aboutMenu.setText("?");

        menuAPropos.setText("A propos");
        aboutMenu.add(menuAPropos);

        MenuBar.add(aboutMenu);

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
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem menuAPropos;
    private javax.swing.JMenu menuAction;
    private javax.swing.JMenuItem menuAddReg;
    private javax.swing.JMenuItem menuConnect;
    private javax.swing.JMenuItem menuDisc;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuHome;
    private javax.swing.JMenuItem menuList;
    private javax.swing.JMenuItem menuSchPers;
    private javax.swing.JMenuItem menuSchReg;
    private javax.swing.JMenuItem menuSchUnit;
    private javax.swing.JMenu menuSearch;
    private javax.swing.JPanel panelHome;
    // End of variables declaration//GEN-END:variables
}
