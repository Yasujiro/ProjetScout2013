/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jérémy
 */
public class Localite {
    
    private String libelle;
    private Integer postalCode;
    
    public Localite(String lib, Integer pCode)
    {
        libelle = lib;
        postalCode = pCode;
    }
    
    public String getLib()
    {
        return this.libelle;
    }
    
    public Integer getPCode()
    {
        return this.postalCode;
    }

}
