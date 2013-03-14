/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Jérémy
 */
public class AddPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddPanel
     */
    public AddPanel() {
        initComponents();
        groupSect.add(sect1Radio);
        groupSect.add(sect2Radio);
        groupSect.add(sect3Radio);
        groupSect.add(sect4Radio);
        
        
        comboType.addItemListener(new TypeState());
    }

    
    private class TypeState implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent ie) {
           if(ie.getItem().equals("Responsable légal"))
           {
               comboUnit.setEnabled(false);
               labUnit.setForeground(Color.gray);
               sect1Radio.setEnabled(false);
               sect2Radio.setEnabled(false);
               sect3Radio.setEnabled(false);
               sect4Radio.setEnabled(false);         
           }
           else
           {
               comboUnit.setEnabled(true);
               labUnit.setForeground(Color.black);
               sect1Radio.setEnabled(true);
               sect2Radio.setEnabled(true);
               sect3Radio.setEnabled(true);
               sect4Radio.setEnabled(true);
           
               /*if(ie.getItem().equals("Chef"))
               {
                        /*phoneField.setEnabled(true);
                        mailField.setEnabled(true);
                        labPhone.setForeground(Color.black);
                        labMail.setForeground(Color.black);
               }*/
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
        nameField = new javax.swing.JTextField();
        labFiName = new javax.swing.JLabel();
        fiNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dateSpin = new javax.swing.JSpinner();
        labUnit = new javax.swing.JLabel();
        comboUnit = new javax.swing.JComboBox();

        labType.setText("Type de personne à ajouter");

        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chef", "Animé", "Responsable légal" }));

        sect1Radio.setText("jRadioButton1");

        sect2Radio.setText("jRadioButton1");

        sect3Radio.setText("jRadioButton2");

        sect4Radio.setText("jRadioButton3");

        labName.setText("Nom");

        labFiName.setText("Prénom");

        jLabel2.setText("Date de Naissance");

        dateSpin.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1363279597259L), null, null, java.util.Calendar.MILLISECOND));

        labUnit.setText("Unité");

        comboUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(dateSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)
                        .addComponent(labFiName)
                        .addGap(18, 18, 18)
                        .addComponent(fiNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labType)
                            .addComponent(labUnit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(288, Short.MAX_VALUE))
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
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labFiName)
                        .addComponent(fiNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(323, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboType;
    private javax.swing.JComboBox comboUnit;
    private javax.swing.JSpinner dateSpin;
    private javax.swing.JTextField fiNameField;
    private javax.swing.ButtonGroup groupSect;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labType;
    private javax.swing.JLabel labUnit;
    private javax.swing.JTextField nameField;
    private javax.swing.JRadioButton sect1Radio;
    private javax.swing.JRadioButton sect2Radio;
    private javax.swing.JRadioButton sect3Radio;
    private javax.swing.JRadioButton sect4Radio;
    // End of variables declaration//GEN-END:variables
}
