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
            message = m;
            
        }
        public String toString(){
            return "<html>Une erreur est survenue lors de la modification des données<br><br>"+this.message+"</html>";
        }
}
