/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import Exception.SearchDataException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import model.Localite;
import model.Personne;
import model.SchPersModel;
import model.Unit;


public class SearchPanel extends javax.swing.JPanel {

    /**
     * Creates new form SearchPanel
     */
    private ArrayList<Personne> listPersFound;
    private SchPersModel modelTable;
    private ApplicationController app = new ApplicationController();
    public SearchPanel() {
        initComponents();
        
        
        ButtonState buttonListener = new ButtonState();
        buttValid.addActionListener(buttonListener);
        buttCancel.addActionListener(buttonListener);
        fieldPostalCode.addFocusListener(new Focus());
    }
    


    private class ButtonState implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==buttValid)
            {
                try{
                    String type="";
                    Localite loc;

                    if(comboLoc.getSelectedItem().equals("Sélectionner une localité"))
                        if(fieldPostalCode.getText().equals(""))
                            loc = new Localite("",null);
                        else
                            loc = new Localite("",Integer.parseInt(fieldPostalCode.getText()));
                    else
                        loc = (Localite)comboLoc.getSelectedItem();

                    if(!comboType.getSelectedItem().equals("Tous"))
                        type = (String)comboType.getSelectedItem();

                   listPersFound = app.getPers(type, fieldName.getText(),fieldFiName.getText(),loc);
                   modelTable = new SchPersModel(listPersFound);
                   resultTable.setModel(modelTable);

                   TableColumn column = null;
                                    for (int i = 0; i < 4; i++) {
                                        column = resultTable.getColumnModel().getColumn(i);
                                        switch (i) 
                                        {	//Type
                                            case 0 : column.setPreferredWidth(50);
                                                    break;
                                            //Nom
                                            case 1 : column.setPreferredWidth(50);
                                                    break;	
                                            //Prénom
                                            case 2 : column.setPreferredWidth(50);
                                                    break;
                                            //Adresse
                                            case 3 : column.setPreferredWidth(200); 
                                                    break;
                                            //Responsable
                                            case 4 : column.setPreferredWidth(150);
                                                    break;
                                        }
                                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            if(ae.getSource()==buttCancel)
            {
                comboType.setSelectedItem("Tous");                
                comboLoc.removeAll();
                comboLoc.addItem("Sélectionner une localité");
                fieldName.setText(null);
                fieldFiName.setText(null);
                fieldPostalCode.setText(null);
            }
        }
        
        
    }
    private class Focus implements FocusListener{

        @Override
        public void focusGained(FocusEvent fe) {
            
        }

        @Override
        public void focusLost(FocusEvent fe) {
           
            if(fe.getSource()==fieldPostalCode)
            {
                              
                ArrayList<Localite> listLoca=null;
                Integer postalCode = null;
                
                if(!fieldPostalCode.getText().equals(""))
                {
                    try{
                         postalCode = Integer.parseInt(fieldPostalCode.getText());
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                
                comboLoc.removeAllItems();
                comboLoc.addItem("Sélectionner une localité");
                
                try{
                    listLoca = app.getLocalite(postalCode);
                }
                catch(SearchDataException e)
                {
                    JOptionPane.showMessageDialog(null,e.toString(),"Erreur",JOptionPane.PLAIN_MESSAGE);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br><br>"+e.toString()+"</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                
                for(Localite var: listLoca)
                {

                    comboLoc.addItem(var);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        labName = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        labFiName = new javax.swing.JLabel();
        fieldFiName = new javax.swing.JTextField();
        labType = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox();
        labLoca = new javax.swing.JLabel();
        labResult = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        buttValid = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();
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

        labName.setText("Nom");

        labFiName.setText("Prénom");

        labType.setText("Type de personne recherché");

        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "Chef", "Animé", "Responsable légal" }));

        labLoca.setText("Localité");

        labResult.setText("Résultat");

        comboLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une localité" }));

        jScrollPane2.setViewportView(resultTable);

        buttValid.setText("Valider");

        buttCancel.setText("Annuler");

        labPostalCode.setText("Code Postal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labPostalCode)
                            .addGap(30, 30, 30)
                            .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(labType)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(180, 180, 180)
                            .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labLoca)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labFiName)))
                        .addComponent(fieldFiName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(labResult, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(buttValid)
                        .addGap(18, 18, 18)
                        .addComponent(buttCancel)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labType))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labFiName)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPostalCode)
                    .addComponent(labLoca)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttCancel)
                    .addComponent(buttValid))
                .addGap(25, 25, 25)
                .addComponent(labResult)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttCancel;
    private javax.swing.JButton buttValid;
    private javax.swing.JComboBox comboLoc;
    private javax.swing.JComboBox comboType;
    private javax.swing.JTextField fieldFiName;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldPostalCode;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLoca;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labPostalCode;
    private javax.swing.JLabel labResult;
    private javax.swing.JLabel labType;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables
}
