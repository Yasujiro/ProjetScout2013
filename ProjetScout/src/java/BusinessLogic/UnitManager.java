/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.UnitDBAccess;
import Exception.SearchDataException;
import Interface.UnitDataAccess;
import java.util.ArrayList;
import model.Unit;


public class UnitManager {
    
    private UnitDataAccess dba;
    private String nameUnit =null;
    private String libLoca;
    
    public UnitManager(){
        dba = new UnitDBAccess();
    }
    
    public ArrayList<Unit> getUnits() throws SearchDataException
    {
        return dba.getUnits();
    }

    public ArrayList<Unit> getUnits(String name, Integer postalCode, String libLoc) throws SearchDataException {
                   
       nameUnit = "%"+name+"%";
       if(libLoc.equals("Sélectionner une localité"))
       {
           libLoca="";
       }
       else
           libLoca=libLoc;
       
        
       return dba.getUnits(nameUnit,postalCode,libLoca);
        
    }
}
