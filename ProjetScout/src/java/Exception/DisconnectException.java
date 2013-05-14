
package Exception;


public class DisconnectException extends Exception {
    
    private String message;
    public DisconnectException(String m){
        message = "Disconnection error : "+m;
       
    }
    public String getMessage(){
        return message;
    }
    public String toString(){
        return "Erreur de déconnexion";
    }
    
}
