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
public class SchPersModel extends AbstractTableModel {
    
    private ArrayList<Personne> content;
    private ArrayList<String> columnNames;
    
    public SchPersModel(ArrayList<Personne> c){
        content = c;
        columnNames = new ArrayList<String>();
        columnNames.add("Type");
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Adresse");
        columnNames.add("Responsable");
    }

    @Override
    public int getRowCount() {
        return content.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    public String getColumnName(int col) { return columnNames.get(col); };

    @Override
    public Object getValueAt(int row, int col) {
       Personne selectedPers = content.get(row);
                
        switch(col)
        {
            case 0:
                return selectedPers.getType();
            case 1:
                return selectedPers.getName();
            case 2:
                return selectedPers.getFiName();
            case 3 :
                String addr;
                if(selectedPers.getBox()!=null)
                    addr = selectedPers.getStreet()+" "+selectedPers.getHouse()+" "+selectedPers.getBox()+ ", "
                        +selectedPers.getLoc().getPCode()+ " "+selectedPers.getLoc().getLib();
                else
                    addr = selectedPers.getStreet()+" "+selectedPers.getHouse()+ ", "
                        +selectedPers.getLoc().getPCode()+ " "+selectedPers.getLoc().getLib();
                return addr;
            case 4:
                if(selectedPers.getLegal()!=null)
                    return selectedPers.getLegal().getName()+" "+selectedPers.getLegal().getFiName();
                else
                    return "";
            default :
                return null;
    }
    
}
}
