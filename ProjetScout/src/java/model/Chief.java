/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;


public class Chief extends Personne {
    
    private String phoneNumber;
    private String email;
    private String totem;
    private int id_Resp;
    
    public Chief(String nameChief, String lastNameChief, int id_Chief, int idLoc, String street, int house, int box, Date birth, String gsm, String mail, String totem,int idResp )
    {
        super(nameChief,lastNameChief,id_Chief,idLoc, street,house,box,birth);
        phoneNumber = gsm;
        email = mail;
        this.totem = totem;
        id_Resp = idResp;
        super.setType("Chef");
        
    }
    
}
