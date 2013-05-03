/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import model.Localite;
import model.Personne;


public class ModPersPanel extends javax.swing.JPanel {

    /**
     * Creates new form modPersPanel
     */
    private PopUp parents;
    private Personne mPers;
    private ApplicationController app;
    private Integer postalCode;
    private ArrayList<Localite> listLoc;
    private ButtonListener buttListener;
    private Focus focusList;
    
    public ModPersPanel(Personne p) {
        initComponents();
        mPers = p;
        app = new ApplicationController();
        listLoc = new ArrayList<Localite>();
        buttListener = new ButtonListener();
        focusList = new Focus();
        
        buttVali.addActionListener(buttListener);
        buttCancel.addActionListener(buttListener);
        fieldPostalCode.addFocusListener(focusList);
        setInitValues(p);
    }
    
    public void setParents(PopUp p){
        parents = p;
    }
    
    public void setInitValues(Personne p)
    {
        JComponent editor = new JSpinner.DateEditor(spinBirth, "dd/MM/yyyy");
        spinBirth.setEditor(editor);
        String pCode=""+p.getLoc().getPCode();        
        fieldName.setText(p.getName());
        fieldFiName.setText(p.getFiName()); 
        fieldStreet.setText(p.getStreet());
        fieldNum.setText(p.getHouse());
        fieldBox.setText(p.getBox());
        spinBirth.setValue(new Date(p.getBirth().getTimeInMillis()));
        fieldPostalCode.setText(pCode);
        postalCode = p.getLoc().getPCode();
        fieldTel.setText(p.getTel());
        fieldMail.setText(p.getMail());
        
        if(p.getType().equals("Chef"))
        {
            labTel.setForeground(Color.BLACK);
            labMail.setForeground(Color.BLACK);
            fieldTel.setForeground(Color.BLACK);
            fieldMail.setForeground(Color.BLACK);
            fieldTel.setEditable(true);
            fieldMail.setEditable(true);
        }
        else{
            labTel.setForeground(Color.gray);
            labMail.setForeground(Color.gray);
            fieldTel.setForeground(Color.gray);
            fieldMail.setForeground(Color.gray);
            fieldTel.setEditable(false);
            fieldMail.setEditable(false);
        }
        
        comboLoc.removeAllItems();
        
        try{
            listLoc = app.getLocalite(postalCode);
            for(Localite var: listLoc)
                    {
                        comboLoc.addItem(var);
                    }                    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","error",JOptionPane.ERROR_MESSAGE);
          }
        
        
    }
   
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()== buttVali){
                
                mPers.setBirth((Date)spinBirth.getValue());
                mPers.setName(fieldName.getText());
                mPers.setFiName(fieldFiName.getText());
                mPers.setStreet(fieldStreet.getText());
                mPers.setBox(fieldBox.getText());
                mPers.setHouse(fieldNum.getText());
                mPers.setLoc((Localite)comboLoc.getSelectedItem());
                app.modPers(mPers);
                parents.dispose();
            }
            if(ae.getSource()== buttCancel){
                parents.dispose();
            }
        }
        
    }
    private class Focus implements FocusListener{

        @Override
        public void focusGained(FocusEvent fe) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void focusLost(FocusEvent fe) {
            if(fe.getSource()==fieldPostalCode){
                
                try{
                    comboLoc.removeAllItems();
                    listLoc = app.getLocalite(postalCode);
                    
                    for(Localite var: listLoc)
                    {
                        comboLoc.addItem(var);
                    }
                    
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","error",JOptionPane.ERROR_MESSAGE);
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
        labBirth = new javax.swing.JLabel();
        spinBirth = new javax.swing.JSpinner();
        labAdrr = new javax.swing.JLabel();
        labStreet = new javax.swing.JLabel();
        fieldStreet = new javax.swing.JTextField();
        labNum = new javax.swing.JLabel();
        fieldNum = new javax.swing.JTextField();
        labBox = new javax.swing.JLabel();
        fieldBox = new javax.swing.JTextField();
        labPCode = new javax.swing.JLabel();
        fieldPostalCode = new javax.swing.JTextField();
        labLoc = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        buttVali = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();
        labTel = new javax.swing.JLabel();
        fieldTel = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        fieldMail = new javax.swing.JTextField();

        labName.setText("Nom :");

        labFiName.setText("Prénom :");

        labBirth.setText("Date de Naiss.");

        spinBirth.setModel(new javax.swing.SpinnerDateModel());

        labAdrr.setText("Adresse :");

        labStreet.setText("Rue :");

        labNum.setText("Num");

        labBox.setText("Boite");

        labPCode.setText("Code Postal");

        labLoc.setText("Localité :");

        buttVali.setText("Valider");

        buttCancel.setText("Annuler");

        labTel.setText("Tel :");

        labMail.setText("Mail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labPCode)
                                    .addComponent(labStreet)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labAdrr)
                                    .addComponent(labName)
                                    .addComponent(labBirth))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labFiName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labNum)
                                .addGap(18, 18, 18)
                                .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labLoc))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(buttVali)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttCancel)
                                    .addComponent(comboLoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(labBox)
                        .addGap(18, 18, 18)
                        .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labTel)
                .addGap(18, 18, 18)
                .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labMail)
                .addGap(18, 18, 18)
                .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labAdrr)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labFiName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labBirth))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNum)
                            .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labBox)
                            .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labStreet))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labLoc)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTel)
                    .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labMail)
                    .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttCancel)
                    .addComponent(buttVali))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JLabel labAdrr;
    private javax.swing.JLabel labBirth;
    private javax.swing.JLabel labBox;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLoc;
    private javax.swing.JLabel labMail;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labNum;
    private javax.swing.JLabel labPCode;
    private javax.swing.JLabel labStreet;
    private javax.swing.JLabel labTel;
    private javax.swing.JSpinner spinBirth;
    // End of variables declaration//GEN-END:variables
}
