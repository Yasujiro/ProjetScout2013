/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Anime extends Personne {
    
    
   
    private Personne legal;
    
    public Anime(String id)
    {
        super(id);
    }
    public Anime(String n,String fiN,String street,String house, Calendar bDate,Personne legalResp )
    {
        super(n,fiN,street,house,bDate);
        legal = legalResp;
    }
    
    public String getTel(){
        if(legal!=null)
            return "";
        else
            return super.getTel();
    }
    
    public String getMail()
    {
        if(legal!=null)
            return "";
        else
            return super.getMail();
    }
    public String getType()
    {
        return "Animé";
    }
    public Personne getLegal()
    {
        return this.legal;
    }
    
    
    public void setTel(String t){
        if(legal!=null)
            legal.setTel(t);
        else
            super.setTel(t);
    }
    
    public void setMail(String m){
        if(legal!=null)
            legal.setMail(m);
        else
            super.setMail(m);
    }
        public void setLegalPers(Personne legalResp){
            legal = legalResp;
        }

    
}
