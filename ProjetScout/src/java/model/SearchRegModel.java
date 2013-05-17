/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.AbstractTableModel;


public class SearchRegModel extends AbstractTableModel {

    private ArrayList<Registration> content;
    private ArrayList<String> columnsNames =new ArrayList<String>();
    
    public SearchRegModel(ArrayList<Registration> c)
    {
        content = c;
        columnsNames.add("Unité");
        columnsNames.add("Section");
        columnsNames.add("Etat");
        columnsNames.add("Création");
        columnsNames.add("Demandeur");
        columnsNames.add("Responsable");
        columnsNames.add("Adresse");
        columnsNames.add("Colis");
        
        
    }
    
    @Override
    public int getRowCount() {
        return content.size();
    }

    @Override
    public int getColumnCount() {
        return columnsNames.size();
    }
    public String getColumnName(int col) { return columnsNames.get(col); };

    @Override
    public Object getValueAt(int row, int col) {
        Registration selectedReg = content.get(row);
        String returnedValue="";
        Personne pers = selectedReg.getPers();
        switch(col)
        {
            case 0:
                return selectedReg.getSect().getUnit().getLib();
            case 1 :
                return selectedReg.getSect().getLib();
            case 2:
                return selectedReg.getState();
            case 3:
                return new Date(selectedReg.getCrea().getTimeInMillis());
                        
            case 4:
                return  pers.getName()+" "+ pers.getFiName();
            case 5:
                if(pers.getLegal()!=null)
                    return pers.getLegal().getName()+" " + pers.getLegal().getFiName();
                else
                    return null;
                
            case 6:
                if(pers.getBox()!= null)
                    return pers.getStreet()+" "+pers.getHouse()+" "+pers.getBox()+", "+ pers.getLoc().getPCode() +" "+ pers.getLoc().getLib();
                else
                    return pers.getStreet()+" "+pers.getHouse()+", "+ pers.getLoc().getPCode() +" "+ pers.getLoc().getLib();
            case 7:
                if(selectedReg.getColis())
                  return "Envoyé";
                else
                    return "En attente";
            default :
                return null;
        }
        
    }
    public Class getColumnClass(int col) {
		Class c;
		switch(col) {
			case 0 : c = String.class;
				break;
			case 1 : c = String.class;
				break;
			case 2 : c = String.class;
				break;
			case 3 : c = Date.class;
				break;
			case 4 : c = String.class;
				break;
			case 5 : c = String.class;
				break;
			case 6 : c = String.class;
				break;
			case 7 : c = String.class;
				break;
                        default:
                            c= String.class;
                            break;
		}
		return c;
	}
    
    public Registration getSelectedReg(int row)
    {
        return content.get(row);
    }
    
    
}
