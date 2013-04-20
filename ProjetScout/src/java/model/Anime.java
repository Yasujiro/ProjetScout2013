/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Anime extends Personne {
    
    
   
    private Personne legal;
    
    public Anime(String n,String fiN,String street,String house, Calendar bDate,Personne legalResp )
    {
        super(n,fiN,street,house,bDate);
        legal = legalResp;
    }
    
    public String getType()
    {
        return "Animé";
    }
    
}
