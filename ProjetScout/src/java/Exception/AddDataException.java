/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Jérémy
 */
public class AddDataException extends Exception {
   
    private String message;
    public AddDataException (String m){
        message = m;
    }
    
    public String toString(){
        return "<html>"+ "Erreur lors de l'ajout de la demande<br><br>"+message+"</html>";
    }
    
}
