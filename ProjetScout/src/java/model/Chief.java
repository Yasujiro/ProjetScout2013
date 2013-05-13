/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

public class Chief extends Personne {
    
    private String totem;
    
    public Chief(String id)
    {
        super(id);
    }
    public Chief(String name, String fiName){
        super(name,fiName);
    }
    public Chief(String name,String fiName,String street,String house,Calendar birth,String tel,String mail)
    {
        super(name,fiName,street,house,birth);
        super.setTel(tel);
        super.setMail(mail);
    }
    
    public String getType()
    {
        return "Chef";
    }
    public String getTotem(){
        return this.totem;
    }
    
    public void setTotem(String t){
        this.totem = t;
    }
}
