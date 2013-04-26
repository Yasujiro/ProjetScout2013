/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jérémy
 */
public class Section {
    
    private String libelle;
    private Unit unit;
    
    public Section(String lib, String u)
    {
        libelle = lib;
        unit = new Unit(u);
    }
    
    public String getLib()
    {
        return this.libelle;
    }
    
    public Unit getUnit()
    {
        return this.unit;
    }
    
    public void setLib(String lib)
    {
        libelle = lib;
    }
    
}
