/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.AddDataException;
import Exception.ConnectionException;
import Exception.ModDataException;
import Exception.SearchDataException;
import Interface.PersonneDataAccess;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Anime;
import model.Chief;
import model.LegalResp;
import model.Localite;

import model.Personne;


public class PersonneDBAccess implements PersonneDataAccess {
    
    
    public void addPersonne(Personne p) throws AddDataException
    {
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
            String instructionAddPers;
            String instructionUpdate;
            Date birthDate=null;
            if(p.getBirth()!=null)
              birthDate = new Date(p.getBirth().getTimeInMillis());
            
            
           
            instructionAddPers = "INSERT INTO PERSONNE(NUMID,NOM,PRENOM,TYPEPERS,POSTALCODELOC,LIBELLELOC,"
                    + "RUE,NUM,GSM,EMAIL,DATENAISSANCE,NUMBOITE,IDRESP,TOTEM) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepStat = BDConnection.prepareStatement(instructionAddPers);
            
            
            prepStat.setString(1, p.getId());
            prepStat.setString(2,p.getName());
            prepStat.setString(3,p.getFiName());
            prepStat.setString(4,p.getType());
            prepStat.setInt(5,p.getLoc().getPCode());
            prepStat.setString(6,p.getLoc().getLib());
            prepStat.setString(7,p.getStreet());
            prepStat.setString(8,p.getHouse());
            prepStat.setString(9,p.getTel());
            prepStat.setString(10,p.getMail());
            prepStat.setDate(11,birthDate);
            prepStat.setString(12,p.getBox());
            if(p.getLegal()!=null)
                prepStat.setString(13,p.getLegal().getId());
            else
                prepStat.setString(13,null);
            prepStat.setString(14,p.getTotem());
            
                    
            prepStat.executeUpdate();
            
            
           
            
        }
       catch(ConnectionException e){
           throw new AddDataException(e.toString());
       }
        catch(SQLException e){
            throw new AddDataException(e.toString());
        }
        
    }
    public ArrayList<Personne> getPers(Personne p) throws SearchDataException{
        ArrayList<Personne> listPers = new ArrayList<Personne>();
        Personne pFound;
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
            String searchInstruct;
            
            
                searchInstruct = "SELECT * FROM PERSONNE p "
                        +"where p.TYPEPERS like ? and p.NOM like ? and p.PRENOM like ? "
                        + "and p.LIBELLELOC like ?";
                        
                if(p.getLoc().getPCode()== 9999)
                    searchInstruct += " and p.POSTALCODELOC < ?";
                else
                    searchInstruct += " and p.POSTALCODELOC = ?";
                searchInstruct+= "order by typepers asc, nom asc,prenom asc";
                PreparedStatement prepStat= BDConnection.prepareStatement(searchInstruct);
                
                if(p.getType().equals("Chef")||p.getType().equals("Animé")||p.getType().equals("Responsable légal"))
                    prepStat.setString(1,p.getType());
                else
                    prepStat.setString(1,"%");
                prepStat.setString(2,p.getName());
                prepStat.setString(3,p.getFiName());
                prepStat.setString(4,p.getLoc().getLib());
                prepStat.setInt(5,p.getLoc().getPCode());
                ResultSet data = prepStat.executeQuery();
                
                while(data.next()){
                     
                    //Création d'un objet en fonction du type
                    if(data.getString("TYPEPERS").equals("Chef"))
                        pFound = new Chief(data.getString("NUMID"));
                    else if (data.getString("TYPEPERS").equals("Animé"))
                        pFound = new Anime(data.getString("NUMID"));
                    else if (data.getString("TYPEPERS").equals("Responsable légal"))
                        pFound = new LegalResp(data.getString("NUMID"));
                    else
                        pFound = new Personne(data.getString("NUMID"));
                    
                    // Récupération des donnée relatives à la personne;
                    
                    pFound.setName(data.getString("NOM"));
                    pFound.setFiName(data.getString("PRENOM"));
                    pFound.setTel(data.getString("GSM"));
                    pFound.setMail(data.getString("EMAIL"));
                    pFound.setStreet(data.getString("RUE"));
                    pFound.setHouse(data.getString("NUM"));
                    pFound.setBox(data.getString("NUMBOITE"));
                    String libLoc = data.getString("LIBELLELOC");
                    Integer pCode = data.getInt("POSTALCODELOC");
                    Localite loc = new Localite (libLoc, pCode);
                    pFound.setLoc(loc);
                    
                   if(data.getString("IDRESP")!=null){
                        String schLegal = "SELECT * from Personne legal join PERSONNE p on (legal.numID = p.IDRESP)";
                        PreparedStatement prepStatOpt = BDConnection.prepareStatement(schLegal);
                        ResultSet dataOpt = prepStatOpt.executeQuery();
                        while (dataOpt.next()){
                            LegalResp legal = new LegalResp(dataOpt.getString("NUMID"));
                            legal.setName(dataOpt.getString("NOM"));
                            legal.setFiName(dataOpt.getString("PRENOM"));
                            pFound.setLegalPers(legal);   
                            }
                                
                    }
                    listPers.add(pFound);
                }
                
        }
        catch(ConnectionException e){
            throw new SearchDataException(e.toString());
        }
        catch(SQLException e){
            throw new SearchDataException(e.toString());
        }
        
        finally{
            return listPers;
        }
       
    }
    public ArrayList<LegalResp> getLegal() throws SearchDataException
    {
        ArrayList<LegalResp> listLegal = new ArrayList<LegalResp>();
        try{
        
        String searchLegalInstruction;
        String name,fiName,street,num,tel,mail,id;
        Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
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
        catch(ConnectionException e)
        {
            throw new SearchDataException(e.toString());
        }
        catch(SQLException e){
            throw new SearchDataException(e.toString());
        }
        finally {
            return listLegal;
        }
    }
    
   public void modPers(Personne p)throws ModDataException{
       Date birthDate=null;
       try{
           Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
           String updateInstruction = "UPDATE PERSONNE SET NOM = ?, PRENOM = ?, DATENAISSANCE = ?, POSTALCODELOC = ?, "
                   + "LIBELLELOC = ?, RUE = ? , NUM = ? , NUMBOITE = ?, GSM = ?,EMAIL = ?  "
                   + "where NUMID = ? ";
           PreparedStatement prepStat = BDConnection.prepareStatement(updateInstruction);
           if(p.getBirth()!=null)
                birthDate = new Date(p.getBirth().getTimeInMillis());
           prepStat.setString(1,p.getName());
           prepStat.setString(2,p.getFiName());
           prepStat.setDate(3,birthDate);
           prepStat.setInt(4,p.getLoc().getPCode());
           prepStat.setString(5,p.getLoc().getLib());
           prepStat.setString(6,p.getStreet());
           prepStat.setString(7,p.getHouse());           
           prepStat.setString(8,p.getBox());
           prepStat.setString(9, p.getId());
           prepStat.setString(10,p.getTel());
           prepStat.setString(11,p.getMail());
           
           prepStat.executeUpdate();
           
       }
       catch(ConnectionException e){
           throw new ModDataException(e.toString());
       }
       catch(SQLException e){
           throw new ModDataException(e.toString());
       }
   }
   public int countPers(String id) throws SearchDataException
   {
       try{
           int nbPers=0;
           Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
           String countInstruct = "SELECT COUNT(*) FROM PERSONNE legal " +
                                  "join PERSONNE p on (p.idresp = legal.numID) " +
                                  "where legal.numid = ?";
           PreparedStatement prepStat = BDConnection.prepareStatement(countInstruct);
           prepStat.setString(1, id);
           ResultSet data = prepStat.executeQuery();
           while(data.next()){
               nbPers = data.getInt(1);
           }
           return nbPers;
       }
       catch(ConnectionException e){
           throw new SearchDataException(e.toString());
       }
       catch(SQLException e) {
           throw new SearchDataException(e.toString());
       }
   }
    
  
}
