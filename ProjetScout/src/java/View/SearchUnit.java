/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import Exception.SearchDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Localite;
import model.SearchUnitModel;
import model.Unit;


public class SearchUnit extends javax.swing.JPanel {

  private ApplicationController app = new ApplicationController();
  private ButtonListener buttListener = new ButtonListener();
  

    public SearchUnit() {
        initComponents();
        
        fieldPostalCode.addFocusListener(new Focus());
        
        
        buttValidate.addActionListener(buttListener);
        
    }
    
    private class ButtonListener implements ActionListener
    {
        Integer postalCode;
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==buttValidate)
            {
                ArrayList<Unit> listUnit=null;
                if(!fieldPostalCode.getText().equals(""))
                {
                    try{
                        postalCode = Integer.parseInt(fieldPostalCode.getText());
                    }
                    catch(NumberFormatException e)
                    {
                        app.WriteLog(e.toString(),Level.FINER,e);
                        JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                    postalCode = null;
                
                try{
                listUnit = app.getUnits(fieldNameUnit.getText(), postalCode, (String)comboLoc.getSelectedItem());
                SearchUnitModel modelTable = new SearchUnitModel(listUnit);
                tableResult.setModel(modelTable);
                
                
                
                }
                catch(SearchDataException e)
                {
                   app.WriteLog(e.toString(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e){
                    app.WriteLog(e.toString(),Level.WARNING,e);
                    JOptionPane.showMessageDialog(null,"<html>Une erreur inattendue est survenue"+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        
    }
 
    private class Focus implements FocusListener
    {

        

        @Override
        public void focusLost(FocusEvent fe) {
            
            if(fe.getSource()==fieldPostalCode)
            {
                              
                ArrayList<Localite> listLoca=null;
                Integer postalCode= null;
                
                
                if(!fieldPostalCode.getText().equals(""))
                {
                    try{
                        postalCode = Integer.parseInt(fieldPostalCode.getText());
                    }
                    catch(NumberFormatException e)
                    {
                        app.WriteLog(e.toString(),Level.FINER,e);
                        JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }
               
                //
                comboLoc.removeAllItems();
                comboLoc.addItem("Sélectionner une localité");
                
                try{
                    listLoca = app.getLocalite(postalCode);
                }
                catch(SearchDataException e)
                {
                    app.WriteLog(e.toString(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null,"<html>"+ e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.PLAIN_MESSAGE);
                }
                catch(Exception e){
                    app.WriteLog(e.toString(),Level.WARNING,e);
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue"+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                
                for(Localite var: listLoca)
                {

                    comboLoc.addItem(var.getLib());
                }
                    
    
            }
        }

        
        public void focusGained(FocusEvent fe) {
            
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

        jLabel1 = new javax.swing.JLabel();
        labNameUnit = new javax.swing.JLabel();
        fieldNameUnit = new javax.swing.JTextField();
        labPostalCode = new javax.swing.JLabel();
        fieldPostalCode = new javax.swing.JTextField();
        labLoc = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        buttValidate = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();
        labResult = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResult = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(700, 500));

        labNameUnit.setText("Nom de l'unité");

        labPostalCode.setText("Code Postal");

        labLoc.setText("Localité");

        comboLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une localité" }));

        buttValidate.setText("Valider");

        buttCancel.setText("Annuler");

        labResult.setText("Résultat");

        jScrollPane1.setViewportView(tableResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(66, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labResult)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labNameUnit)
                                    .addComponent(labPostalCode)
                                    .addComponent(labLoc))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldNameUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttValidate)
                                .addGap(109, 109, 109)
                                .addComponent(buttCancel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNameUnit)
                    .addComponent(fieldNameUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPostalCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labLoc)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttValidate)
                    .addComponent(buttCancel))
                .addGap(28, 28, 28)
                .addComponent(labResult)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttCancel;
    private javax.swing.JButton buttValidate;
    private javax.swing.JComboBox comboLoc;
    private javax.swing.JTextField fieldNameUnit;
    private javax.swing.JTextField fieldPostalCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labLoc;
    private javax.swing.JLabel labNameUnit;
    private javax.swing.JLabel labPostalCode;
    private javax.swing.JLabel labResult;
    private javax.swing.JTable tableResult;
    // End of variables declaration//GEN-END:variables
}
