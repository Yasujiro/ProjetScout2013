/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JRadioButton;
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
        columnsNames.add("Téléphone");
        columnsNames.add("E-mail");
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
                return selectedReg.getCrea().get(Calendar.DAY_OF_MONTH)+"/"+selectedReg.getCrea().get(Calendar.MONTH)
                        +"/"+selectedReg.getCrea().get(Calendar.YEAR);
                        
            case 4:
                return  pers.getName()+ pers.getFiName();
            case 5:
                if(pers.getLegal()!=null)
                    return pers.getLegal().getName() + pers.getLegal().getFiName();
                else
                    return null;
                
            case 6:
                return pers.getStreet()+" "+pers.getHouse()+" "+pers.getBox()+", "+ pers.getLoc().getPCode() +" "+ pers.getLoc().getLib();
                
            case 7:
                if(pers.getLegal() != null)
                    return pers.getLegal().getTel();
                else
                    return pers.getTel();
            case 8:
                if(pers.getLegal()!=null)
                    return pers.getLegal().getMail();
                else
                    return pers.getMail();
            case 9:
                JRadioButton butt = new JRadioButton();
                butt.setSelected(selectedReg.getColis());
                butt.setVisible(true);
                return null;
            default :
                return null;
        }
        
    }
    
    
}
