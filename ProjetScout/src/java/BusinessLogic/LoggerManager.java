/*
 * Cette classe permet de gérer les écritures des erreurs rencontrés (le plus souvent par le biais des try-catch
 * dans un seul et même fichier "log" pour l'ensemble du programme.
 * la méthode WriteLog est appelée tout au long du programme (via le controller) lorsqu'une erreur est rencontré afin que les détails
 * de celle-ci puissent être écrit dans le fichier log.
 */
package BusinessLogic;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {
    private static Logger logger;
    private static Handler fh;
    public LoggerManager(){
        
        try{
            
                logger=Logger.getLogger("LoggerTest");
                fh  = new FileHandler("Log.log",true);
                fh.setFormatter(new SimpleFormatter());
                logger.addHandler(fh);
                logger.setLevel(Level.FINEST);
            
            }
            catch(IOException e){
                logger.log(Level.SEVERE,"Erreur création Handler",e);
            }
    }
    
    public static void WriteLog(String message,Level lvl,Exception e){
        if(fh==null){
            new LoggerManager();
        }
        logger.log(lvl, message,(Exception)null);
        
        
    }
    
}
