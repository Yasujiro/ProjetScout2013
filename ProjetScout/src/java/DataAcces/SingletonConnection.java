/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcces;

import Exception.ConnectionException;
import Exception.DisconnectException;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;



public class SingletonConnection {
    
    private static Connection uniqueConnection;
    private SingletonConnection(String id, String pwd) throws ConnectionException
    {
       
        try
        {
            /*Context cont = new InitialContext();            
            DataSource source = (DataSource)cont.lookup("jdbc/ProjetScout");
            uniqueConnection = source.getConnection(id,pwd);
            /*
             * Utilisation de la méthode getConnection de DriverManager plutôt que des méthodes expliquées dans
             * les slides pour deux raisons :
             *     - Les méthodes présentées dans les slides permettent de faire la première connexion correctement
             *  mais une fois celle-ci faite, il est possible de se connecter quel que soit le nom d'utilisateur ou mot de passe
             *  entrés. Un peu comme si la connexion ne se fermait pas, malgré la méthode close();.
             *    - Temps de connexion à la BD beaucoup plus rapide.
             *              
            */
            uniqueConnection =DriverManager.getConnection("jdbc:derby://localhost:1527/ProjetScout",id,pwd);
            uniqueConnection.setAutoCommit(false);
            
            
            
            
            
        }
        catch(SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
       /* catch(NamingException e)
        {
            throw new ConnectionException(e.toString());
        }*/
    }
    
    public static Connection getUniqueInstance(String id, String pwd) throws ConnectionException
    {
        try{
            if(uniqueConnection == null || uniqueConnection.isClosed())
                new SingletonConnection(id,pwd);
        }
        catch(SQLException e){
            throw new ConnectionException(e.getMessage());
        }
        
            return uniqueConnection;
    }
    public static void Disconnect() throws DisconnectException{
        if(uniqueConnection!=null){
            try{
                uniqueConnection.close();
            }
            catch(SQLException e){
                throw new DisconnectException(e.getMessage());
            }
        }
        else
            throw new DisconnectException("No Connection Found");
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
