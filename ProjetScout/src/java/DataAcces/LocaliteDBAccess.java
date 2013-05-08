/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.SearchDataException;
import Interface.LocaliteDataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Localite;


public class LocaliteDBAccess implements LocaliteDataAccess {
    
    public ArrayList<Localite> getLocalite(Integer pCode) throws SearchDataException
    {
        ArrayList<Localite> searchLoca = new ArrayList<Localite>();
        try{
            String instructionSearchLoca = null;
            Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
            if(pCode != null)
            {
                instructionSearchLoca = "SELECT POSTALCODE, LIBELLE FROM LOCALITES where POSTALCODE = ?  ";

                PreparedStatement  prepStat = BDConnection.prepareStatement(instructionSearchLoca);
                prepStat.setInt(1, pCode);
                ResultSet data = prepStat.executeQuery();

                while(data.next())
                {
                    String libelle = data.getString("LIBELLE");
                    Integer postalCode = data.getInt("POSTALCODE");

                    Localite loc = new Localite (libelle, postalCode);
                    searchLoca.add(loc);


                }
            }
        
        }
        catch(SQLException e)
        {
            
        }
    
        
        return searchLoca;
    }
   

}
