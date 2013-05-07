/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.ConnectionException;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.swing.JOptionPane;




public class SingletonConnection {
    
    private static Connection uniqueConnection;
    
    private SingletonConnection() throws ConnectionException, Exception
    {
        try
        {
            Context cont = new InitialContext();
            DataSource source = (DataSource)cont.lookup("jdbc/ProjetScout");
            uniqueConnection = source.getConnection("etu20275","Leb830Se");
        }
        catch(SQLException e)
        {
            
            throw new ConnectionException(e.toString());
            
        }
        catch(NamingException e)
        {
            
            throw new ConnectionException(e.toString());
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }
    
    public static Connection getUniqueInstance() throws ConnectionException, Exception
    {
        if(uniqueConnection == null)
        {
            new SingletonConnection();
        }
        
            return uniqueConnection;
    }
}
