/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.UnitDBAccess;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Unit;


public class UnitManager {
    
    private UnitDBAccess dba = new UnitDBAccess();
    private String nameUnit =null;
    private String libLoca;
    
    public ArrayList<Unit> getUnits() throws Exception
    {
        return dba.getUnits();
    }

    public ArrayList<Unit> getUnits(String name, Integer postalCode, String libLoc) {
                   
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
