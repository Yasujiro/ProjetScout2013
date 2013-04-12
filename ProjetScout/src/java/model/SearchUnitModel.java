/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jérémy
 */
public class SearchUnitModel extends AbstractTableModel{

    private ArrayList<String> columnNames = new ArrayList<String>();
    private ArrayList<Unit> content = new ArrayList<Unit>();
    
    public SearchUnitModel(ArrayList<Unit> listUnit)
    {
        content = listUnit;
        columnNames.add("Libelle");
        columnNames.add("Code postal");
        columnNames.add("Localité");
        columnNames.add("Nombre d'inscrits");
        
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
                return selectedUnit.getNb();
            default :
                return null;
        }
    }
    
}
