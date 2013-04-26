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
import model.Localite;
import model.Personne;
import model.Registration;

// JOINTURE A REVOIR EN SQL;
public class RegistrationDBAccess {
    
    public ArrayList<Registration> getReg(Registration r)
    {
        ArrayList<Registration> regList = new ArrayList<Registration>();
        String libUnit, libSect,id,etat;
        String idPers,name,fiName,street,house,box,tel=null,mail=null,idLegal,nameLegal=null,fiNameLegal=null;
        Personne pers = r.getPers();
        
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
                idLegal="";
                
                libUnit = data.getString("LIBELLEUNITE");
                libSect = data.getString("LIBELLESECTION");
                id = data.getString("IDDEM");
                etat = data.getString("ETAT");
                colis = data.getBoolean("ENVOISCOLIS");
                creation = data.getDate("DATECREA");
                
                name = data.getString("NOM");
                fiName = data.getString("PRENOM");
                idPers = data.getString("NUMID");
                street = data.getString("RUE");
                house = data.getString("NUM");
                box = data.getString("NUMBOITE");
                if(data.getString("IDRESP")!=null)
                {
                    idLegal = data.getString("IDRESP");
                }
                
               
                
                String libLoc = data.getString("LIBELLE");
                Integer pCode = data.getInt("POSTALCODE");
                
                
                
                Localite loc = new Localite(libLoc,pCode);
                Personne p = new Personne(name,fiName,street,house,null);
                if(!(idLegal.equals("")))
                {
                    Personne legalResp = new Personne(idLegal);  
                    String schLegalInstruction = "SELECT legal.nom,legal.prenom from PERSONNE legal "
                            + "where legal.NUMID=? ";
                    prepStat = BDConnection.prepareStatement(schLegalInstruction);
                    prepStat.setString(1,idLegal);
                    ResultSet dataOpt = prepStat.executeQuery();
                    
                    while(dataOpt.next())
                    {
                        
                        nameLegal = dataOpt.getString("NOM");
                        fiNameLegal = dataOpt.getString("PRENOM");
                    }
                    
                    legalResp.setName(nameLegal);
                    legalResp.setFiName(fiNameLegal);
                    p.setLegalPers(legalResp);
                }
                
                p.setLoc(loc);
                p.setId(idPers);
                
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
