/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import model.Unit;


public class SearchPanel extends javax.swing.JPanel {

    /**
     * Creates new form SearchPanel
     */
    private ArrayList<Unit> listUnit;
    private ApplicationController app = new ApplicationController();
    public SearchPanel() {
        initComponents();
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
       
        ButtonState buttonListener = new ButtonState();
        comboType.addItemListener(new TypeState());
        searchButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
    }
    
    private class TypeState implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            if(ie.getItem().equals("Responsable légal"))
            {
                comboUnit.setEnabled(false);
                labUnit.setForeground(Color.gray);
                sect1.setEnabled(false);
                sect2.setEnabled(false);
                sect3.setEnabled(false);
                sect4.setEnabled(false);
                labPhone.setForeground(Color.black);
                labMail.setForeground(Color.black);
                phoneField.setEnabled(true);
                mailField.setEnabled(true);
                
            }
            else 
            {
                    comboUnit.setEnabled(true);
                    labUnit.setForeground(Color.black);
                    sect1.setEnabled(true);
                    sect2.setEnabled(true);
                    sect3.setEnabled(true);
                    sect4.setEnabled(true);
                    if(ie.getItem().equals("Animé"))
                {

                    phoneField.setEnabled(false);
                    mailField.setEnabled(false);
                    labPhone.setForeground(Color.gray);
                    labMail.setForeground(Color.gray);

                }
                    else
                    {
                        phoneField.setEnabled(true);
                        mailField.setEnabled(true);
                        labPhone.setForeground(Color.black);
                        labMail.setForeground(Color.black);
                        
                    }
            }
            
            
        }
    }
    private class ButtonState implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==searchButton)
            {
               /* app.getPersonne(comboType.getSelectedItem(), comboUnit.getSelectedItem(), sect1.isSelected(),
                        sect2.isSelected(),sect3.isSelected(),sect4.isSelected(), nameField.getText(),
                        fiNameField.getText(),comboLoc.getSelectedItem();phoneField.getText(),mailField.getText());
            
            */}
            if(ae.getSource()==cancelButton)
            {
                comboType.setSelectedIndex(0);
                sect1.setSelected(false);
                sect2.setSelected(false);
                sect3.setSelected(false);
                sect4.setSelected(false);
                comboLoc.setSelectedIndex(0);
                comboUnit.setSelectedIndex(0);
                nameField.setText(null);
                fiNameField.setText(null);
                phoneField.setText(null);
                mailField.setText(null);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        comboUnit = new javax.swing.JComboBox();
        labUnit = new javax.swing.JLabel();
        sect1 = new javax.swing.JRadioButton();
        sect2 = new javax.swing.JRadioButton();
        sect3 = new javax.swing.JRadioButton();
        sect4 = new javax.swing.JRadioButton();
        labName = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        labFiName = new javax.swing.JLabel();
        fiNameField = new javax.swing.JTextField();
        labType = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox();
        labLoca = new javax.swing.JLabel();
        labPhone = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        mailField = new javax.swing.JTextField();
        resultLab = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        searchButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        labPostalCode = new javax.swing.JLabel();
        fieldPostalCode = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setPreferredSize(new java.awt.Dimension(700, 500));

        comboUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une unité" }));

        labUnit.setText("Unité");

        sect1.setText("Balladins");

        sect2.setText("Louvetaux");

        sect3.setText("Eclaireurs");

        sect4.setText("Pionniers");

        labName.setText("Nom");

        labFiName.setText("Prénom");

        labType.setText("Type de personne recherché");

        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chef", "Animé", "Responsable légal" }));

        labLoca.setText("Localité");

        labPhone.setText("GSM");

        phoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });

        labMail.setText("E-mail");

        resultLab.setText("Résultat");

        comboLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane2.setViewportView(jTable2);

        searchButton.setText("Valider");

        cancelButton.setText("Annuler");

        labPostalCode.setText("Code Postal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultLab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cancelButton)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(searchButton)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(labName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(nameField))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(labPhone)
                                            .addGap(25, 25, 25)
                                            .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(51, 51, 51))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(labType, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labUnit, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(labPostalCode)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(fieldPostalCode)
                                            .addGap(2, 2, 2)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(labLoca)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(139, 139, 139))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labMail)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(sect1)
                                    .addGap(18, 18, 18)
                                    .addComponent(sect2)
                                    .addGap(18, 18, 18)
                                    .addComponent(sect3))
                                .addComponent(labFiName))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sect4)
                                .addComponent(fiNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mailField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labType)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnit))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sect4)
                    .addComponent(sect3)
                    .addComponent(sect2)
                    .addComponent(sect1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labName)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labFiName)
                            .addComponent(fiNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labLoca)
                    .addComponent(labPostalCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPhone)
                    .addComponent(labMail)
                    .addComponent(mailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(cancelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resultLab)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox comboLoc;
    private javax.swing.JComboBox comboType;
    private javax.swing.JComboBox comboUnit;
    private javax.swing.JTextField fiNameField;
    private javax.swing.JTextField fieldPostalCode;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLoca;
    private javax.swing.JLabel labMail;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labPhone;
    private javax.swing.JLabel labPostalCode;
    private javax.swing.JLabel labType;
    private javax.swing.JLabel labUnit;
    private javax.swing.JTextField mailField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel resultLab;
    private javax.swing.JButton searchButton;
    private javax.swing.JRadioButton sect1;
    private javax.swing.JRadioButton sect2;
    private javax.swing.JRadioButton sect3;
    private javax.swing.JRadioButton sect4;
    // End of variables declaration//GEN-END:variables
}
