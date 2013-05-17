/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import Exception.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import model.*;

public class ListingPanel extends javax.swing.JPanel {

    private ApplicationController app;
    private SearchRegModel tableModel;
    private ArrayList<Registration> listReg;
    private ButtonListener buttList;
    public ListingPanel() {
        initComponents();
        app = new ApplicationController();
        buttList = new ButtonListener();
        buttDelete.addActionListener(buttList);
        this.getTableValue();
        
        
    }
    public void getTableValue(){
                try{
            listReg = app.getReg(null);
            tableModel = new SearchRegModel(listReg);
            tableResult.setModel(tableModel);
            tableResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
             TableColumn column = null;
				for (int i = 0; i < 4; i++) {
				    column = tableResult.getColumnModel().getColumn(i);
				    switch (i) 
				    {	//Unité
				    	case 0 : column.setPreferredWidth(20);                                                 
				    		break;
				    	//Section
				    	case 1 : column.setPreferredWidth(20);
			    			break;	
				    	//Etat	
				    	case 2 : column.setPreferredWidth(20);
				    		break;
				    	//Date Création	
				    	case 3 : column.setPreferredWidth(20); 
				    		break;
                                        //Demandeur
                                        case 4 : column.setPreferredWidth(100);
				    		break;
                                        // Responsable
                                        case 5: column.setPreferredWidth(100);
                                            break;
                                        //Adresse
                                        case 6:column.setPreferredWidth(400);
                                            break;
                                        case 7: column.setPreferredWidth(20);
                                            break;
                                        default :
                                            column.setPreferredWidth(20);
                                            break;                                            
				    }
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
    }
    private class ButtonListener implements ActionListener{

        
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==buttDelete){
                try{
                    Registration reg = tableModel.getSelectedReg(tableResult.getSelectedRow());
                    app.DelReg(reg);
                    app.WriteLog("Suppression d'une demande", Level.INFO, null);
                    JOptionPane.showMessageDialog(null,"La suppression à correctement eue lieu","Erreur",JOptionPane.INFORMATION_MESSAGE);
                    ListingPanel.this.getTableValue();
                }
                catch(ArrayIndexOutOfBoundsException e){
                    app.WriteLog(e.getMessage(),Level.FINEST,e);
                    JOptionPane.showMessageDialog(null,"Veuillez sélectionner une ligne","Erreur",JOptionPane.ERROR_MESSAGE);
                    
                }
                catch(DeleteException e){
                    app.WriteLog(e.getMessage(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null,"<html>"+e.getMessage()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
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
        tableResult = new javax.swing.JTable();
        buttDelete = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jScrollPane1.setViewportView(tableResult);

        buttDelete.setText("Supprimer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttDelete)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttDelete)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttDelete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableResult;
    // End of variables declaration//GEN-END:variables
}
