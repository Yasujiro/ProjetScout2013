/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.ConnectionException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.Personne;


public class PersonneDBAccess {
    
    
    public void addPersonne(Personne p)
    {
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            String instructionAddPers=null;
            Date birthDate = new Date(p.getBirth().getTimeInMillis());
            
            instructionAddPers = "INSERT INTO PERSONNE(NUMID,NOM,PRENOM,TYPEPERS,DATENAISSANCE,POSTALCODELOC,LIBELLELOC,RUE,NUM)"
                        + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepStat = BDConnection.prepareStatement(instructionAddPers);
            
            prepStat.setString(1, p.getId());
            prepStat.setString(2,p.getName());
            prepStat.setString(3,p.getFiName());
            prepStat.setString(4,p.getType());            
            prepStat.setDate(5,birthDate);          
                        
            prepStat.setInt(6,p.getLoc().getPCode());
            prepStat.setString(7,p.getLoc().getLib());
            prepStat.setString(8,p.getStreet());
            prepStat.setString(9,p.getHouse());          
            
            prepStat.executeUpdate();
            
            if(!p.getBox().equals(""))
            {
                String instructionUpdate = "update Personne set NUMBOITE = ? where numId ='"+p.getId()+"'";
                prepStat = BDConnection.prepareStatement(instructionUpdate);
                prepStat.setString(1,p.getBox());
                prepStat.executeUpdate();
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
        
        
    }
    
    
   /* public ArrayList<LegalPers> getResponsable () throws ConnectionException
    {
        
        ArrayList<LegalPers> searchLegal = new ArrayList<LegalPers>();
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            
            String instructionSearchLegal = "";// Commande SQL a excecuter.
            PreparedStatement prepStat = BDConnection.prepareStatement(instructionSearchLegal);
            ResultSet data = prepStat.executeQuery();
            
            while(data.next())
            {
                // Récupération des données et/ou objet.
                
                // Création d'un objet LegalPers;
                // Ajout dans la liste du nouvel objet.
                
            }
        }
        catch(SQLException e)
        {
            // Liste d'erreur;
        }           
            
           return searchLegal;
        
    }*/
    
    
    
    
    
}
