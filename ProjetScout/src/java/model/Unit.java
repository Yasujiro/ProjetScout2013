/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Unit {
    
    private String libelle;
    private Localite loca;
    private int nbMembers,nbScout,nbChief;
    
    public Unit(String lib)
    {
        libelle = lib;
        loca = null;
        nbMembers = 0;
        nbScout = 0;
        nbChief = 0;
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
    
    public int getNbScout()
    {
        return this.nbScout;
    }
    
        public int getNbChief()
    {
        return this.nbChief;
    }
    
    public String toString()
            
    {
        return this.libelle;
    }
    
    
    public void setLib(String lib)
    {
        libelle = lib;
    }
    
    public void setLoc(Localite loc)
    {
        this.loca = loc;
    }
    public void setNbScout(int n)
    {
        this.nbScout+=n;
        this.nbMembers+=n;
    }
    public void setNbChief(int n)
    {
        this.nbChief+=n;
        this.nbMembers+=n;
        
    }
}
