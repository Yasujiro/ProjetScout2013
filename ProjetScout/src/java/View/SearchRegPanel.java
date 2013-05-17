/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import Exception.SearchDataException;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.table.TableColumn;
import model.*;


public class SearchRegPanel extends javax.swing.JPanel {

    private ApplicationController app;
    private ButtonListener buttListener;
    private ArrayList<Registration> result;
    private ArrayList<Unit>listUnit;
    private SearchRegModel tableModel;
    private JFrame popUpFrame;
    private Registration selectedReg;
    private String section ="",unit,name,fiName;
    
    
    public SearchRegPanel() {
        initComponents();
        popUpFrame = null;
        app = new ApplicationController();
        buttListener = new ButtonListener();
        radButtGroup.add(radSect1);
        radButtGroup.add(radSect2);
        radButtGroup.add(radSect3);
        radButtGroup.add(radSect4);
        
        try{
            listUnit = app.getUnits();
             for(Unit var: listUnit)
            {
                comboUnit.addItem(var);
            }
        }
        catch(SearchDataException e){
           app.WriteLog(e.getMessage(),Level.FINER,e);
           JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            app.WriteLog(e.getMessage(),Level.WARNING,e);
            JOptionPane.showMessageDialog(null,"<html>Une erreur inattendue est survenue"+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
        
        
        buttVali.addActionListener(buttListener);
        buttModReg.addActionListener(buttListener);
        buttModPers.addActionListener(buttListener);
        buttModLegal.addActionListener(buttListener);
        
        
        JComponent editor = new JSpinner.DateEditor(spinDate, "dd/MM/yyyy");
        spinDate.setEditor(editor);
        
    }
    
    public void checkValues()
    {
        if(comboUnit.getSelectedIndex()==0)
          unit = "%";
        else
            unit = comboUnit.getSelectedItem().toString();
        
        if(radSect1.isSelected())
                    section=radSect1.getText();
                else if (radSect2.isSelected())
                        section=radSect2.getText();
                else if (radSect3.isSelected())
                    section=radSect3.getText();
                else if (radSect4.isSelected())
                    section=radSect4.getText();
                else
                    section = "%";
        if(fieldName.getText().equals(""))
            name="%";
        else
            name=fieldName.getText();
        if(fieldFiName.getText().equals(""))
            fiName="%";
        else
            fiName=fieldFiName.getText();
    }
    
    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()== buttVali)
            {
                
                SearchRegPanel.this.checkValues();
                
                
                
                Personne pers = new Personne(name,fiName);
                Registration reg = new Registration(unit,section,pers);
                reg.setColis(radButtColis.isSelected());
                reg.setState((String)comboState.getSelectedItem());
                reg.setCrea((Date)spinDate.getValue());
                
                try{
                result = app.getReg(reg);
                
                tableModel = new SearchRegModel(result);
                resultTable.setModel(tableModel);
                resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                
                
                
                TableColumn column = null;
				for (int i = 0; i < 4; i++) {
				    column = resultTable.getColumnModel().getColumn(i);
				    switch (i) 
				    {	//Unité
				    	case 0 : column.setPreferredWidth(50);
				    		break;
				    	//Section
				    	case 1 : column.setPreferredWidth(50);
			    			break;	
				    	//Etat	
				    	case 2 : column.setPreferredWidth(50);
				    		break;
				    	//Date Création	
				    	case 3 : column.setPreferredWidth(50); 
				    		break;
                                        //Demandeur
                                        case 4 : column.setPreferredWidth(150);
				    		break;
                                        // Responsable
                                        case 5: column.setPreferredWidth(150);
                                            break;
                                        //Adresse
                                        case 6:column.setPreferredWidth(200);
                                            break;
                                        case 7: column.setPreferredWidth(50);
                                            break;
                                        default :
                                            column.setPreferredWidth(50);
                                            break;                                            
				    }
				}
                }
                catch(SearchDataException e){
                    app.WriteLog(e.getMessage(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null, "<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.PLAIN_MESSAGE);
                }
                catch(Exception e){
                    app.WriteLog(e.getMessage(),Level.WARNING,e);
                    JOptionPane.showMessageDialog(null,"<html>Une erreur inattendue est survenue<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            if(ae.getSource()==buttModReg ||ae.getSource()== buttModPers || ae.getSource()== buttModLegal){
                 if(resultTable.getSelectedRow() >= 0)
                    selectedReg = tableModel.getSelectedReg(resultTable.getSelectedRow());  
                 else
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande","Erreur",JOptionPane.PLAIN_MESSAGE);
            }
            if(ae.getSource()==buttModReg)
            {
                if(selectedReg!=null){
                    ModRegPanel modReg = new ModRegPanel(selectedReg);
                    popUpFrame =PopUp.getPopUpInstance();                    
                    PopUp.setContent(modReg);
                    modReg.setParents(popUpFrame);
                    popUpFrame.setVisible(true);
                }
                
            }
            if(ae.getSource()==buttModPers){
                if(selectedReg!=null){
                    ModPersPanel modPers = new ModPersPanel(selectedReg.getPers());
                    popUpFrame = PopUp.getPopUpInstance();;
                    PopUp.setContent(modPers);
                    modPers.setParents(popUpFrame);
                    
                    popUpFrame.setVisible(true);
                    
                    
                }
            }
            if(ae.getSource()==buttModLegal){
                
                if(selectedReg.getPers().getLegal()!=null){
                ModLegalPanel modLegal = new ModLegalPanel(selectedReg.getPers().getLegal());
                        
                    popUpFrame = PopUp.getPopUpInstance();
                    PopUp.setContent(modLegal);
                    popUpFrame.setResizable(false);
                    modLegal.setParents(popUpFrame);
                    popUpFrame.setVisible(true);
            }
                else{
                    JOptionPane.showMessageDialog(null, "Cette demande concerne une personne majeure. Il n'y a donc pas de responsable légal","Erreur",JOptionPane.PLAIN_MESSAGE);
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

        radButtGroup = new javax.swing.ButtonGroup();
        labUnit = new javax.swing.JLabel();
        labSect = new javax.swing.JLabel();
        radSect1 = new javax.swing.JRadioButton();
        radSect2 = new javax.swing.JRadioButton();
        radSect3 = new javax.swing.JRadioButton();
        radSect4 = new javax.swing.JRadioButton();
        labName = new javax.swing.JLabel();
        labFiName = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        fieldFiName = new javax.swing.JTextField();
        labDate = new javax.swing.JLabel();
        spinDate = new javax.swing.JSpinner();
        labColis = new javax.swing.JLabel();
        labState = new javax.swing.JLabel();
        radButtColis = new javax.swing.JRadioButton();
        comboState = new javax.swing.JComboBox();
        buttVali = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();
        labResult = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        buttModReg = new javax.swing.JButton();
        buttModPers = new javax.swing.JButton();
        buttModLegal = new javax.swing.JButton();
        comboUnit = new javax.swing.JComboBox();

        labUnit.setText("Unité");

        labSect.setText("Section");

        radSect1.setText("Balladins");

        radSect2.setText("Louvetaux");

        radSect3.setText("Eclaireurs");

        radSect4.setText("Pionniers");

        labName.setText("Nom du demandeur");

        labFiName.setText("Prénom du demandeur");

        labDate.setText("Date d'introduction");

        spinDate.setModel(new javax.swing.SpinnerDateModel());

        labColis.setText("Colis envoyé");

        labState.setText("Etat");

        radButtColis.setText("Oui");

        comboState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "En attente", "Acceptée", "Refusée" }));

        buttVali.setText("Valider");

        buttCancel.setText("Annuler");

        labResult.setText("Resultat");

        jScrollPane2.setViewportView(resultTable);

        buttModReg.setText("Modifier Demande");

        buttModPers.setText("Modifier Demandeur");

        buttModLegal.setText("Modifier responsable");

        comboUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une unité" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labName)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labColis)
                                        .addComponent(labDate)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radButtColis)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttVali)
                                        .addGap(79, 79, 79)
                                        .addComponent(buttCancel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labFiName)
                                            .addComponent(labState))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fieldFiName)
                                            .addComponent(comboState, 0, 140, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labSect)
                                    .addComponent(labUnit))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radSect1)
                                        .addGap(18, 18, 18)
                                        .addComponent(radSect2)
                                        .addGap(18, 18, 18)
                                        .addComponent(radSect3)
                                        .addGap(18, 18, 18)
                                        .addComponent(radSect4))))
                            .addComponent(labResult, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttModReg)
                .addGap(18, 18, 18)
                .addComponent(buttModPers)
                .addGap(18, 18, 18)
                .addComponent(buttModLegal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labUnit)
                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radSect1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radSect3)
                        .addComponent(radSect4)
                        .addComponent(labSect)
                        .addComponent(radSect2)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labName)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labFiName)
                            .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labState)
                            .addComponent(comboState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labDate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radButtColis)
                            .addComponent(labColis))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttVali)
                            .addComponent(buttCancel))))
                .addGap(8, 8, 8)
                .addComponent(labResult)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttModReg)
                    .addComponent(buttModPers)
                    .addComponent(buttModLegal))
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttCancel;
    private javax.swing.JButton buttModLegal;
    private javax.swing.JButton buttModPers;
    private javax.swing.JButton buttModReg;
    private javax.swing.JButton buttVali;
    private javax.swing.JComboBox comboState;
    private javax.swing.JComboBox comboUnit;
    private javax.swing.JTextField fieldFiName;
    private javax.swing.JTextField fieldName;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labColis;
    private javax.swing.JLabel labDate;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labResult;
    private javax.swing.JLabel labSect;
    private javax.swing.JLabel labState;
    private javax.swing.JLabel labUnit;
    private javax.swing.JRadioButton radButtColis;
    private javax.swing.ButtonGroup radButtGroup;
    private javax.swing.JRadioButton radSect1;
    private javax.swing.JRadioButton radSect2;
    private javax.swing.JRadioButton radSect3;
    private javax.swing.JRadioButton radSect4;
    private javax.swing.JTable resultTable;
    private javax.swing.JSpinner spinDate;
    // End of variables declaration//GEN-END:variables
}
