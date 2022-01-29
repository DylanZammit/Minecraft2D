import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Mobs extends Canvas{
    
    int pigNum = (int) (Math.random()*3)+1;
    int cowNum = (int) (Math.random()*3)+1;
    
    int pAlive;
    int cAlive;
    
    boolean respawnP = false;
    boolean respawnC = false;
    
    Pigs p[] = new Pigs[4];
    Cows c[] = new Cows[4];
    
    public Mobs() throws Exception{
    
        for(int x = 0; x < p.length; x++){
            p[x] = new Pigs();  
        }
        
        for(int x = 0; x < c.length; x++){
            c[x] = new Cows();  
        }
        
    }
    
    public void render(Graphics g, int blockID[][], Inventory i){
        try{
        pAlive = 0;
        cAlive = 0;
        
        for(int x = 0; x < pigNum; x++){
            if(!p[x].dead){
                pAlive++;
            }
        }
        
        for(int x = 0; x < cowNum; x++){
            
            if(!c[x].dead){
                cAlive++;
            }
        }
        
        if(pAlive == 0){
            p[0].dead = false;
            p[0].x = ((int) (Math.random()*60)) * 25;
            p[0].y = -875;
            p[0].p.health = 5;
            p[0].start = false;
            p[0].arrY = 79;
            respawnP = true;
        }
        
        if(cAlive == 0){
            c[0].dead = false;
            c[0].x = ((int) (Math.random()*60)) * 25;
            c[0].y = -875;
            c[0].c.health = 5;
            c[0].start = false;
            c[0].arrY = 79;
            respawnC = true;
        }
        
        //p[0].render(g, blockID, i);
        
        for(int x = 0; x < pigNum /*&& !respawnP*/; x++){
            p[x].render(g, blockID, i);
        }
        
        //c[0].render(g, blockID, i);
        
        for(int x = 0; x < cowNum /*&& !respawnC*/; x++){
            c[x].render(g, blockID, i);
        }
    }catch(Exception e){e.printStackTrace();}
    }
    
}