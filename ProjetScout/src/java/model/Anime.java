/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;


public class Anime extends Personne {
    
    
   
    private Personne legal;
    private String totem;
    
    public Anime(String id)
    {
        super(id);
    }
    public Anime(String name,String fiName){
        super(name,fiName);
    }
    public Anime(String n,String fiN,String street,String house, Calendar bDate,Personne legalResp )
    {
        super(n,fiN,street,house,bDate);
        legal = legalResp;
    }
    
    // Fonctions gettor
    //<editor-fold>
    public String getTel(){
        if(legal!=null)
            return null;
        else
            return super.getTel();
    }
    
    public String getMail()
    {
        if(legal!=null)
            return null;
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
    public String getTotem(){
        return this.totem;
    }
    //</editor-fold>
    
    
    //Fonctions settor
    //<editor-fold>
    public void setTel(String t){     
            super.setTel(t);
    }
    
    public void setMail(String m){
        super.setMail(m);
    }
    public void setLegalPers(Personne legalResp){
            legal = legalResp;
    }
    
    public void setTotem(String t){
        this.totem = t;
    }
    //</editor-fold>
       

    
}
