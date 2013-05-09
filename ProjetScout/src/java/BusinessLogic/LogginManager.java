/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.SingletonConnection;
import Exception.ConnectionException;
import java.sql.SQLException;


public class LogginManager {
    
    public void Loggin(String user, String password) throws ConnectionException{
        SingletonConnection.getUniqueInstance(user, password);
    }
    public void Disconnect()throws ConnectionException{
        SingletonConnection.Disconnect();
    }
    public boolean getConnectionState(){
        return SingletonConnection.getConnectionState();
    }
}
