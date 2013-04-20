/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Jérémy
 */
public class Chief extends Personne {
    
    private String telNum;
    private String email;
    
    public Chief(String name,String fiName,String street,String house,Calendar birth,String tel,String mail)
    {
        super(name,fiName,street,house,birth);
        telNum = tel;
        email = mail;
    }
    
    public String getType()
    {
        return "Chef";
    }
}
