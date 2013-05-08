/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jérémy
 */
public class Registration {
    
    private String state,id;
    private Calendar dateCrea,lastModif;
    private Integer price;
    private boolean colis;
    private Section section;
    private Personne membre;
    
    public Registration (String unit,String sect,Personne p)
    {
        section = new Section(sect,unit);
        dateCrea = Calendar.getInstance();
        lastModif = Calendar.getInstance();
        state ="En attente";
        price = 50;
        
        colis = false;
        
        membre = p;

    }
    
    public void setPrice(int p)
    {
        price = p;
    }
    public void setCrea(Date d)
    {
        dateCrea.setTime(d);
    }
    public void setModif(Calendar date)
    {
        lastModif = date;
    }
    public void setId(String uuid)
    {
        id = uuid;
    }

    public void setColis(boolean s)
    {
        colis =s;
    }
    public void setState(String updatedState)
    {
        state = updatedState;
    }
    public void setSect(String libSect, String libUnit)
    {
        section.setLib(libSect);
        section.getUnit().setLib(libUnit);
    }
    
    
    public int getPrice()
    {
        return price;
    }
    public Section getSect()
    {
        return section;
    }
    public Calendar getLastModif()
    {
        return this.lastModif;
    }
    public Calendar getCrea()
    {
        return this.dateCrea;
    }
    public String getState()
    {
        return state;
    }
    public String getId()
    {
        return this.id;
    }
    public Personne getPers()
    {
        return this.membre;
    }
    public Boolean getColis()
    {
        return this.colis;
    }
    
    
}
