/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import Exception.ModDataException;
import Exception.SearchDataException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Localite;
import model.Personne;


public class ModPersPanel extends javax.swing.JPanel {

    /**
     * Creates new form modPersPanel
     */
    private JFrame parents;
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
    
    public void setParents(JFrame p){
        parents = p;
    }
    
    public void setInitValues(Personne p)
    {
        JComponent editor = new JSpinner.DateEditor(spinDate, "dd/MM/yyyy");
        spinDate.setEditor(editor);
        String pCode=""+p.getLoc().getPCode();        
        fieldName.setText(p.getName());
        fieldFiName.setText(p.getFiName()); 
        fieldStreet.setText(p.getStreet());
        fieldNum.setText(p.getHouse());
        fieldBox.setText(p.getBox());
        spinDate.setValue(new Date(p.getBirth().getTimeInMillis()));
        fieldPostalCode.setText(pCode);
        postalCode = p.getLoc().getPCode();
        fieldTel.setText(p.getTel());
        fieldMail.setText(p.getMail());
        fieldTotem.setText(p.getTotem());
        if(p.getType().equals("Chef"))
        {
            labPhone.setForeground(Color.BLACK);
            labMail.setForeground(Color.BLACK);
            fieldTel.setEnabled(true);
            fieldMail.setEnabled(true);
        }
        else{
            int age = checkAge();
            if(age >= 18){
                        fieldTel.setEnabled(true);
                        fieldMail.setEnabled(true);
                        labMail.setForeground(Color.BLACK);
                        labPhone.setForeground(Color.BLACK);
                    }
                    else{
                        
                        fieldTel.setEnabled(false);
                        fieldMail.setEnabled(false);
                        labMail.setForeground(Color.gray);
                        labPhone.setForeground(Color.gray);
                    }
        }
        
        comboLoc.removeAllItems();
        
        try{
            listLoc = app.getLocalite(postalCode);
            for(Localite var: listLoc)
                    {
                        comboLoc.addItem(var);
                        if(var.getLib().equals(p.getLoc().getLib()))
                            comboLoc.setSelectedItem(var);
                    }                    
        }
        catch(SearchDataException e){
            JOptionPane.showMessageDialog(null, e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
          }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br><br>"+e.toString()+"</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    public int checkAge(){
                    Calendar curr = Calendar.getInstance();
                    Calendar birth = Calendar.getInstance();
                    birth.setTime((Date)spinDate.getValue());
                    int age = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

                    if(birth.get(Calendar.MONTH) > curr.get(Calendar.MONTH))
                    {
                        age--;
                    }
                    else if(birth.get(Calendar.MONTH) == curr.get(Calendar.MONTH))
                    {
                        if(birth.get(Calendar.DAY_OF_MONTH) > curr.get(Calendar.DAY_OF_MONTH))
                        {
                            age--;
                        }
                    }
                    return age;
    }
   
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()== buttVali){
                try{
                    mPers.setBirth((Date)spinDate.getValue());
                    mPers.setName(fieldName.getText());
                    mPers.setFiName(fieldFiName.getText());
                    mPers.setStreet(fieldStreet.getText());
                    mPers.setBox(fieldBox.getText());
                    mPers.setHouse(fieldNum.getText());
                    mPers.setLoc((Localite)comboLoc.getSelectedItem());
                    mPers.setMail(fieldMail.getText());
                    mPers.setTel(fieldTel.getText());
                    mPers.setTotem(fieldTotem.getText());
                    app.modPers(mPers);
                    parents.dispose();
                }
                catch(ModDataException e){
                    JOptionPane.showMessageDialog(null,e.toString(),"error",JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br><br>"+e.toString()+"</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            if(ae.getSource()== buttCancel){
                parents.dispose();
            }
        }
        
    }
    private class Focus implements FocusListener{

        @Override
        public void focusGained(FocusEvent fe) { }

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
                catch(SearchDataException e){
                    JOptionPane.showMessageDialog(null,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br><br>"+e.toString()+"</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }
    private class Change implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent ce) {
           if(ce.getSource()==spinDate)
            {
               if(mPers.getType().equals("Animé")){
                   
                    int age = checkAge();

                    if(age >= 18){
                        fieldTel.setEnabled(true);
                        fieldMail.setEnabled(true);
                        labMail.setForeground(Color.BLACK);
                        labPhone.setForeground(Color.BLACK);
                    }
                    else{
                        
                        fieldTel.setEnabled(false);
                        fieldMail.setEnabled(false);
                        labMail.setForeground(Color.gray);
                        labPhone.setForeground(Color.gray);
                    }
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
        labDate = new javax.swing.JLabel();
        spinDate = new javax.swing.JSpinner();
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
        labPhone = new javax.swing.JLabel();
        fieldTel = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        fieldMail = new javax.swing.JTextField();
        labTotem = new javax.swing.JLabel();
        fieldTotem = new javax.swing.JTextField();

        labName.setText("Nom :");

        labFiName.setText("Prénom :");

        labDate.setText("Date de Naiss.");

        spinDate.setModel(new javax.swing.SpinnerDateModel());

        labAdrr.setText("Adresse :");

        labStreet.setText("Rue :");

        labNum.setText("Num");

        labBox.setText("Boite");

        labPCode.setText("Code Postal");

        labLoc.setText("Localité :");

        buttVali.setText("Valider");

        buttCancel.setText("Annuler");

        labPhone.setText("Tel :");

        labMail.setText("Mail");

        labTotem.setText("Totem");

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
                                    .addComponent(labDate))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labFiName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labTotem)
                        .addGap(18, 18, 18)
                        .addComponent(fieldTotem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labPhone)
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
                            .addComponent(labName)
                            .addComponent(labTotem)
                            .addComponent(fieldTotem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labFiName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labDate))
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
                    .addComponent(labPhone)
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
    private javax.swing.JTextField fieldTotem;
    private javax.swing.JLabel labAdrr;
    private javax.swing.JLabel labBox;
    private javax.swing.JLabel labDate;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLoc;
    private javax.swing.JLabel labMail;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labNum;
    private javax.swing.JLabel labPCode;
    private javax.swing.JLabel labPhone;
    private javax.swing.JLabel labStreet;
    private javax.swing.JLabel labTotem;
    private javax.swing.JSpinner spinDate;
    // End of variables declaration//GEN-END:variables
}
