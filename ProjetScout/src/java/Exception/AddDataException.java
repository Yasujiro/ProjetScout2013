/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;


public class AddDataException extends Exception {
   
    private String message;
    public AddDataException (String m){
        message = "Add data error : "+m;
    }
    
    public String toString(){
        return "Une erreur est survenue lors de l'ajout des données";
    }
    public String getMessage(){
        return message;
    }
    
}
