/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

import java.sql.SQLException;

/**
 *
 * @author Jérémy
 */
public class ConnectionException extends SQLException 
{
    
    public ConnectionException(String m){
        super(m);
       
    }
    

}
