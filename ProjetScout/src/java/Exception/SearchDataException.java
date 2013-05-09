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
        message = "Search data error : "+m;
    }
    public String toString(){
         return "Une erreur est survenue lors de la recherche des données";
    }
    
}
