/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.*;
import Exception.*;
import Interface.*;
import java.util.*;
import model.*;

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
    public void addRegistration(String unit,String sect,Personne pers) throws AddDataException, WrongValuesException
    {
        Calendar curr = Calendar.getInstance();
        Calendar birth = pers.getBirth();
        int age = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        String wrongValuesDesc="";

       
       try{
           /*
            * Test de l'âge de la personne pour vérifier que celui-ci correspond bien a son type
            * et, dans le cas d'un animé, à sa section.
            */
           if(pers.getType().equals("Animé")){
               if(age<5)
                 wrongValuesDesc+="La personne inscrite est trop jeune";
               else if(age<8){ // Si entre 5 et 8 ans
                   if(!sect.equals("Balladins"))
                       wrongValuesDesc+="L'âge du scout ne correspond pas à la tranche d'âge de la section";
               }
               else if (age <12){ // Entre 8 et 12 ans
                       if(!sect.equals("Louveteaux"))
                           wrongValuesDesc+="L'âge du scout ne correspond pas à la tranche d'âge de la section";
               }
               else if (age <16){ // Entre 12 et 16 ans
                   if(!sect.equals("Eclaireurs"))
                       wrongValuesDesc+="L'âge du scout ne correspond pas à la tranche d'âge de la section";
               }
               else if(age < 19){ // Entre 16 et 18 ans.
                   if(!sect.equals("Pionniers"))
                       wrongValuesDesc+="L'âge du scout ne correspond pas à la tranche d'âge de la section";
               }
               else
                   wrongValuesDesc+="Les animés ne peuvent avoir plus de 18 ans";
            }
           else if(pers.getType().equals("Chef"))
           {
               if(age<18)
                    wrongValuesDesc+="Les chef doivent au moins êtres dans leur année de majorité";
               
           }
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

            if(!wrongValuesDesc.equals(""))
                throw new WrongValuesException(wrongValuesDesc);
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
    public void DelReg(Registration reg)throws DeleteException{
        dba.DelReg(reg);
    }
        
    
}
