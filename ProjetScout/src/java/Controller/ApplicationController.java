/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogic.*;
import Exception.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LegalResp;

import model.*;


public class ApplicationController {
    private LogginManager logM = new LogginManager();
    private LocaManager lm = new LocaManager();
    private UnitManager um = new UnitManager();
    private RegistrationManager rm = new RegistrationManager();
    private PersonneManager pm = new PersonneManager();
    

    public Personne addPersonne(String type,String name,String fiName, Date birth,Personne legalPers,
            String street,String num,String box,Localite loc,String tel,String mail,String totem) throws AddDataException
    {
        return pm.addPersonne(type,name,fiName,birth,legalPers,street,num,box,loc,tel,mail,totem);
    }
    public ArrayList<LegalResp> getLegal() throws SearchDataException
    {
        return pm.getLegal();
        
    }
    
    public void modPers(Personne p)throws ModDataException{
        pm.modPers(p);
    }
    
    public ArrayList<Personne> getPers(String type,String name,String fiName, Localite loc) throws SearchDataException{
        return pm.getPers(type,name,fiName,loc);
    }
    
    public ArrayList<Localite> getLocalite(Integer pCode) throws SearchDataException 
    {
        return lm.getLocalite(pCode);
    }
    public ArrayList<Unit> getUnits() throws SearchDataException
    {
        return um.getUnits();
    }
    public ArrayList<Unit> getUnits(String name, Integer postalCode, String libLoc) throws SearchDataException
    {
        return um.getUnits(name,postalCode,libLoc);
    }
    
    public ArrayList<Registration> getReg(Registration reg) throws SearchDataException
    {
        return rm.getReg(reg);
    }

    public void addRegistration(String unit,String sect,Personne pers)throws AddDataException {
        rm.addRegistration(unit,sect,pers);
        
    }
    public void modRegistration(Registration reg) throws ModDataException
    {
        rm.modRegistration(reg);
    }
    public void Loggin(String user, String password) throws ConnectionException{
        logM.Loggin(user, password);
    }
    public void Disconnect() throws DisconnectException{
        logM.Disconnect();
    }
    public boolean getConnectionState(){
        return logM.getConnectionState();
    }
    public void DelReg(Registration reg)throws DeleteException{
        rm.DelReg(reg);
    }
    
    public void WriteLog(String message,Level lvl,Exception e){
        LoggerManager.WriteLog(message,lvl,e);
    }
}



