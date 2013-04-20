/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.PersonneDBAccess;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import model.Anime;
import model.Chief;
import model.Localite;
import model.Personne;


public class PersonneManager {
    
    
    public Personne addPersonne(String type,String name,String fiName, Date birth,Personne legalResp,String street,String num,String box,Localite loc,String tel,String mail)
    {
        PersonneDBAccess dba = new PersonneDBAccess();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(birth);
        Personne pers;
        UUID uniqueId = UUID.randomUUID();
        String idPers=""+uniqueId;
        
        
        if(type.equals("Animé"))
        {
            pers = new Anime(name,fiName,street,num,birthDate,legalResp);
            
        }
        else if(type.equals("Chef"))
        {
            pers = new Chief(name,fiName,street,num,birthDate,tel,mail);
        }
        else
        {
            //throw new Exception.....
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
}
