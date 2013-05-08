/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.ConnectionException;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;



public class SingletonConnection {
    
    private static Connection uniqueConnection;
    
    private SingletonConnection(String id, String pwd) throws ConnectionException
    {
       
        try
        {
            Context cont = new InitialContext();
            
            DataSource source = (DataSource)cont.lookup("jdbc/ProjetScout");
            uniqueConnection = source.getConnection(id,pwd);
            
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
        catch(NamingException e)
        {
            throw new ConnectionException(e.toString());
        }
    }
    
    public static Connection getUniqueInstance(String id, String pwd) throws ConnectionException
    {
        if(uniqueConnection == null)
        {
            new SingletonConnection(id,pwd);
        }
        
            return uniqueConnection;
    }
    public static boolean getConnectionState(){
        try{
            return uniqueConnection.isClosed();
        }
        catch(NullPointerException e){
            return true;
        }
        catch(SQLException e){
            return true;
        }
    }
}
