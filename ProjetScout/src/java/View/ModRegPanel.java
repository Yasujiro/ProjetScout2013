/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Registration;
import model.Unit;


public class ModRegPanel extends javax.swing.JPanel {

    private Registration updatedReg;
    private ApplicationController app =new ApplicationController();
    private ArrayList<Unit> listUnit;
    private JFrame parents;
    private ButtonListener buttList;
    
    public ModRegPanel(Registration reg) {
        initComponents();
        updatedReg =  reg;
        buttList = new ButtonListener();
        
        try{
            listUnit = app.getUnits();
             for(Unit var: listUnit)
            {
                comboUnit.addItem(var);
            }
        }
        catch(Exception e)
        {
            //Exception a créer;
        }
        
        buttValid.addActionListener(buttList);
        buttCancel.addActionListener(buttList);
        comboUnit.setSelectedItem(reg.getSect().getUnit());
        comboSect.setSelectedItem(reg.getSect().getLib());
        radButtColis.setSelected(reg.getColis());
        comboState.setSelectedItem(reg.getState());
        
    }

    public void setParents(JFrame frame)
    {
        parents = frame;
    }
    
    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
           if(ae.getSource()== buttValid) {
               //ModRegPanel.this.checkValues();
               updatedReg.setColis(radButtColis.isSelected());
               updatedReg.setState((String)comboState.getSelectedItem());
               updatedReg.setSect((String)comboSect.getSelectedItem(),comboUnit.getSelectedItem().toString());
               
               app.modRegistration(updatedReg);
               
               parents.dispose();
           }
           if(ae.getSource()==buttCancel)
            parents.dispose();
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

        jTextField1 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        labUnit = new javax.swing.JLabel();
        labSect = new javax.swing.JLabel();
        labColis = new javax.swing.JLabel();
        radButtColis = new javax.swing.JRadioButton();
        comboSect = new javax.swing.JComboBox();
        comboUnit = new javax.swing.JComboBox();
        buttValid = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();
        labState = new javax.swing.JLabel();
        comboState = new javax.swing.JComboBox();

        jTextField1.setText("jTextField1");

        labUnit.setText("Unité");

        labSect.setText("Section");

        labColis.setText("Colis envoyé");

        comboSect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Balladins", "Louvetaux", "Eclaireurs", "Pionniers" }));

        buttValid.setText("Valider");

        buttCancel.setText("Annuler");

        labState.setText("Etat inscription");

        comboState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "En attente", "Acceptée", "Refusée" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labColis)
                            .addComponent(labSect)
                            .addComponent(labUnit)
                            .addComponent(labState)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(buttValid)))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboSect, 0, 114, Short.MAX_VALUE)
                    .addComponent(comboUnit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buttCancel))
                    .addComponent(radButtColis)
                    .addComponent(comboState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labUnit)
                        .addGap(29, 29, 29)
                        .addComponent(labSect)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labState)
                            .addComponent(comboState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labColis))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboSect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(radButtColis)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttValid)
                    .addComponent(buttCancel))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttCancel;
    private javax.swing.JButton buttValid;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboSect;
    private javax.swing.JComboBox comboState;
    private javax.swing.JComboBox comboUnit;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labColis;
    private javax.swing.JLabel labSect;
    private javax.swing.JLabel labState;
    private javax.swing.JLabel labUnit;
    private javax.swing.JRadioButton radButtColis;
    // End of variables declaration//GEN-END:variables
}
