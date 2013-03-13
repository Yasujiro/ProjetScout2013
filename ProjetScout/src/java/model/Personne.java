/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Personne {
    
    private String name, lastName, location, streetName,type,unit,section;    
    private int id_Personne,id_Loc,numHouse,numBox;    
    private Date birthDate;
    
    public Personne(String nameP, String lastNameP, int idP, int idLoc, String street, int house, int box, Date birth )
    {
        name = nameP;
        lastName = lastNameP;
        id_Personne = idP;
        id_Loc = idLoc;
        streetName = street;
        numHouse = house;
        numBox = box;
        birthDate = birth;
        
    }
    
    
    public String getName()
    {
        return this.name;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    public int getId()
    {
        return this.id_Personne;
    }
    
    public String getUnit()
    {
        return this.unit;
    }
    
    public String getSect()
    {
        return this.section;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public String getFiName()
    {
        return this.name;
    }
    
    
    // Fonctions Setter;
    
    public void setType(String typePers)
    {
        this.type = typePers;
    }
}
