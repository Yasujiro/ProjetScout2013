/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Jérémy
 */
public class SearchDataException extends Exception {
    
    private String message;
    public SearchDataException(String m){
        message = m;
    }
    public String toString(){
         return "<html>Une erreur est survenue lors de la récupération des données<br><br>"+this.message+"</html>";
    }
    
}
