/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;


public class SearchUnitModel extends AbstractTableModel{

    private ArrayList<String> columnNames = new ArrayList<String>();
    private ArrayList<Unit> content = new ArrayList<Unit>();
    
    public SearchUnitModel(ArrayList<Unit> listUnit)
    {
        content = listUnit;
        columnNames.add("Libelle");
        columnNames.add("Code postal");
        columnNames.add("Localité");
        columnNames.add("Nombre d'animés");
        columnNames.add("Nombre de chefs");
        columnNames.add("Nombre de membres total");
        
    }
    
    @Override
    public int getRowCount() {
        return content.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    
    public String getColumnName(int col) { return columnNames.get(col); }

    @Override
    public Object getValueAt(int row, int col) {
        
        Unit selectedUnit = content.get(row);
        
        switch(col)
        {
            case 0:
                return selectedUnit.getLib();
            case 1:
                return selectedUnit.getLoc().getPCode();
            case 2:
                return selectedUnit.getLoc().getLib();
            case 3:
                return selectedUnit.getNbScout();
            case 4:
                return selectedUnit.getNbChief();
            case 5:
                return selectedUnit.getNb();
            default :
                return null;
        }
    }
    public Class getColumnClass(int col) {
		Class c;
		switch(col) {
			case 0 : c = String.class;
				break;
			case 1 : c = Integer.class;
				break;
			case 2 : c = String.class;
				break;
			case 3 : c = Integer.class;
				break;
			case 4 : c = Integer.class;
				break;
			case 5 : c = Integer.class;
				break;
                        default:
                            c= String.class;
                            break;
		}
		return c;
	}
    
    public Unit getSelectedUnit(int row)
    {
        return content.get(row);
        
    }
    
}
