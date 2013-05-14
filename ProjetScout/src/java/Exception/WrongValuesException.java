
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
    public String getMessage(){
        return message;
    }
    
}
