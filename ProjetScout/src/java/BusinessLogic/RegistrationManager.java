/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.RegistrationDBAccess;
import java.util.Calendar;
import model.Personne;
import model.Registration;

/**
 *
 * @author Jérémy
 */
public class RegistrationManager {
    
    private Registration reg;
    private Personne membre;
    private int price;
    private String id="";
    private RegistrationDBAccess dba;
    
    public void addRegistration(String unit,String sect,Personne pers)
    {
        reg = new Registration(unit,sect);
        membre = pers;
        
        //Générer UUID.
        
        // Calculer prix en fonction du nombre de personne à la charge du RL de "membre"
        reg.setPrice(price);
        reg.setId(id);
        reg.setMembre(membre);
        
        dba.addRegistration(reg);
        
        
        
        
        
    }
    
}
