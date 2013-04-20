/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Unit {
    
    private String libelle;
    private Localite loca;
    private int nbMembers;
    
    public Unit(String lib)
    {
        libelle = lib;
        loca = null;
        nbMembers = 0;
    }
    
    
    
    public String getLib()
    {
        return this.libelle;
    }
    
    public Localite getLoc()
    {
        return this.loca;
    }
    
    public int getNb()
    {
        return this.nbMembers;
    }
    
    public String toString()
            
    {
        return this.libelle;
    }
    
    
    public void setLoc(Localite loc)
    {
        this.loca = loc;
    }
}
