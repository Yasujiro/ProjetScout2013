/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.AddDataException;
import Exception.ConnectionException;
import Exception.ModDataException;
import Exception.SearchDataException;
import Interface.RegistrationDataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import model.Anime;
import model.Chief;
import model.Localite;
import model.Personne;
import model.Registration;

public class RegistrationDBAccess implements RegistrationDataAccess {
    
    public ArrayList<Registration> getReg(Registration r) throws SearchDataException
    {
        ArrayList<Registration> regList = new ArrayList<Registration>();
        String libUnit, libSect,id,etat;
        String idPers,idLegal;
        Personne p;
        
        Boolean colis;
        
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
            String schInstruction ="Select * "
                        + "from DEMANDEINSCRIPT dem "                        
                        + "join PERSONNE p on (dem.NUMID = p.NUMID )" 
                        + "join LOCALITES loc on(loc.LIBELLE = p.LIBELLELOC and loc.POSTALCODE= p.POSTALCODELOC) ";
            PreparedStatement prepStat =null; 
            if(r !=null){
                Date creaDate = new Date(r.getCrea().getTimeInMillis());
                schInstruction += "and  dem.ETAT = ? and dem.ENVOISCOLIS = ? and dem.DATECREA = ? and "
                                + "dem.LIBELLEUNITE like ? and dem.LIBELLESECTION like ? "
                                + "and p.NOM like ? and p.PRENOM like ?"
                                + "ORDER BY LIBELLEUNITE asc, LIBELLESECTIOn asc " ;
                prepStat = BDConnection.prepareStatement(schInstruction);
                prepStat.setString(1,r.getState());
                prepStat.setBoolean(2, r.getColis());
                prepStat.setDate(3,creaDate);
                prepStat.setString(4,r.getSect().getUnit().getLib());
                prepStat.setString(5,r.getSect().getLib());
                prepStat.setString(6,r.getPers().getName());
                prepStat.setString(7,r.getPers().getFiName());
            }
            else{
                schInstruction += "ORDER BY LIBELLEUNITE asc, LIBELLESECTIOn asc " ;
                prepStat = BDConnection.prepareStatement(schInstruction);
                  }
                
            
            
              ResultSet data = prepStat.executeQuery();
            
            
            while(data.next()){
                
                
                // Information de la table DEMANDEINSCRIPT
                libUnit = data.getString("LIBELLEUNITE");
                libSect = data.getString("LIBELLESECTION");
                id = data.getString("IDDEM");
                etat = data.getString("ETAT");
                colis = data.getBoolean("ENVOISCOLIS");                
                idPers = data.getString("NUMID");
                
                if(data.getString("IDRESP")==null)
                    idLegal="";
                else
                    idLegal =data.getString("IDRESP");
                
                
                if(data.getString("TYPEPERS").equals("Animé"))
                    p = new Anime(idPers);
                else if (data.getString("TYPEPERS").equals("Chef"))
                    p = new Chief(idPers);
                else
                    p = new Personne(idPers);
                
                
                
                
                 if(!idLegal.equals("")){
                     
                     // Si la valeur IDRESP est garni (donc scout mineur) on récupère les info du RL.
                    idLegal = data.getString("IDRESP");  
                    // Création et "garniture" de l'objet "Responsable Légal
                    Personne legalResp = new Personne(idLegal);                     
                    p.setLegalPers(legalResp);
                    
                    String schLegalInstruction = "SELECT * from PERSONNE legal "
                            + "where legal.NUMID=? ";
                    prepStat = BDConnection.prepareStatement(schLegalInstruction);
                    prepStat.setString(1,idLegal);
                    ResultSet dataOpt = prepStat.executeQuery();
                    
                    while(dataOpt.next())
                    {
                        // Information relatif au responsable légal
                        
                        String libLoc = dataOpt.getString("LIBELLELOC");
                        Integer pCode = dataOpt.getInt("POSTALCODELOC");
                        legalResp.setStreet(dataOpt.getString("RUE"));
                        legalResp.setHouse(dataOpt.getString("NUM"));
                        legalResp.setBox(dataOpt.getString("NUMBOITE"));
                        legalResp.setTel(dataOpt.getString("GSM"));
                        legalResp.setMail(dataOpt.getString("EMAIL"));
                        legalResp.setName(dataOpt.getString("NOM"));
                        legalResp.setFiName(dataOpt.getString("PRENOM"));
                        Localite loc = new Localite (libLoc,pCode);
                        legalResp.setLoc(loc);
                        
                    }  
                }
                 else{
                     //Sinon on récupère le Tel et le mail de la personne "initiales"
                       p.setTel(data.getString("GSM"));
                       p.setMail(data.getString("EMAIL"));
                 }
                                
                // Information sur le demandeur
                p.setName(data.getString("NOM"));                
                p.setFiName(data.getString("PRENOM"));                 
                p.setStreet(data.getString("RUE"));                
                p.setHouse(data.getString("NUM")); 
                if(data.getString("NUMBOITE")!=null)
                    p.setBox (data.getString("NUMBOITE"));
                else
                    p.setBox("");
                p.setBirth(data.getDate("DATENAISSANCE"));
                p.setTotem(data.getString("TOTEM"));
                
                
                String libLoc = data.getString("LIBELLE");
                Integer pCode = data.getInt("POSTALCODE");                
                Localite loc = new Localite(libLoc,pCode);
                p.setLoc(loc);
                
                
                Registration reg = new Registration(libUnit,libSect,p);                
                reg.setId(id);
                reg.setState(etat);
                reg.setColis(colis);
                reg.setCrea(data.getDate("DATECREA"));
                reg.setPrice(data.getInt("PRIX"));
                regList.add(reg);
            }
        }
        catch(ConnectionException e){
            throw new SearchDataException(e.toString());
        }
        catch(SQLException e){
            throw new SearchDataException(e.toString());
        }
        return regList;
    }
    
    public void addRegistration(Registration reg) throws AddDataException
    {
        try{
                Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
                Date crea = new Date(reg.getCrea().getTimeInMillis());
                Date modification = new Date(reg.getLastModif().getTimeInMillis());
                String instructionInsertReg = "INSERT INTO DEMANDEINSCRIPT (IDDEM,NUMID,ETAT,DATECREA,DATEMODIF,ENVOISCOLIS,PRIX,LIBELLESECTION,LIBELLEUnite)"
                        +"values(?,?,?,?,?,?,?,?,?) ";
                PreparedStatement prepStat = BDConnection.prepareStatement(instructionInsertReg);
                
                prepStat.setString(1, reg.getId());
                prepStat.setString(2, reg.getPers().getId());
                prepStat.setString(3, reg.getState());
                prepStat.setDate(4,crea);
                prepStat.setDate(5,modification);
                prepStat.setBoolean(6,reg.getColis());
                prepStat.setInt(7, reg.getPrice());
                prepStat.setString(8, reg.getSect().getLib());
                prepStat.setString(9,reg.getSect().getUnit().getLib());
                
                prepStat.executeUpdate();
                
                
                
        }
        catch(ConnectionException e)
        {
            throw new AddDataException(e.toString());
        }
        catch(SQLException e){
            throw new AddDataException(e.toString());
        }
    }
    public void modRegistration(Registration reg) throws ModDataException
    {
        try{
             Connection BDConnection = SingletonConnection.getUniqueInstance(null,null);
             Date modDate = new Date(Calendar.getInstance().getTimeInMillis());
             
             String updateInstruction = "update DEMANDEINSCRIPT set LIBELLEUNITE = ?, LIBELLESECTION = ?,ETAT = ?, ENVOISCOLIS = ?, DATEMODIF = ?"
                     + "where IDDEM = ? ";
             PreparedStatement prepStat = BDConnection.prepareStatement(updateInstruction);
             prepStat.setString(1, reg.getSect().getUnit().getLib());
             prepStat.setString(2,reg.getSect().getLib());
             prepStat.setString(3,reg.getState());
             prepStat.setBoolean(4,reg.getColis());
             prepStat.setDate(5, modDate);
             prepStat.setString(7,reg.getId());
             
             prepStat.executeUpdate();
             
             
        }
        catch(ConnectionException e)
        {
            throw new ModDataException(e.toString());
        }
        catch(SQLException e){
            throw new ModDataException(e.toString());
        }
        
        
    }
    
}
