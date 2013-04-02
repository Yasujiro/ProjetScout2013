/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.ConnectionException;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;



/**
 *
 * @author Jérémy
 */
public class SingletonConnection {
    
    private static Connection uniqueConnection;
    
    private SingletonConnection() throws ConnectionException
    {
        try
        {
            Context cont = new InitialContext();
            DataSource source = (DataSource)cont.lookup("jdbc/myDatasource");
            uniqueConnection = source.getConnection();
        }
        catch(SQLException e)
        {
            throw new ConnectionException();
        }
        catch(NamingException e)
        {
            throw new ConnectionException();
        }
    }
    
    public static Connection getUniqueInstance() throws ConnectionException
    {
        if(uniqueConnection == null)
        {
            try{
                SingletonConnection singleConnect = new SingletonConnection();
                return uniqueConnection;
            }
            catch(ConnectionException e)
            {
                throw new ConnectionException();
            }         
            
        }
        else
            return uniqueConnection;
    }
}
