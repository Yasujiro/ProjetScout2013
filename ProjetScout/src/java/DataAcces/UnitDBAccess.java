/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Localite;
import model.Unit;


public class UnitDBAccess {
    
    
    public ArrayList<Unit> getUnits() throws Exception
    {
        ArrayList<Unit> unitList = new ArrayList();
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            
            String instructionSearchUnit = "SELECT LIBELLEUNIT FROM UNIT";
            PreparedStatement  prepStat = BDConnection.prepareStatement(instructionSearchUnit);
            ResultSet data = prepStat.executeQuery();
            
            while(data.next())
            {
                String libelle = data.getString("LIBELLEUNIT");
                
                
                Unit unit = new Unit(libelle);
                unitList.add(unit);
            }
        }
        catch(Exception e)
        {
            
        }
        return unitList;
    }

    public ArrayList<Unit> getUnits(String name, Integer postalCode, String libLoc) {
        ArrayList<Unit> unitList = new ArrayList();
        String instructionSearchUnit=null;
        PreparedStatement prepStat;
        try{
            Connection BDConnection = SingletonConnection.getUniqueInstance();
            
            if(postalCode != null)
            {
                if(!libLoc.equals(""))
                {
                    instructionSearchUnit = "SELECT * from UNIT where LIBELLEUNIT like ? and POSTALCODELOCA = ? and LIBELLELOCA = ? ";
                    prepStat = BDConnection.prepareStatement(instructionSearchUnit);
                    prepStat.setString(1, name);
                    prepStat.setInt(2, postalCode);
                    prepStat.setString(3, libLoc);
                }
                else{
                    instructionSearchUnit = "SELECT * from UNIT where LIBELLEUNIT like ? and POSTALCODELOCA = ?";
                    prepStat = BDConnection.prepareStatement(instructionSearchUnit);
                    prepStat.setString(1, name);
                    prepStat.setInt(2, postalCode);
                    
                }
            }
            else
            {
                instructionSearchUnit = "SELECT * from UNIT where LIBELLEUNIT like ?";
                prepStat = BDConnection.prepareStatement(instructionSearchUnit);
                prepStat.setString(1, name);
            }            
            
            
            ResultSet data = prepStat.executeQuery();
            
            while(data.next())
            {
                String libelle  = data.getString("LIBELLEUNIT");
                Integer pCode   = data.getInt("POSTALCODELOCA");
                String libLoca   = data.getString("LIBELLELOCA");
                
                Unit unit = new Unit(libelle);
                Localite loc  = new Localite(libLoca,pCode);
                
                unit.setLoc(loc);
                unitList.add(unit);               
            }
            for(Unit var: unitList)
            {
                String countInstruction = "SELECT COUNT(*) from DEMANDEINSCRIPT dem,LIBSECT s,PERSONNE p "
                        +"where LIBELLESECTION = s.LIBELLESECT and s.LIBELLEUNIT = ? and dem.NUMID = p.NUMID and p.TYPEPERS = ?";
                
                /*select count(*)  from DEMANDeINSCRIPT dem,LIBSECT s,PERSONNE p 
where LIBELLESECTION = s.LIBELLESECT and s.LIBELLEUNIT = 'NM005 - Scouts et Guides Andenne' 
and dem.NUMID = p.NUMID and p.TYPEPERS = 'Animé' */
                
                prepStat = BDConnection.prepareStatement(countInstruction);
                prepStat.setString(1, var.getLib());
                prepStat.setString(2,"Animé");
                data = prepStat.executeQuery();
                
                
                while(data.next())
                {
                    var.setNbScout(data.getInt(1));
                }
                prepStat = BDConnection.prepareStatement(countInstruction);
                prepStat.setString(1, var.getLib());
                prepStat.setString(2, "Chef");
                data = prepStat.executeQuery();
                while(data.next())
                {
                    var.setNbChief(data.getInt(1));
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erreur - SQl "+e,"error",JOptionPane.ERROR_MESSAGE);
        }
        return unitList;
    }

    
}
