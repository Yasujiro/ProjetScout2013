/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Personne {
    
    private String name, fiName, streetName,id_Personne, numHouse,numBox,totem,mail,gsm;   
    private Personne legalResp;
    private Calendar birthDate;
    private Localite loc;
    
    public Personne(String id)
    {
        id_Personne = id;
        birthDate = null;
    }
    public Personne(String n,String fiN)
    {
        name = n;
        fiName = fiN;
    }
    public Personne(String nameP, String fiNameP, String street, String house, Calendar birth )
    {
        name = nameP;
        fiName = fiNameP;      
        streetName = street;
        numHouse = house;
        birthDate = birth;
    }
    public Personne(String nameP,String fiNameP,String street, String house)
    {
        name = nameP;
        fiName = fiNameP;      
        streetName = street;
        numHouse = house;
        
    }
    
    public String toString()
    {
        return this.name+" "+this.fiName;
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
        return "";
    }    
    public Calendar getBirth()
    {
        return this.birthDate;
    }
    public Localite getLoc()
    {
        return this.loc;
    }
    public String getStreet()
    {
        return this.streetName;
       
    }

    public String getHouse()
    {
        return this.numHouse;
    }
    public String getBox()
    {
        return this.numBox;
    }
    
    public Personne getLegal()
    {
        return legalResp;
    }
    public String getTel()
    {
        return this.gsm;
    }
    public String getMail()
    {
        return this.mail;
    }
 
     // Fonctions Setter;

    public void setId(String uId)
    {
        this.id_Personne = uId;
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
    { }
    public void setBox(String box)
    {
        this.numBox = box;
    }
    
    public void setTotem(String t)
    {
        this.totem=t;
    }
    public void setName(String n)
    {
        this.name=n;
    }
    public void setFiName(String fName)
    {
        this.fiName=fName;
    }
    
    public void setHouse(String h)
    {
        this.numHouse = h;
    }
    public void setStreet(String s){
        this.streetName = s;
    }
    
    public void setBirth(Date d){
        
        if(this.birthDate==null)
            this.birthDate=Calendar.getInstance();
        this.birthDate.setTime(d);
    }
   

}
