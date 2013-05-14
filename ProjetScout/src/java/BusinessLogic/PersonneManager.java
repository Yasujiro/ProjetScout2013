/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.PersonneDBAccess;
import Exception.*;
import Interface.PersonneDataAccess;
import java.util.*;
import java.util.regex.Pattern;
import model.*;


public class PersonneManager {
    
    private PersonneDataAccess dba;
    
    public PersonneManager(){
        dba = new PersonneDBAccess();
    }
   
    
    public Personne addPersonne(String type,String name,String fiName, Date birth,Personne legalResp,
            String street,String num,String box,Localite loc,String tel,String mail,String totem) throws WrongValuesException,  AddDataException
    {
        
        Personne pers;
        /*
         * Création d'un UUID grâce à la méthode static 'randomUUID()'
         * et "conversion" de celui en string pour pouvoir être intégré plus tard dans la BD.
         */
        UUID uniqueId = UUID.randomUUID();
        String idPers=""+uniqueId;
        
        String wrongValuesDescript="";
        
        
        
        Calendar birthDate = Calendar.getInstance();
        if(birth!=null){
            birthDate.setTime(birth);            
        }
        
        /*Début des test de valeurs pour les différentes variables reçu.
         * Utilisation de regex  pour tester les String.
         */
        //<editor-fold>
        if(!Pattern.matches("\\p{Upper}([\\p{IsLatin}]|\\p{Blank})+",name))
            wrongValuesDescript+="Nom invalide \n";
        if(!Pattern.matches("\\p{Upper}([\\p{IsLatin}]|\\p{Blank})+",fiName))
            wrongValuesDescript+="Prénom invalide \n";
        if(totem!=null){
            if(!totem.equals("")){
                if(!Pattern.matches("\\p{Upper}([\\p{IsLatin}]|\\p{Blank})+",totem))
                    wrongValuesDescript+="Totem invalide \n";
            }
        }
        if(!Pattern.matches("[0-9]+[A-Z]{0,1}", num))
            wrongValuesDescript+="Numéro de rue invalide \n";
        if(type.equals("Chef")||type.equals("Responsable légal")){
            if(!Pattern.matches("[qq\\p{IsLatin}\\p{Digit}\\p{Punct}&&[^@]]+[@][\\p{Alpha}]+[/.][\\p{Alpha}]{2,3}",mail))
                wrongValuesDescript+="Email invalide \n";
        }
        /*
         * Fin des Test
         */
//</editor-fold>
         if(!wrongValuesDescript.equals(""))
            throw new WrongValuesException(wrongValuesDescript);
        
        
        
        // "Choix" de la classe fille utilisé pour la création de l'objet personne en fonction du type reçu en argument.
        if(type.equals("Animé"))
        {
            
            pers = new Anime(name,fiName,street,num,birthDate,legalResp);
            pers.setTel(tel);
            pers.setMail(mail);
        }
        else if(type.equals("Chef"))
        {
            pers = new Chief(name,fiName,street,num,birthDate,tel,mail);
        }
        else if (type.equals("Responsable légal"))
        {
            pers = new LegalResp(name,fiName,street,num,tel,mail);
            
        }
        else            
            pers=null;
        
        // Garniture des attributs restant de la personne + appel de la méthode de la couche dataaccess.
        pers.setBox(box);
        pers.setId(idPers);
        pers.setLoc(loc);
        pers.setTotem(totem);
        dba.addPersonne(pers);
       
        return pers;
        
    }
    
    public ArrayList<LegalResp>  getLegal() throws SearchDataException
    {
        return dba.getLegal();
    }
    
    public void modPers(Personne p)throws ModDataException, WrongValuesException{
        
        /*Début des test de valeurs pour les différentes variables reçu.
         * Utilisation de regex  pour tester les String.
         */
        //<editor-fold>
        String wrongValuesDescript="";
        if(!Pattern.matches("\\p{Upper}([\\p{IsLatin}]|\\p{Blank})+",p.getName()))
            wrongValuesDescript+="Nom invalide \n";
        if(!Pattern.matches("\\p{Upper}([\\p{IsLatin}]|\\p{Blank})+",p.getFiName()))
            wrongValuesDescript+="Prénom invalide \n";
        if(!p.getTotem().equals("")){
            if(!Pattern.matches("\\p{Upper}([\\p{IsLatin}]|\\p{Blank})+",p.getTotem()))
                wrongValuesDescript+="Totem invalide \n";
        }
        if(!Pattern.matches("[0-9]+[A-Z]{0,1}", p.getHouse()))
            wrongValuesDescript+="Numéro de rue invalide \n";
        if(p.getType().equals("Chef")||p.getType().equals("Responsable légal")){
            if(!Pattern.matches("[\\p{IsLatin}\\p{Digit}\\p{Punct}&&[^@]]+[@][\\p{Alpha}]+[/.][\\p{Alpha}]{2,3}",p.getMail()))
                wrongValuesDescript+="Email invalide \n";
        }
        if (!wrongValuesDescript.equals(""))
            throw new WrongValuesException(wrongValuesDescript);
        //</editor-fold>
        dba.modPers(p);
        
    }
    
    public ArrayList<Personne> getPers(String type,String name,String fiName, Localite loc)throws SearchDataException{
        
         /*
          * Modification des éventuelles string vide ainsi que du code postal afin d'effectuer une recherche global 
          * sur la BD dans ce cas.
          */   
            Personne persSought;
            if(name.equals(""))
                name ="%";
            if(fiName.equals(""))
                fiName="%";
            if(loc.getLib().equals(""))
                loc.setLib("%");
            if(loc.getPCode()==null){
                loc.setPCode(9999);
            }
            // "Choix" de la classe fille utilisé pour la création de l'objet personne en fonction du type reçu en argument.
            if(type.equals("Chef"))
                persSought = new Chief(name,fiName); 

            else if (type.equals("Animé"))
                persSought = new Anime(name,fiName);
            else if (type.equals("Responsable légal"))
                persSought = new LegalResp(name,fiName);
            else
                persSought = new Personne (name,fiName);
            persSought.setLoc(loc);
            return dba.getPers(persSought);
        }
        
    
}
