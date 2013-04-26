/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.ConnectionException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Anime;
import model.LegalResp;

import model.Personne;


public class PersonneDBAccess {
    
    
    public void addPersonne(Personne p)
    {
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            String instructionAddPers;
            String instructionUpdate;
            
            
           
            instructionAddPers = "INSERT INTO PERSONNE(NUMID,NOM,PRENOM,TYPEPERS,POSTALCODELOC,LIBELLELOC,RUE,NUM)VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement prepStat = BDConnection.prepareStatement(instructionAddPers);
            
            
            prepStat.setString(1, p.getId());
            prepStat.setString(2,p.getName());
            prepStat.setString(3,p.getFiName());
            prepStat.setString(4,p.getType());            
            prepStat.setInt(5,p.getLoc().getPCode());
            prepStat.setString(6,p.getLoc().getLib());
            prepStat.setString(7,p.getStreet());
            prepStat.setString(8,p.getHouse());          
            
            prepStat.executeUpdate();
            
            if(p.getBox()!=null)
            {
                instructionUpdate = "update Personne set NUMBOITE = ? where numId ='"+p.getId()+"'";
                prepStat = BDConnection.prepareStatement(instructionUpdate);
                prepStat.setString(1,p.getBox());
                prepStat.executeUpdate();
            }
            
            
            if(p.getType().equals("Animé"))
            {   
                Date birthDate = new Date(p.getBirth().getTimeInMillis());
                JOptionPane.showMessageDialog(null, "ERREUR Data Access PERSONNE" + p.getLegal().getId(),"error",JOptionPane.PLAIN_MESSAGE);
                instructionUpdate = "update PERSONNE set IDRESP = ?,DATENAISSANCE = ?  where numId = '"+p.getId()+"'";
                prepStat = BDConnection.prepareStatement(instructionUpdate);
                
                prepStat.setString(1,p.getLegal().getId());
                prepStat.setDate(2, birthDate);
                prepStat.executeUpdate();
            }
            else
            {
                instructionUpdate = "update PERSONNE set GSM = ?, EMAIL = ? where numID ='"+p.getId()+"'";
                prepStat = BDConnection.prepareStatement(instructionUpdate);
                
                prepStat.setString(1,p.getTel());
                prepStat.setString(2,p.getMail());
                prepStat.executeUpdate();
                
                if(p.getType().equals("Chef"))
                {
                    Date birthDate = new Date(p.getBirth().getTimeInMillis());
                    instructionUpdate = "update PERSONNE set DATENAISSANCE = ? where numId = '"+p.getId()+"'";
                    prepStat = BDConnection.prepareStatement(instructionUpdate);
                    
                    prepStat.setDate(1,birthDate);
                    prepStat.executeUpdate();
                }
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access PERSONNE"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
        
        
    }
    public ArrayList<LegalResp> getLegal()
    {
        ArrayList<LegalResp> listLegal = new ArrayList<LegalResp>();
        try{
        
        String searchLegalInstruction;
        String name,fiName,street,num,tel,mail,id;
        Connection BDConnection = SingletonConnection.getUniqueInstance();
        searchLegalInstruction = "SELECT * FROM PERSONNE where TYPEPERS = ? ORDER BY NOM ASC";
        
        PreparedStatement prepStat = BDConnection.prepareStatement(searchLegalInstruction);
        
        prepStat.setString(1,"Responsable légal");
        ResultSet data = prepStat.executeQuery();
        
        while(data.next())
        {
            name = data.getString("NOM");
            fiName = data.getString("PRENOM");
            street = data.getString("RUE");
            num = data.getString("NUM");
            tel = data.getString("GSM");
            mail = data.getString("EMAIL");
            id = data.getString("NUMID");
            
            LegalResp p = new LegalResp(name,fiName,street,num,tel,mail);
            p.setId(id);
            listLegal.add(p);
        }
        
            
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access PERSONNE"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
        finally {
            return listLegal;
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
