/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import model.Anime;
import model.Chief;
import model.Localite;
import model.Personne;
import model.Registration;

// JOINTURE A REVOIR EN SQL;
public class RegistrationDBAccess {
    
    public ArrayList<Registration> getReg(Registration r)
    {
        ArrayList<Registration> regList = new ArrayList<Registration>();
        String libUnit, libSect,id,etat;
        String idPers,tel=null,mail=null,idLegal,nameLegal=null,fiNameLegal=null;
        Personne pers = r.getPers(),p;
        
        Boolean colis;
        Date creation,creaDate = new Date(r.getCrea().getTimeInMillis());
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            
            PreparedStatement prepStat =null; 
            
                String schInstruction ="Select dem.*,p.*,loc.* "
                        + "from DEMANDEINSCRIPT dem "                        
                        + "join PERSONNE p on (dem.NUMID = p.NUMID )" 
                        + "join LOCALITES loc on(loc.LIBELLE = p.LIBELLELOC and loc.POSTALCODE= p.POSTALCODELOC) "
                        + "and  dem.ETAT = ? and dem.ENVOISCOLIS = ? and dem.DATECREA = ? and dem.LIBELLEUNITE like ? and dem.LIBELLESECTION like ? "
                        +"and p.NOM like ? and p.PRENOM like ?" ;
                prepStat = BDConnection.prepareStatement(schInstruction);
                prepStat.setString(1,r.getState());
                prepStat.setBoolean(2, r.getColis());
                prepStat.setDate(3,creaDate);
                prepStat.setString(4,r.getSect().getUnit().getLib());
                prepStat.setString(5,r.getSect().getLib());
                prepStat.setString(6,r.getPers().getName());
                prepStat.setString(7,r.getPers().getFiName());
                
            
            
              ResultSet data = prepStat.executeQuery();
            
            
            while(data.next())
            {
                
                
                // Information de la table DEMANDEINSCRIPT
                libUnit = data.getString("LIBELLEUNITE");
                libSect = data.getString("LIBELLESECTION");
                id = data.getString("IDDEM");
                etat = data.getString("ETAT");
                colis = data.getBoolean("ENVOISCOLIS");
                creation = data.getDate("DATECREA");
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
                    throw new Exception();
                
                 if(!idLegal.equals("")){
                     // Si la valeur IDRESP est garni (donc scout mineur) on récupère le nom,prénom,tel et mail du RL.
                    idLegal = data.getString("IDRESP");                
                    Personne legalResp = new Personne(idLegal);                     
                    p.setLegalPers(legalResp);
                    
                    String schLegalInstruction = "SELECT * from PERSONNE legal "
                            + "where legal.NUMID=? ";
                    prepStat = BDConnection.prepareStatement(schLegalInstruction);
                    prepStat.setString(1,idLegal);
                    ResultSet dataOpt = prepStat.executeQuery();
                    
                    while(dataOpt.next())
                    {
                        
                        nameLegal = dataOpt.getString("NOM");
                        fiNameLegal = dataOpt.getString("PRENOM");
                        
                        p.setTel(dataOpt.getString("GSM"));
                        p.setMail(dataOpt.getString("EMAIL"));
                    }
                    
                    legalResp.setName(nameLegal);
                    legalResp.setFiName(fiNameLegal);
                    
                    
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
                p.setBox (data.getString("NUMBOITE"));
                p.setBirth(data.getDate("DATENAISSANCE"));
                
                
                String libLoc = data.getString("LIBELLE");
                Integer pCode = data.getInt("POSTALCODE");                
                Localite loc = new Localite(libLoc,pCode);
                p.setLoc(loc);
                
                
                Registration reg = new Registration(libUnit,libSect,p);                
                reg.setId(id);
                reg.setState(etat);
                reg.setColis(colis);
                reg.setCrea(creaDate);
                regList.add(reg);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access DEMANDE"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
        
        return regList;
    }
    
    public void addRegistration(Registration reg)
    {
        try{
                Connection BDConnection = SingletonConnection.getUniqueInstance();
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
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access DEMANDE"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void modRegistration(Registration reg)
    {
        try{
             Connection BDConnection = SingletonConnection.getUniqueInstance();
             Date modDate = new Date(Calendar.getInstance().getTimeInMillis());
             
             String updateInstruction = "update DEMANDEINSCRIPT set LIBELLEUNITE = ?, LIBELLESECTION = ?,ETAT = ?, ENVOISCOLIS = ?, DATEMODIF = ?"
                     + "where IDDEM = ? ";
             PreparedStatement prepStat = BDConnection.prepareStatement(updateInstruction);
             prepStat.setString(1, reg.getSect().getUnit().getLib());
             prepStat.setString(2,reg.getSect().getLib());
             prepStat.setString(3,reg.getState());
             prepStat.setBoolean(4,reg.getColis());
             prepStat.setDate(5, modDate);
             prepStat.setString(6,reg.getId());
             
             prepStat.executeUpdate();
             
             
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR Data Access DEMANDE"+e.toString(),"error",JOptionPane.PLAIN_MESSAGE);
        }
        
    }
    
}
