/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;




public class ConnectionException extends Exception 
{
    private String message;
    public ConnectionException(String m){
        message = "Connection errror : "+m;
       
    }
    public String getMessage(){
        return message;
    }
    public String toString(){
        return "Erreur de connexion";
    }
    
    

}
