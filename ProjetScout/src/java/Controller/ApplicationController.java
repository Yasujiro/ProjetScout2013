/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogic.LocaManager;
import BusinessLogic.UnitManager;
import java.util.ArrayList;

import model.Localite;
import model.Unit;

public class ApplicationController {
    private LocaManager lm = new LocaManager();
    private UnitManager um = new UnitManager();

    
    public ArrayList<Localite> getLocalite(Integer pCode) throws Exception //Exception a créer;
    {
        return lm.getLocalite(pCode);
    }
    
    public ArrayList<Unit> getUnits() throws Exception
    {
        return um.getUnits();
    }
}



