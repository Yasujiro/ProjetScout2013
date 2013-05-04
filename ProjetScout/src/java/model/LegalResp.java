/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jérémy
 */
public class LegalResp extends Personne {
    
    
    
    public LegalResp (String name,String fiName, String street, String num,String tel, String mail )
    {
        super(name,fiName,street,num);
        super.setTel(tel);
        super.setMail(mail);
    }
    public LegalResp(String name, String fiName){
        super(name,fiName);
    }
    public LegalResp(String id){
        super(id);
    }
    
    public String getType()
    {
        return "Responsable légal";
    }
}
