/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Localite;

/**
 *
 * @author Jérémy
 */
public class LocaliteDBAccess {
    
    public ArrayList<Localite> getLocalite(Integer pCode) throws Exception //Création d'exception spécial
    {
        ArrayList<Localite> searchLoca = new ArrayList<Localite>();
        try{
            String instructionSearchLoca = null;
            Connection BDConnection = SingletonConnection.getUniqueInstance();
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
   /* public Localite getLoc() throws Exception
    {
        Localite l =null;
        try{
           Connection BDConnection = SingletonConnection.getUniqueInstance();
            String requeteSQL = "select POSTALCODE, LIBELLE from LOCALITES where POSTALCODE = ? and LIBELLE = ?";
            
            
            PreparedStatement  prepStat = BDConnection.prepareStatement(requeteSQL);
            
            prepStat.setInt(1, 5000);
            prepStat.setString(2, "NAMUR");
            ResultSet data = prepStat.executeQuery();
            
            while(data.next()) {
            String libelle = data.getString("LIBELLE");
            Integer postalCode = data.getInt("POSTALCODE");
            l = new Localite(libelle,postalCode);
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
        return l;
    }*/

}
