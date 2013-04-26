/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;


public class WrongValuesException extends Exception {
    
    private String message;
    
    public WrongValuesException (String s)
    {
        message = s;
    }
    
    public String toString()
    {
        return message;
    }
    
}
