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
    
    private String state,id,colis;
    private Calendar dateCrea,lastModif;
    private int price;
    private Section section;
    private Personne membre;
    
    public Registration (String unit,String sect)
    {
        section = new Section(sect,unit);
        dateCrea = Calendar.getInstance();
        lastModif = Calendar.getInstance();
        state ="En attente";
        price = 0;
        colis = "En attente";

    }
    
    public void setPrice(int p)
    {
        price = p;
    }
    public void setModif(Calendar date)
    {
        lastModif = date;
    }
    public void setId(String uuid)
    {
        id = uuid;
    }
    public void setMembre(Personne p)
    {
        membre=p;
    }
    public void setColis(String s)
    {
        colis =s;
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
    public String getColis()
    {
        return this.colis;
    }
    
    
}
