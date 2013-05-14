/*
 * Cette classe permet de gérer les écritures des erreurs rencontrés (le plus souvent par le biais des try-catch)
 * ou de  diverse information (comme l'ajout ou la modification d'une demande)
 * dans un seul et même fichier "log" pour l'ensemble du programme. Ce fichier log est situé dans le repertoire courrant.
 * la méthode WriteLog est appelée tout au long du programme (via le controller) lorsqu'une erreur est rencontré afin que les détails
 * de celle-ci puissent être écrit dans le fichier log.
 */
package BusinessLogic;

import java.io.IOException;
import java.util.logging.*;

public class LoggerManager {
    private static Logger logger;
    private static Handler fh;
    public LoggerManager(){
        
        try{
            
                logger = Logger.getLogger("Logger"); // Création du journal.
                // création d'un "pointeur" vers le fichier en donnant le nom du fichier. le 2eme argumen est là pour spécifié si on réécrit le fichier a chaque ouverture ou non
                // La cration du pointeur est suivit par la définition du format d'écriture et ar l'ajout du pointeur au journal.
                fh  = new FileHandler("Log.log",true); 
                fh.setFormatter(new SimpleFormatter());
                logger.addHandler(fh);
                
                // Défini à partir de quel niveau les message seront écrit dans le fichier.
                // Avec Level.All, tous les niveaux de messages seront notés.
                logger.setLevel(Level.ALL); 
            
            }
            catch(IOException e){
                logger.log(Level.SEVERE,"Erreur création Handler",e);
            }
    }
    
    public static void WriteLog(String message,Level lvl,Exception e){
        if(fh==null){
            new LoggerManager();
        }
        /* 
         * La méthode 'log' permet d'écrire dans le fichier via le journal. Elle reçoit plusieurs arguments
         * dans notre cas, la méthode à 3 arguments a été choisie, les arguments :
         * Le niveau de criticité du message, le message a écrire et éventuellement l'exception provoquant l'écriture du message
         * Ce paramètre a été mis a null, mais peut très bien avoir une valeur ce qui provoquera l'écriture du StackTrace de l'exception.
        */
        logger.log(lvl, message,(Exception)null);
        
        
    }
    
}
