/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Registration;


public class RegistrationDBAccess {
    
    public void addRegistration(Registration reg)
    {
        try{
                Connection BDConnection = SingletonConnection.getUniqueInstance();
                String instructionInsertReg = "INSERT INTO DEMANDEINSCRIPT values(?,?,?,?,?,?,?,?,?) ";
                PreparedStatement prepStat = BDConnection.prepareStatement(instructionInsertReg);
                
                prepStat.setString(1, reg.getId());
                prepStat.setString(2, reg.getPers().getId());
                prepStat.setString(3, reg.getState());
                prepStat.setLong(4,reg.getCrea().getTimeInMillis());
                prepStat.setLong(5,reg.getLastModif().getTimeInMillis());
                prepStat.setString(6,reg.getColis());
                prepStat.setInt(7, reg.getPrice());
                prepStat.setString(8, reg.getSect().getLib());
                prepStat.setString(9,reg.getSect().getUnit().getLib());
                
                
                
        }
        catch(Exception e)
        {
            
        }
    }
    
}
