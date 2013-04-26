/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.RegistrationDBAccess;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import model.Personne;
import model.Registration;

/**
 *
 * @author Jérémy
 */
public class RegistrationManager {
    
    private Registration reg;
    
    private Integer price = 15;
    private String idReg="";
    private RegistrationDBAccess dba = new RegistrationDBAccess();;
    
    
    public ArrayList<Registration> getReg(Registration reg)
    {
        return dba.getReg(reg);
    }
    public void addRegistration(String unit,String sect,Personne pers)
    {
        
        reg = new Registration(unit,sect, pers);
        
        
        UUID uniqueId = UUID.randomUUID();
        String idReg=""+uniqueId;
        
        // Calculer prix en fonction du nombre de personne à la charge du RL de "membre"
        reg.setPrice(price);
        reg.setId(idReg);
       
        
        dba.addRegistration(reg);  
    }
    public void modRegistration(Registration reg)
    {
        dba.modRegistration(reg);
    }
    
}
