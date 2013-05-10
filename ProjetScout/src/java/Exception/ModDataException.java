/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Jérémy
 */
public class ModDataException extends Exception {
    
    private String message;
        public ModDataException(String m){
            message = "Modification error : "+m;
            
        }
     public String toString(){
         return "Une erreur est survenue lors de la modification des données";
      }
    public String getMessage(){
       return message;
    }
}
