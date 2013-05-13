/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAcces.SingletonConnection;
import Exception.*;


public class LogginManager {
    
    public void Loggin(String user, String password) throws ConnectionException{
        SingletonConnection.getUniqueInstance(user, password);
    }
    public void Disconnect()throws DisconnectException{
        SingletonConnection.Disconnect();
    }
    public boolean getConnectionState(){
        return SingletonConnection.getConnectionState();
    }
}
