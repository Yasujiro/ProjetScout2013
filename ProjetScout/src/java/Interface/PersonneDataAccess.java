/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Exception.AddDataException;
import Exception.ModDataException;
import Exception.SearchDataException;
import java.util.ArrayList;
import model.LegalResp;
import model.Personne;

public interface PersonneDataAccess {
    public void addPersonne(Personne p) throws AddDataException;
    public ArrayList<Personne> getPers(Personne p) throws SearchDataException;
    public ArrayList<LegalResp> getLegal() throws SearchDataException;
    public void modPers(Personne p)throws ModDataException;
    public int countPers(String id) throws SearchDataException;
}
