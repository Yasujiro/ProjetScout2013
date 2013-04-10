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
        
        public Localite getLocalite() throws Exception
        {
            return dba.getLoc();
        }
    
}
