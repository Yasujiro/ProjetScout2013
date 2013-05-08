/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import Exception.AddDataException;
import Exception.SearchDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.LegalResp;
import model.Localite;
import model.Personne;


public class AddLegalResp extends javax.swing.JPanel {

    private ApplicationController app = new ApplicationController();
    private JFrame parents=null;
    private ActionListener buttListener = new ButtonListener();
    private Personne p;
    private JComboBox updatedBox;
    
    
    
    public AddLegalResp(JComboBox uBox) {
        initComponents();
        
        fieldPostalCode.addFocusListener(new Focus());
        buttVali.addActionListener(buttListener);
        buttCancel.addActionListener(buttListener);
        updatedBox = uBox;
        
    }
    
    public void setPopUp(JFrame p)
    {
        parents = p;
    }
    public void resetValues()
    {
        fieldPostalCode.setText(null);
        comboLoc.removeAllItems();
        comboLoc.addItem("Sélectionner une localité");
        fieldName.setText(null);
        fieldFiName.setText(null);
        fieldTel.setText(null);
        fieldMail.setText(null);
        fieldStreet.setText(null);
        fieldNum.setText(null);
        fieldBox.setText(null);
    }
    private class Focus implements FocusListener
    {

        

        @Override
        public void focusLost(FocusEvent fe) {
            
            if(fe.getSource()==fieldPostalCode){
                              
                ArrayList<Localite> listLoca=null;
                Integer postalCode = null;
               try{
                    if(!fieldPostalCode.equals(""))
                        postalCode = Integer.parseInt(fieldPostalCode.getText());
                    
                    comboLoc.removeAllItems();
                    comboLoc.addItem("Sélectionner une localité");
                    listLoca = app.getLocalite(postalCode);
                    for(Localite var: listLoca)
                    {
                        comboLoc.addItem(var);
                    }
            }
               catch(SearchDataException e){
                    JOptionPane.showMessageDialog(null, e.toString(),"Erreur",JOptionPane.PLAIN_MESSAGE);
                   }
               catch(NumberFormatException e){
                   JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","Erreur",JOptionPane.ERROR_MESSAGE);
                   }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br><br>"+e.toString()+"</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                   }
           }
            
        }

        
        public void focusGained(FocusEvent fe) {
            
        }
        
    }
    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==buttCancel)
            {
                if(parents!=null){
                    
                    resetValues();
                     parents.dispose();
                }
                
                                   
                
            }
            if(ae.getSource()==buttVali)
            {
                try{
                    p = app.addPersonne("Responsable légal",fieldName.getText(),fieldFiName.getText(),null,null,
                            fieldStreet.getText(),fieldNum.getText(),fieldBox.getText(),
                            (Localite)comboLoc.getSelectedItem(),fieldTel.getText(),fieldMail.getText(),null);

                    if(parents!=null)
                    {
                        resetValues();
                        parents.dispose();
                    }

                    if(updatedBox!=null)
                    {
                        updatedBox.addItem(p);
                        updatedBox.setSelectedItem(p);
                    }
                }
                catch(AddDataException e){
                    JOptionPane.showMessageDialog(null,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Erreur inconnue rencontrée"+e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
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

        labName = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        labFiName = new javax.swing.JLabel();
        fieldFiName = new javax.swing.JTextField();
        labAddr = new javax.swing.JLabel();
        labStreet = new javax.swing.JLabel();
        fieldStreet = new javax.swing.JTextField();
        labLoca = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        labPostalCode = new javax.swing.JLabel();
        fieldPostalCode = new javax.swing.JTextField();
        labNum = new javax.swing.JLabel();
        labBox = new javax.swing.JLabel();
        labTel = new javax.swing.JLabel();
        fieldTel = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        fieldMail = new javax.swing.JTextField();
        buttVali = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();
        fieldBox = new javax.swing.JTextField();
        fieldNum = new javax.swing.JTextField();

        labName.setText("Nom");

        labFiName.setText("Prénom");

        labAddr.setText("Adresse");

        labStreet.setText("Rue");

        labLoca.setText("Localite");

        comboLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une localité" }));

        labPostalCode.setText("Code postal");

        labNum.setText("Numero");

        labBox.setText("Boite");

        labTel.setText("Tel");

        labMail.setText("E-mail");

        buttVali.setText("Valider");

        buttCancel.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAddr)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(labStreet)
                        .addGap(18, 18, 18)
                        .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labName)
                            .addComponent(labTel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldName)
                            .addComponent(fieldTel, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labFiName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labMail)
                                .addGap(18, 18, 18)
                                .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labPostalCode)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labNum)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttVali)
                            .addComponent(fieldPostalCode, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(fieldNum))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttCancel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labLoca)
                                    .addComponent(labBox))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldBox)
                                    .addComponent(comboLoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labName)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labFiName)
                    .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTel)
                    .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labMail)
                    .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(labAddr)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labStreet)
                    .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNum)
                    .addComponent(labBox)
                    .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labLoca)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPostalCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttVali)
                    .addComponent(buttCancel))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttCancel;
    private javax.swing.JButton buttVali;
    private javax.swing.JComboBox comboLoc;
    private javax.swing.JTextField fieldBox;
    private javax.swing.JTextField fieldFiName;
    private javax.swing.JTextField fieldMail;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldNum;
    private javax.swing.JTextField fieldPostalCode;
    private javax.swing.JTextField fieldStreet;
    private javax.swing.JTextField fieldTel;
    private javax.swing.JLabel labAddr;
    private javax.swing.JLabel labBox;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLoca;
    private javax.swing.JLabel labMail;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labNum;
    private javax.swing.JLabel labPostalCode;
    private javax.swing.JLabel labStreet;
    private javax.swing.JLabel labTel;
    // End of variables declaration//GEN-END:variables
}
