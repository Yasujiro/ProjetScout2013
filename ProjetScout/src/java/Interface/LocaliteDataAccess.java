/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Exception.SearchDataException;
import java.util.ArrayList;
import model.Localite;

public interface LocaliteDataAccess {
    
    public ArrayList<Localite> getLocalite(Integer pCode) throws SearchDataException;
    
}
