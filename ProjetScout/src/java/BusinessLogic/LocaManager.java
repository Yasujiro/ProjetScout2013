/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.LocaliteDBAccess;
import java.util.ArrayList;
import model.Localite;

/**
 *
 * @author Jérémy
 */
public class LocaManager {
    
        private LocaliteDBAccess dba = new LocaliteDBAccess();
        
        public ArrayList<Localite> getLocalite(Integer pCode) throws Exception
        {
            return dba.getLocalite(pCode);
        }
    
}
