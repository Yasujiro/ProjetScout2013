
package View;

import Controller.ApplicationController;
import java.awt.Color;
import javax.swing.*;


public class ThreadConnection extends Thread {

  private ApplicationController app;
  private JLabel lab;
  private int i;
  private boolean ascendant;
    public ThreadConnection(JLabel j){
    
        super();
        app = new ApplicationController();
        lab = j;
        i=0;
        ascendant = true;
        
        
    }
    
    public void run() {
        
        while(true){
            try{
                
                if(app.getConnectionState()){
                    
                    lab.setForeground(new Color(i,0,0));
                    lab.setText("Déconnecté");
                }
                else{
                    
                    lab.setForeground(new Color(0,i,0));
                    lab.setText("Connecté");
                }
                
                Thread.sleep(50);
                if(i==255)
                    ascendant = false;
                else if (i==0)
                    ascendant = true;
                
                if(ascendant==true)
                    i+=5;
                else
                    i-=5;
                
            }
            catch(Exception e){
                
            }
        }
    }
    
    
}
