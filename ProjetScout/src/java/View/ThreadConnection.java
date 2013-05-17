
package View;

import Controller.ApplicationController;
import java.awt.Color;
import javax.swing.*;


public class ThreadConnection extends Thread {

  private ApplicationController app;
  private JLabel lab;
  private int i, coeff;
    public ThreadConnection(JLabel j){
    
        super();
        app = new ApplicationController();
        lab = j;
        i=0;
        coeff = 5;
        
        
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
                    coeff = -5;
                else if (i==0)
                    coeff = 5;
                
                
               i+=coeff;
                
            }
            catch(Exception e){
                
            }
        }
    }
    
    
}
