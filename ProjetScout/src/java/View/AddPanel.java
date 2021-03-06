/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Controller.ApplicationController;
import Exception.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.event.*;
import model.*;


public class AddPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddPanel
     */
    private JFrame popUpFrame;
    private ApplicationController app = new ApplicationController();
    private ArrayList<Unit> listUnit;
    private ArrayList<LegalResp> listLegal;
    
    
    public AddPanel(){
        initComponents();
        
        ComboState comboListener = new ComboState();
        
        //Création du groupe de boutton pour qu'une seule section ne puisse être sélectionnée
        ButtonListener buttonListener = new ButtonListener();        
        groupSect.add(sect1Radio);
        groupSect.add(sect2Radio);
        groupSect.add(sect3Radio);
        groupSect.add(sect4Radio);
        
        // Choix du format d'affichage de la date dans la JSpinner.
        JComponent editor = new JSpinner.DateEditor(spinDate, "dd/MM/yyyy");
        spinDate.setEditor(editor);
        
        buttAddLegal.setEnabled(false);
        comboLegal.setEnabled(false);
        labLegal.setForeground(Color.GRAY);
       
        // Ajouts des différents écouteur aux composants
        comboType.addItemListener(comboListener);
        cancelButton.addActionListener(buttonListener);
        buttAddLegal.addActionListener(buttonListener);
        buttValidate.addActionListener(buttonListener);
        fieldPostalCode.addFocusListener(new Focus());
        spinDate.addChangeListener(new Change());
        
        
        try{ // On tente de garnir les comboBox Unit et ResponsableLégal.
            listUnit = app.getUnits();            
            listLegal = app.getLegal();
            
            
             for(Unit var: listUnit)
            {
                comboUnit.addItem(var);
            }
             for(LegalResp var: listLegal)
             {
                 comboLegal.addItem(var);
             }
        }
        catch(SearchDataException e){
            app.WriteLog(e.getMessage(),Level.FINER,e);
            JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        } 
        catch(Exception e){
            app.WriteLog(e.getMessage(),Level.WARNING,e);
            JOptionPane.showMessageDialog(null,"<html>Une erreur inattendue est survenue<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
        }

    }
    /*
     * Méthode permettant de remettre les attributs par défaut des composants swing.
     * Cette méthode est surtout utilisée en réponse a l'action du boutton Cancel.
     */
    public void resetValues(){
                comboType.setSelectedIndex(0);               
                groupSect.clearSelection();                
                spinDate.setValue(new Date(Calendar.getInstance().getTimeInMillis()));
                comboLoc.removeAllItems();
                comboLoc.addItem("Sélectionner une localité");
                comboLegal.setSelectedIndex(0);
                fieldPostalCode.setText("");
                comboUnit.setSelectedIndex(0);
                comboLegal.setSelectedItem(0);
                fieldName.setText(null);
                fieldFiName.setText(null);
                fieldTel.setText(null);
                fieldMail.setText(null);
                fieldTotem.setText(null);
                fieldStreet.setText(null);
                fieldNum.setText(null);
                fieldBox.setText(null);
    }
    /*
     * Classe interne permettant de gérer les évenements des comboBox.
     */
    private class ComboState implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            
            // Si la comboBox est celle des type.
           if(ie.getSource().equals(comboType))
           {
                if(ie.getItem().equals("Chef"))
                {/*Si type "Chef" sélectionné on empèche le choix d'un responsable légal
                 * Et on rend éditable les champs Tel et mail.
                 */
                         comboLegal.setSelectedIndex(0);
                         comboLegal.setEnabled(false);
                         buttAddLegal.setEnabled(false);
                         fieldTel.setEnabled(true);
                         fieldMail.setEnabled(true);
                         labPhone.setForeground(Color.black);
                         labMail.setForeground(Color.black);
                }
                else
                {/*
                 * Si type "Animé" sélectionné, on rend inéditable les champs Tel et Mail
                 * L'édition du choix de Rl ne se fait pas ici.
                 */
                    fieldMail.setText(null);
                    fieldTel.setText(null);
                    fieldTel.setEnabled(false);
                    fieldMail.setEnabled(false);
                    labPhone.setForeground(Color.gray);
                    labMail.setForeground(Color.gray);
                }
               
           }       
           
        }
    }
    /*
     * Classe interne gérant les action effectuées sur clique de bouttons.
     */
    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            // Si boutton Valider
            if(ae.getSource()== buttValidate)
            {
                Personne legalResp;
                
                try{
                    // On garni un string en fonction du boutton de section sélectionné.
                    String section=null;
                    if(sect1Radio.isSelected())
                        section=sect1Radio.getText();
                    else if (sect2Radio.isSelected())
                            section=sect2Radio.getText();
                    else if (sect3Radio.isSelected())
                        section=sect3Radio.getText();
                    else if (sect4Radio.isSelected())
                        section=sect4Radio.getText();

                    // Si pas de RL sélectionner, valeur de RL mise à null (car on va passer l'objet à la méthode d'ajout plus bas)
                    if(comboLegal.getSelectedItem().equals("Sélectionner un responsable"))
                        legalResp = null;
                    else // Sinon on le garni avec l'objet sélectionné.
                        legalResp = (LegalResp)comboLegal.getSelectedItem();
                    // Si pas de localité sélectionnée, nouvelle exception jetée.
                    if(comboLoc.getSelectedItem().equals("Sélectionner une localité"))
                        throw new WrongValuesException("Veuillez sélectionner une localité");
                    
                    /*
                     * Appel de la fonction "addPersonne" du controlleur afin d'ajouter la personne dans la BD et de créer l'objet Personne
                     * utilisé dans la fonction "addRegistration" par la suite.
                     */
                    Personne pers = app.addPersonne((String)comboType.getSelectedItem(),fieldName.getText(),fieldFiName.getText(),
                            (Date)spinDate.getValue(),legalResp,fieldStreet.getText(),fieldNum.getText(),
                            fieldBox.getText(),(Localite)comboLoc.getSelectedItem(),fieldTel.getText(),fieldMail.getText(),fieldTotem.getText());


                       app.addRegistration(comboUnit.getSelectedItem().toString(),section,pers);
                        
                        
                       app.WriteLog("Ajout d'une demande", Level.INFO, null);
                       JOptionPane.showMessageDialog(null, "Ajout bien déroulé","Ajout confirmé",JOptionPane.PLAIN_MESSAGE);
                       resetValues(); // On remet les valeur par défaut si tout s'est bien passé.
                
                    
            }
            catch(AddDataException e){
                app.WriteLog(e.getMessage(),Level.FINER,e);
                JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            catch(WrongValuesException e){
                    app.WriteLog(e.toString(), Level.FINEST, e);
                    JOptionPane.showMessageDialog(null,e.toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
           
            catch(Exception e){
                app.WriteLog(e.getMessage(),Level.WARNING,e);
                JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
            
            // Si boutton "Ajout RL"
            if(ae.getSource()==buttAddLegal)
            {
                /*
                 * On crée un panneau d'ajout de RL
                 * On appel la fonction getPopUpInstance() afin d'obtenir la fenêtre "popUp"
                 * Une fois celle-ci "obtenu" on la garni du pannel créé.
                 */
                AddLegalResp popUpPanel = new AddLegalResp(comboLegal);
                popUpFrame = PopUp.getPopUpInstance();
                popUpFrame.setLocation(200,150);
                PopUp.setContent(popUpPanel); 
                popUpPanel.setPopUp(popUpFrame);
                popUpFrame.setVisible(true);
            }
            //Si boutton "Annulé", mise par défault des valeurs.
            if(ae.getSource().equals(cancelButton))
            {
                resetValues();
            }
        }
        
    }
    /*
     * Classe interne permettant de gérer le gain ou la perte de focus d'un composant swing.
     * Dans notre cas elle sera utilisée avec le champs "PostalCode"
     */
    private class Focus implements FocusListener
    {

        

        @Override
        
        public void focusLost(FocusEvent fe) {
           // Si le champs PostalCode perds le focus 
            if(fe.getSource()==fieldPostalCode){
                
                try{
                    /*
                     * Si le code postal n'est pas null, on transforme le string reçu du champs texte en un entier
                     * On effectue ensuite une recherche des localités ayant ce code postal
                     * On ajoute toutes ces localités à la comboBox, en ayant au préalable enlever les anciennes localités
                     */
                    ArrayList<Localite> listLoca=null;
                    Integer postalCode = null;

                    if(!fieldPostalCode.getText().equals(""))                        
                        postalCode = Integer.parseInt(fieldPostalCode.getText());
                    AddPanel.this.comboLoc.removeAllItems();
                    AddPanel.this.comboLoc.addItem("Sélectionner une localité");
                    listLoca = app.getLocalite(postalCode);
                    for(Localite var: listLoca)
                    {
                        AddPanel.this.comboLoc.addItem(var);
                    }
                }
                catch(SearchDataException e){
                     app.WriteLog(e.getMessage(),Level.FINER,e);
                     JOptionPane.showMessageDialog(null,"<html>"+e.toString()+"<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.PLAIN_MESSAGE);
                  }
                catch(NumberFormatException e){
                    app.WriteLog(e.getMessage(),Level.FINER,e);
                    JOptionPane.showMessageDialog(null, "Erreur - Le code postal doit être un nombre","Erreur",JOptionPane.ERROR_MESSAGE);
                  }
                catch(Exception e){
                    app.WriteLog(e.getMessage(),Level.WARNING,e);
                    JOptionPane.showMessageDialog(null,"<html>Une Erreur inattendue est survenue<br>Référez vous au fichier de log pour plus de détails</html>","Erreur",JOptionPane.ERROR_MESSAGE);
                  }
           }
            
            
        }

        
        public void focusGained(FocusEvent fe) {
            
        }
        
    }
    /*
     * Classe interne permettant de gérant le changement d'état de notre JSpinner.
     */
    private class Change implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent ce) {
           if(ce.getSource()==spinDate)
            {
                /*
                 * Si la valeur de la JSpinner change et que le type sélectionné est "Animé"
                 * On calcule l'age
                 * Si l'âge est inférieur à 18, on rends sélectionnable le choix du Responsable légal.
                 */
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
                        fieldTel.setEnabled(true);
                        fieldMail.setEnabled(true);
                        labMail.setForeground(Color.BLACK);
                        labPhone.setForeground(Color.BLACK);
                    }
                    else
                    {
                        buttAddLegal.setEnabled(true);
                        comboLegal.setEnabled(true);
                        labLegal.setForeground(Color.BLACK);
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
        fieldStreet = new javax.swing.JTextField();
        labNum = new javax.swing.JLabel();
        labLoc = new javax.swing.JLabel();
        comboLoc = new javax.swing.JComboBox();
        labPostalCode = new javax.swing.JLabel();
        fieldPostalCode = new javax.swing.JTextField();
        labPhone = new javax.swing.JLabel();
        fieldTel = new javax.swing.JTextField();
        labMail = new javax.swing.JLabel();
        fieldMail = new javax.swing.JTextField();
        labTotem = new javax.swing.JLabel();
        fieldTotem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buttAddLegal = new javax.swing.JButton();
        buttValidate = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        fieldNum = new javax.swing.JTextField();
        labBox = new javax.swing.JLabel();
        fieldBox = new javax.swing.JTextField();

        labType.setText("Type de personne à ajouter");

        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chef", "Animé" }));

        sect1Radio.setText("Balladins");

        sect2Radio.setText("Louveteaux");

        sect3Radio.setText("Eclaireurs");

        sect4Radio.setText("Pionniers");

        labName.setText("Nom");

        labFiName.setText("Prénom");

        jLabel2.setText("Date de Naissance");

        spinDate.setModel(new javax.swing.SpinnerDateModel());

        labUnit.setText("Unité");

        comboUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une unité" }));

        labLegal.setText("Responsable légal");

        comboLegal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner un responsable" }));

        labAddr.setText("Adresse :");

        labStreet.setText("Rue :");

        labNum.setText("Num");

        labLoc.setText("Localité :");

        comboLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sélectionner une localité" }));

        labPostalCode.setText("Code Postal");

        labPhone.setText("GSM");

        labMail.setText("E-mail");

        labTotem.setText("Totem*");

        jLabel3.setText("* : Champs facultatifs");

        buttAddLegal.setText("Nouveau");

        buttValidate.setText("Valider");

        cancelButton.setText("Annuler");

        labBox.setText("Boite");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labType)
                            .addComponent(labUnit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labAddr)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sect1Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sect2Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sect3Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sect4Radio)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labFiName)
                                .addGap(18, 18, 18)
                                .addComponent(fieldFiName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labPhone)
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labStreet)
                                        .addGap(18, 18, 18)
                                        .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labNum)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cancelButton)
                                            .addComponent(comboLoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(spinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(labTotem)
                                            .addGap(18, 18, 18)
                                            .addComponent(fieldTotem))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(labName)
                                            .addGap(26, 26, 26)
                                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labLegal)
                                .addGap(18, 18, 18)
                                .addComponent(comboLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(buttAddLegal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(fieldTotem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNum)
                    .addComponent(fieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labBox)
                    .addComponent(fieldBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labLoc)
                    .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPostalCode)
                    .addComponent(fieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPhone)
                    .addComponent(fieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labMail)
                    .addComponent(fieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttValidate)
                    .addComponent(cancelButton))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JTextField fieldBox;
    private javax.swing.JTextField fieldFiName;
    private javax.swing.JTextField fieldMail;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldNum;
    private javax.swing.JTextField fieldPostalCode;
    private javax.swing.JTextField fieldStreet;
    private javax.swing.JTextField fieldTel;
    private javax.swing.JTextField fieldTotem;
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
    private javax.swing.JRadioButton sect1Radio;
    private javax.swing.JRadioButton sect2Radio;
    private javax.swing.JRadioButton sect3Radio;
    private javax.swing.JRadioButton sect4Radio;
    private javax.swing.JSpinner spinDate;
    // End of variables declaration//GEN-END:variables
   
}
