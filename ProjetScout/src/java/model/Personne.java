/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.GregorianCalendar;

public class Personne {
    
    private String name, fiName, streetName,type,id_Personne, numBox,totem,mail,gsm;   
    private int numHouse;    
    private GregorianCalendar birthDate;
    private Localite loc;
    private Personne legalPers;
    
    public Personne(String id)
    {
        id_Personne = id;
    }
    public Personne(String nameP, String fiNameP, String street, int house, GregorianCalendar birth )
    {
        name = nameP;
        fiName = fiNameP;      
        streetName = street;
        numHouse = house;
        birthDate = birth;
    }
    
    
    public String getFiName()
    {
        return this.fiName;
    }    
    public String getName()
    {
        return this.name;
    }
    public String getId()
    {
        return this.id_Personne;
    }
    public String getType()
    {
        return this.type;
    }    

    
    
     // Fonctions Setter;
    public void setBox(String nBox)
    {
        this.numBox= nBox;
    }
    public void setMail(String mail)
    {
        this.mail=mail;
    }
    public void setTel(String tel)
    {
        this.gsm =tel;
    }
    public void setLoc (Localite l)
    {
        this.loc=l;
    }
    public void setLegalPers(Personne legal)
    {
        legalPers = legal;
    }
    
    public void setTotem(String t)
    {
        this.totem=t;
    }
    
    
   

}
