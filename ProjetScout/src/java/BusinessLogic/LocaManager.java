/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.LocaliteDBAccess;
import Exception.SearchDataException;
import Interface.LocaliteDataAccess;
import java.util.ArrayList;
import model.Localite;

public class LocaManager {
    
        private LocaliteDataAccess dba;
        public LocaManager(){
            dba = new LocaliteDBAccess();
        }
        
        public ArrayList<Localite> getLocalite(Integer pCode) throws SearchDataException
        {
            
            return dba.getLocalite(pCode);
        }
    
}
