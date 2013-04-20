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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Localite;
import model.Personne;
import model.Unit;


public class AddPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddPanel
     */
    private PopUp popUpFrame;
    private ApplicationController app = new ApplicationController();
    private ArrayList<Unit> listUnit;
    
    
    public AddPanel(){
        initComponents();
        
        ComboState comboListener = new ComboState();
        ButtonListener buttonListener = new ButtonListener();
        
        groupSect.add(sect1Radio);
        groupSect.add(sect2Radio);
        groupSect.add(sect3Radio);
        groupSect.add(sect4Radio);
        
        
        JComponent editor = new JSpinner.DateEditor(spinDate, "dd/MM/yyyy");
        spinDate.setEditor(editor);
        buttAddLegal.setEnabled(false);
        comboLegal.setEnabled(false);
        labLegal.setForeground(Color.GRAY);
       
        
        comboType.addItemListener(comboListener);
        comboLoc.addItemListener(comboListener);
        cancelButton.addActionListener(buttonListener);
        buttAddLegal.addActionListener(buttonListener);
        fieldPostalCode.addFocusListener(new Focus());
        spinDate.addChangeListener(new Change());
        
        
        try{
            listUnit = app.getUnits();
             for(Unit var: listUnit)
            {
                comboUnit.addItem(var.getLib());
            }
        }
        catch(Exception e) // Exception a créer
        {
            
        }     

    }

    
    private class ComboState implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            
           if(ie.getSource().equals(comboType))
           {
                if(ie.getItem().equals("Chef"))
                {
                         phoneField.setEnabled(true);
                         mailField.setEnabled(true);
                         labPhone.setForeground(Color.black);
                         labMail.setForeground(Color.black);
                }
                else
                {
                    phoneField.setEnabled(false);
                    mailField.setEnabled(false);
                    labPhone.setForeground(Color.gray);
                    labMail.setForeground(Color.gray);
                }
               
           }
           
           if(ie.getSource().equals(comboLoc))
           {
               
               
           }           
           
        }
    }
    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()== buttValidate)
            {
                try{
                String section;
                if(sect1Radio.isSelected())
                    section=sect1Radio.getText();
                else if (sect2Radio.isSelected())
                        section=sect2Radio.getText();
                else if (sect3Radio.isSelected())
                    section=sect3Radio.getText();
                else if (sect4Radio.isSelected())
                    section=sect4Radio.getText();
                else
                    throw new Exception(); // Exception a créer
                
                // Générer Personne,etc
                Personne pers = null;
                app.addRegistration((String)comboUnit.getSelectedItem(),section,pers);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erreur - champs mal remplie "+e,"error",JOptionPane.ERROR_MESSAGE);
            }
            }
            
            if(ae.getSource()==buttAddLegal)
            {
                
                if(AddPanel.this.popUpFrame == null)
                {
                    
                     popUpFrame = new PopUp(new AddLegalResp());                
                     popUpFrame.setLocation(200,150);                     
                }
                
                popUpFrame.setVisible(true);
            }
            
            if(ae.getSource().equals(cancelButton))
            {
                comboType.setSelectedIndex(0);
               
                groupSect.clearSelection();
                
                
                comboLoc.removeAllItems();
                comboLoc.addItem("Sélectionner une localité");
                
                fieldPostalCode.setText(null);
                comboUnit.setSelectedIndex(0);
                comboLegal.removeAllItems();
                fieldName.setText(null);
                fieldFiName.setText(null);
                phoneField.setText(null);
                mailField.setText(null);
                totemField.setText(null);
                streetField.setText(null);
                numSpin.setValue(0);
                fieldNumBox.setText(null);
                
                
                
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
                Integer postalCode = null;
                
                if(!fieldPostalCode.equals(""))
                {
                    try{
                         postalCode = Integer.parseInt(fieldPostalCode.getText());
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                //
                
                AddPanel.this.comboLoc.removeAllItems();
                AddPanel.this.comboLoc.addItem("Sélectionner une localité");
                
                try{
                    listLoca = app.getLocalite(postalCode);
                }
                catch(Exception e) // Exception a créer
                {
                    JOptionPane.showMessageDialog(null, "ERREUR FocusLost"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
                }
                
                for(Localite var: listLoca)
                {

                    AddPanel.this.comboLoc.addItem(var.getLib());
                }
                    
    
            }
            
            
        }

        
        public void focusGained(FocusEvent fe) {
            
        }
        
    }
    
    private class Change implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent ce) {
           if(ce.getSource()==spinDate)
            {
               if(comboType.getSelectedItem() == "Animé")
               {
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

                    if(age >= 18)
                    {
                        buttAddLegal.setEnabled(false);
                        comboLegal.setEnabled(false);
                        labLegal.setForeground(Color.GRAY);
                    }
                    else
                    {
                        buttAddLegal.setEnabled(true);
                        comboLegal.setEnabled(true);
                        labLegal.setForeground(Color.BLACK);
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

        groupSect = new javax.swing.ButtonGroup();
        labType = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox();
        sect1Radio = new javax.swing.JRadioButton();
        sect2Radio = new javax.swing.JRadioButton();
        sect3Radio = new javax.swing.JRadioButton();
        sect4Radio = new javax.swing.JRadioButton();
        labName = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        labFiName = new javax.swing.JLabel();
        fieldFiName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spinDate = new javax.swing.JSpinner();
        labUnit = new javax.swing.JLabel();
        comboUnit = new javax.swing.JComboBox();
        labLegal = new javax.swing.JLabel();
        comboLegal = new javax.swing.JComboBox();
        labAddr = new javax.swing.JLabel();
        labStreet = new javax.swing.JLabel();
        streetField = new javax.swing.JTextField();
        labNum = new javax.swing.JLabel();
        numSpin = new javax.swing.JSpinner();
        labLoc = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        labPostalCode = new javax.swing.JLabel();
        fieldPostalCode = new javax.swing.JTextField();
        labPhone = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        mailField = new javax.swing.JTextField();
        labBox = new javax.swing.JLabel();
        labTotem = new javax.swing.JLabel();
        totemField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buttAddLegal = new javax.swing.JButton();
        buttValidate = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        fieldNumBox = new javax.swing.JTextField();

        labType.setText("Type de personne à ajouter");

        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chef", "Animé" }));

        sect1Radio.setText("Balladins");

        sect2Radio.setText("Louvetaux");

        sect3Radio.setText("Eclaireurs");

        sect4Radio.setText("Pionniers");

        labName.setText("Nom");

        labFiName.setText("Prénom");

        jLabel2.setText("Date de Naissance");

        spinDate.setModel(new javax.swing.SpinnerDateModel());

        labUnit.setText("Unité");

        comboUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une unité" }));

        labLegal.setText("Responsable légal");

        labAddr.setText("Adresse :");

        labStreet.setText("Rue :");

        labNum.setText("Num");

        numSpin.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        labLoc.setText("Localité :");

        comboLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une localité" }));

        labPostalCode.setText("Code Postal");

        labPhone.setText("GSM");

        labMail.setText("E-mail");

        labBox.setText("Boite*");

        labTotem.setText("Totem*");

        jLabel3.setText("* : Champs facultatifs");

        buttAddLegal.setText("Nouveau");

        buttValidate.setText("Valider");

        cancelButton.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labTotem)
                                .addGap(18, 18, 18)
                                .addComponent(totemField)
                                .addGap(58, 58, 58))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labName)
                                        .addGap(26, 26, 26)
                                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(labLegal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttAddLegal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labFiName)
                                .addGap(68, 68, 68)
                                .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(183, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sect1Radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sect2Radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sect3Radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sect4Radio))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labType)
                                    .addComponent(labUnit))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labAddr)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labPhone)
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labStreet)
                                        .addGap(18, 18, 18)
                                        .addComponent(streetField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labNum)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(numSpin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fieldNumBox))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57)
                                                .addComponent(labMail))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(buttValidate))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labPostalCode)
                                                .addGap(18, 18, 18)
                                                .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(labLoc)))
                                        .addGap(58, 58, 58)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cancelButton)
                                            .addComponent(mailField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel3))
                        .addContainerGap(230, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labType)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labUnit)
                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sect1Radio)
                    .addComponent(sect2Radio)
                    .addComponent(sect3Radio)
                    .addComponent(sect4Radio))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labName)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labFiName)
                        .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTotem)
                    .addComponent(totemField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labLegal)
                    .addComponent(comboLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttAddLegal))
                .addGap(24, 24, 24)
                .addComponent(labAddr)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labStreet)
                    .addComponent(streetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNum)
                    .addComponent(numSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labBox)
                    .addComponent(fieldNumBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labLoc)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPostalCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPhone)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labMail)
                    .addComponent(mailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttValidate)
                    .addComponent(cancelButton))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttAddLegal;
    private javax.swing.JButton buttValidate;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox comboLegal;
    private javax.swing.JComboBox comboLoc;
    private javax.swing.JComboBox comboType;
    private javax.swing.JComboBox comboUnit;
    private javax.swing.JTextField fieldFiName;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldNumBox;
    private javax.swing.JTextField fieldPostalCode;
    private javax.swing.ButtonGroup groupSect;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labAddr;
    private javax.swing.JLabel labBox;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLegal;
    private javax.swing.JLabel labLoc;
    private javax.swing.JLabel labMail;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labNum;
    private javax.swing.JLabel labPhone;
    private javax.swing.JLabel labPostalCode;
    private javax.swing.JLabel labStreet;
    private javax.swing.JLabel labTotem;
    private javax.swing.JLabel labType;
    private javax.swing.JLabel labUnit;
    private javax.swing.JTextField mailField;
    private javax.swing.JSpinner numSpin;
    private javax.swing.JTextField phoneField;
    private javax.swing.JRadioButton sect1Radio;
    private javax.swing.JRadioButton sect2Radio;
    private javax.swing.JRadioButton sect3Radio;
    private javax.swing.JRadioButton sect4Radio;
    private javax.swing.JSpinner spinDate;
    private javax.swing.JTextField streetField;
    private javax.swing.JTextField totemField;
    // End of variables declaration//GEN-END:variables
   
}
