
package DataAcces;

import Exception.*;
import Interface.*;
import java.sql.*;
import java.util.*;
import model.*;


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
                    //BDConnection.commit();
                    searchLoca.add(loc);


                }
            }
        
        }
        catch(ConnectionException e){
            throw new SearchDataException(e.getMessage());
        }
        catch(SQLException e)
        {
            throw new SearchDataException(e.getMessage());
        }
    
        
        return searchLoca;
    }
   

}
