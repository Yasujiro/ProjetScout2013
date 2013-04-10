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
    
    private SingletonConnection() throws ConnectionException
    {
        try
        {
            Context cont = new InitialContext();
            DataSource source = (DataSource)cont.lookup("jdbc/ProjetScout");
            uniqueConnection = source.getConnection("etu20275","Leb830Se");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR SQL","error",JOptionPane.PLAIN_MESSAGE);
            throw new ConnectionException();
            
        }
        catch(NamingException e)
        {
            JOptionPane.showMessageDialog(null, "ERREUR NAMING","error",JOptionPane.PLAIN_MESSAGE);
            throw new ConnectionException();
        }
    }
    
    public static Connection getUniqueInstance() throws ConnectionException
    {
        if(uniqueConnection == null)
        {
            
                new SingletonConnection();                
              
        }
        
            return uniqueConnection;
    }
}
