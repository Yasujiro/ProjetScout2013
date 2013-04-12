/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Unit;

/**
 *
 * @author Jérémy
 */
public class UnitDBAccess {
    
    
    public ArrayList<Unit> getUnits() throws Exception
    {
        ArrayList<Unit> unitList = new ArrayList();
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            
            String instructionSearchUnit = "SELECT LIBELLEUNIT FROM UNIT";
            PreparedStatement  prepStat = BDConnection.prepareStatement(instructionSearchUnit);
            ResultSet data = prepStat.executeQuery();
            
            while(data.next())
            {
                String libelle = data.getString("LIBELLEUNIT");
                
                Unit unit = new Unit(libelle);
                unitList.add(unit);
            }
        }
        catch(Exception e)
        {
            
        }
        return unitList;
    }

    
}
