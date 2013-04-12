
import DataAcces.SingletonConnection;
import View.SearchPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import View.ListingPanel;
import View.AddPanel;
import java.sql.Connection;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class MainFrame extends javax.swing.JFrame {

   
    public MainFrame() {
        initComponents();
        
        
        MenuBarListener menuListener = new MenuBarListener();        
        schPersMenu.addActionListener(menuListener);
        exitMenu.addActionListener(menuListener);
        listMenu.addActionListener(menuListener);
        newRegistration.addActionListener(menuListener);
        
        
    }
    
    public void ChangePanel (JPanel newPanel)
    {
        newPanel.setBounds(this.jPanel1.getBounds());
        this.getContentPane().removeAll();
        this.getContentPane().add(newPanel);
        this.validate();
        this.repaint();
    }
    
    private class MenuBarListener implements ActionListener
    {
       

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==newRegistration)
            {
                AddPanel addPan = new AddPanel();
                MainFrame.this.ChangePanel(addPan);
            }
            if(ae.getSource()==schPersMenu)
            {
                SearchPanel searchPersonne = new SearchPanel();
                MainFrame.this.ChangePanel(searchPersonne);
                
            }
            
            if(ae.getSource()==exitMenu)
            {
                System.exit(0);
            } 
            
            if(ae.getSource()==listMenu)
            {
                ListingPanel listPanel = new ListingPanel();
                MainFrame.this.ChangePanel(listPanel);
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

        jPanel1 = new javax.swing.JPanel();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        newRegistration = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        ActionMenu = new javax.swing.JMenu();
        SearchMenu = new javax.swing.JMenu();
        schPersMenu = new javax.swing.JMenuItem();
        listMenu = new javax.swing.JMenuItem();
        AboutMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );

        FileMenu.setText("Fichier");

        newRegistration.setText("Ajouter nouvelle demande");
        FileMenu.add(newRegistration);

        exitMenu.setText("Quitter");
        FileMenu.add(exitMenu);

        MenuBar.add(FileMenu);

        ActionMenu.setText("Action");

        SearchMenu.setText("Recherches");

        schPersMenu.setText("Personne");
        SearchMenu.add(schPersMenu);

        ActionMenu.add(SearchMenu);

        listMenu.setText("Listing");
        ActionMenu.add(listMenu);

        MenuBar.add(ActionMenu);

        AboutMenu.setText("About");
        MenuBar.add(AboutMenu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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
    private javax.swing.JMenu ActionMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu SearchMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem listMenu;
    private javax.swing.JMenuItem newRegistration;
    private javax.swing.JMenuItem schPersMenu;
    // End of variables declaration//GEN-END:variables
}
