import javax.swing.*;
import java.applet.*;

public class Main/* extends Applet*/{
    public static void main(String args[]) throws Exception{
        
        LandGen l = new LandGen();
        JFrame f = new JFrame();
        
        f.add(l);
        
        f.setTitle("SandboxGame");
        f.setLocation(350, 150);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
       
    }
   
   /*public void init(){
       try{
       
           LandGen l = new LandGen();
           JFrame f = new JFrame();
            
           f.add(l);
            
           f.setTitle("SandboxGame");
           f.setLocation(350, 150);
           f.setSize(800, 600);
           f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           f.setResizable(false);
           f.setVisible(true);
           
        }catch(Exception e){}
       
   }*/
}