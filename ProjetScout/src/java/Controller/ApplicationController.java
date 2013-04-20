/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogic.LocaManager;
import BusinessLogic.RegistrationManager;
import BusinessLogic.UnitManager;
import java.util.ArrayList;

import model.Localite;
import model.Personne;
import model.Unit;

public class ApplicationController {
    private LocaManager lm = new LocaManager();
    private UnitManager um = new UnitManager();
    private RegistrationManager rm = new RegistrationManager();

    
    public ArrayList<Localite> getLocalite(Integer pCode) throws Exception //Exception a créer;
    {
        return lm.getLocalite(pCode);
    }
    
    public ArrayList<Unit> getUnits() throws Exception
    {
        return um.getUnits();
    }
    public ArrayList<Unit> getUnits(String name, Integer postalCode, String libLoc) throws Exception
    {
        return um.getUnits(name,postalCode,libLoc);
    }

    public void addRegistration(String unit,String sect,Personne pers) {
        rm.addRegistration(unit,sect,pers);
        
    }
}



