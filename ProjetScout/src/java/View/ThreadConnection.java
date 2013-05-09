/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicationController;
import java.awt.Color;
import javax.swing.*;


public class ThreadConnection extends Thread {

  private ApplicationController app;
  private JLabel lab;
  
    public ThreadConnection(JLabel j){
    
        super();
        app = new ApplicationController();
        lab = j;
    }
    
    public void run() {
        
        while(true){
            try{
                
                if(app.getConnectionState()){
                lab.setForeground(Color.RED);
                lab.setText("Déconnecté");
                }
                else{
                lab.setForeground(Color.GREEN);
                lab.setText("Connecté");
                }
                //frame.setLabConnected(app.getConnectionState());
                Thread.sleep(50);
            }
            catch(Exception e){
                
            }
        }
    }
    
    
}
