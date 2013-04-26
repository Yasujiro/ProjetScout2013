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
    
    
    public Personne addPersonne(String type,String name,String fiName, Date birth,Personne legalResp,String street,String num,String box,Localite loc,String tel,String mail)
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
        {            
            pers=null;
            
        }
        if(!box.equals(""))
        {
            pers.setBox(box);
        }
        pers.setId(idPers);
        pers.setLoc(loc);
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
}
