/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Jérémy
 */
public class DeleteException extends Exception {
    
    private String message;
    public DeleteException (String m){
        message =m;
    }
    public String toString(){
        return "Une erreur est survenue lors de la suppression des données";
    }
    public String getMessage(){
        return message;
    }
}
