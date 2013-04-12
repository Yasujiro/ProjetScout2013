/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.UnitDBAccess;
import java.util.ArrayList;
import model.Unit;


public class UnitManager {
    
    private UnitDBAccess dba = new UnitDBAccess();
    
    public ArrayList<Unit> getUnits() throws Exception
    {
        return dba.getUnits();
    }
}
