/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.PersonneDBAccess;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.swing.JOptionPane;
import model.Anime;
import model.Chief;
import model.LegalResp;
import model.Localite;
import model.Personne;


public class PersonneManager {
    
    
    public Personne addPersonne(String type,String name,String fiName, Date birth,Personne legalResp,String street,String num,String box,Localite loc,String tel,String mail,String totem)
    {
        PersonneDBAccess dba = new PersonneDBAccess();
        Personne pers;
        UUID uniqueId = UUID.randomUUID();
        String idPers=""+uniqueId;
        
        Calendar birthDate = Calendar.getInstance();
        if(birth!=null)
            birthDate.setTime(birth);
        
        
        
        
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
        
        
        pers.setBox(box);
        pers.setId(idPers);
        pers.setLoc(loc);
        pers.setTotem(totem);
        dba.addPersonne(pers);
        
        return pers;
        
    }
    
    public ArrayList<LegalResp>  getLegal()
    {
        PersonneDBAccess dba = new PersonneDBAccess();
        
        
            try{
                return dba.getLegal();
            }
            catch(Exception e)
            {
               return null; 
            }
        
        
    }
    
    public void modPers(Personne p){
        PersonneDBAccess dba = new PersonneDBAccess();
        
        try{
            dba.modPers(p);
        }
        catch(Exception e){
            
        }
    }
    
    public ArrayList<Personne> getPers(String type,String name,String fiName, Localite loc){
        PersonneDBAccess dba = new PersonneDBAccess();
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
