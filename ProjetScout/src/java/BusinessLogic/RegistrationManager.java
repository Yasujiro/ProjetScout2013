/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.PersonneDBAccess;
import DataAcces.RegistrationDBAccess;
import Exception.AddDataException;
import Exception.ModDataException;
import Exception.SearchDataException;
import Interface.PersonneDataAccess;
import Interface.RegistrationDataAccess;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import javax.swing.JOptionPane;
import model.Personne;
import model.Registration;

public class RegistrationManager {
    
    private Registration reg;
    
    
    
    private RegistrationDataAccess dba;
    private PersonneDataAccess dbaP;
    
    public RegistrationManager(){
        dba = new RegistrationDBAccess();
        dbaP = new PersonneDBAccess();
    }
    
    
    public ArrayList<Registration> getReg(Registration reg) throws SearchDataException
    {
        
        return dba.getReg(reg);
        
    }
    public void addRegistration(String unit,String sect,Personne pers) throws AddDataException
    {
       try{
            reg = new Registration(unit,sect, pers);
            int priceReg = reg.getPrice();
            int nbPers=0;
            double ristourne =1;
            if(reg.getPers().getLegal()!=null){
                
                nbPers = dbaP.countPers(reg.getPers().getLegal().getId());
                ristourne -= ((double)nbPers*10/100);
            }
            
            UUID uniqueId = UUID.randomUUID();
            String idReg=""+uniqueId;

            // Calculer prix en fonction du nombre de personne à la charge du RL de "membre"
            priceReg *= ristourne;
            if(priceReg<35)
                priceReg = 35;
            reg.setPrice(priceReg);
            reg.setId(idReg);


            dba.addRegistration(reg); 
       }
       catch(SearchDataException e){
           throw new AddDataException(e.toString());
       }
       
       
    }
    public void modRegistration(Registration reg) throws ModDataException
    {
        
            dba.modRegistration(reg);
    }
        
    
}
