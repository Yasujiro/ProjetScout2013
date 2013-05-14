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
import javax.swing.*;
import model.*;


public class ModLegalPanel extends javax.swing.JPanel {

    private ApplicationController app;
    private JFrame parents;
    private Personne modLegal;
    private ArrayList<Localite> listLoc;
    private ButtonListener buttList;
    private Focus focusList;
    public ModLegalPanel(Personne legal) {
        initComponents();
        
        app = new ApplicationController();
        modLegal = legal;
        buttList = new ButtonListener();
        focusList = new Focus();
        buttVali.addActionListener(buttList);
        buttCancel.addActionListener(buttList);
        fieldPostalCode.addFocusListener(focusList);        
        
        setInitValues();
    }

    public void setInitValues(){
        
        fieldName.setText(modLegal.getName());
        fieldFiName.setText(modLegal.getFiName());
        fieldTel.setText(modLegal.getTel());
        fieldMail.setText(modLegal.getMail());
        fieldStreet.setText(modLegal.getStreet());
        fieldNum.setText(modLegal.getHouse());
        fieldBox.setText(modLegal.getBox());        
        fieldPostalCode.setText(""+modLegal.getLoc().getPCode());
        comboLoc.removeAll();
        
        try{
            listLoc = app.getLocalite(modLegal.getLoc().getPCode());
            for(Localite var: listLoc)
                    {
                        comboLoc.addItem(var);
                        if(var.getLib().equals(modLegal.getLoc().getLib()))
                            comboLoc.setSelectedItem(var);
                    }                    
        }
        catch(SearchDataException e){
            app.WriteLog(e.getMessage(),Level.FINER,e);
            JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            app.WriteLog(e.getMessage(),Level.WARNING,e);
            JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setParents(JFrame p){
        parents = p;
    }
    
    
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==buttVali){
                try{
                    modLegal.setName(fieldName.getText());
                    modLegal.setFiName(fieldFiName.getText());
                    modLegal.setTel(fieldTel.getText());
                    modLegal.setMail(fieldMail.getText());
                    modLegal.setStreet(fieldStreet.getText());
                    modLegal.setHouse(fieldNum.getText());
                    modLegal.setBox(fieldNum.getText());
                    modLegal.setLoc((Localite)comboLoc.getSelectedItem());
                    app.modPers(modLegal);
                    app.WriteLog("Modification d'un responsable légal", Level.INFO, null);
                    parents.dispose();
                }
                catch(ModDataException e){
                    app.WriteLog(e.getMessage(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                catch(WrongValuesException e){
                    app.WriteLog(e.getMessage(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e){
                    app.WriteLog(e.getMessage(),Level.WARNING,e);
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br><br>"+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            if(ae.getSource()==buttCancel){
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
             
                if(!fieldPostalCode.getText().equals("")){
                try{
                    Integer pCode = Integer.parseInt(fieldPostalCode.getText());
                    comboLoc.removeAll();
                     listLoc = app.getLocalite(pCode);
            for(Localite var: listLoc)
                    {
                        comboLoc.addItem(var);
                    }             
                }
                catch(SearchDataException e){
                    app.WriteLog(e.getMessage(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e){
                    app.WriteLog(e.getMessage(),Level.WARNING,e);
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue"+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
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
        labFiName = new javax.swing.JLabel();
        labAdrr = new javax.swing.JLabel();
        labStreet = new javax.swing.JLabel();
        labNum = new javax.swing.JLabel();
        labPostalCode = new javax.swing.JLabel();
        labTel = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        fieldFiName = new javax.swing.JTextField();
        fieldStreet = new javax.swing.JTextField();
        fieldNum = new javax.swing.JTextField();
        labBox = new javax.swing.JLabel();
        fieldBox = new javax.swing.JTextField();
        fieldPostalCode = new javax.swing.JTextField();
        labLoc = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        fieldTel = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        fieldMail = new javax.swing.JTextField();
        buttVali = new javax.swing.JButton();
        buttCancel = new javax.swing.JButton();

        labName.setText("Nom :");

        labFiName.setText("Prénom :");

        labAdrr.setText("Adresse :");

        labStreet.setText("Rue");

        labNum.setText("Num");

        labPostalCode.setText("Code postal");

        labTel.setText("Tel :");

        labBox.setText("Boite");

        labLoc.setText("Localité :");

        labMail.setText("Mail");

        buttVali.setText("Valider");

        buttCancel.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labTel)
                        .addGap(18, 18, 18)
                        .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labMail)
                        .addGap(18, 18, 18)
                        .addComponent(fieldMail))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labStreet)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labName)
                                        .addComponent(labFiName)
                                        .addComponent(labAdrr)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldName)
                                        .addComponent(fieldFiName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labPostalCode)
                                        .addGap(18, 18, 18)
                                        .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labLoc)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labNum)
                                        .addGap(18, 18, 18)
                                        .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(buttVali)
                                .addGap(18, 18, 18)
                                .addComponent(buttCancel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labName)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labFiName)
                    .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labAdrr)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labStreet)
                    .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNum)
                    .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labBox)
                    .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPostalCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labLoc)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTel)
                    .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labMail)
                    .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttVali)
                    .addComponent(buttCancel))
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
    private javax.swing.JLabel labBox;
    private javax.swing.JLabel labFiName;
    private javax.swing.JLabel labLoc;
    private javax.swing.JLabel labMail;
    private javax.swing.JLabel labName;
    private javax.swing.JLabel labNum;
    private javax.swing.JLabel labPostalCode;
    private javax.swing.JLabel labStreet;
    private javax.swing.JLabel labTel;
    // End of variables declaration//GEN-END:variables
}
