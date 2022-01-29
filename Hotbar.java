import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Hotbar extends Canvas{
    
    HotbarObjects hb = new HotbarObjects();
    
    int x = 150, y = 450;
    
    int boxNum = 0;
    
    int selX = 160;
    
    public Hotbar() throws Exception{}
    
    public void render(Graphics g, int bX, int bY, int notches) throws Exception{
        
        x = 150;
        y = 450;
        
        if(notches == 1 && selX <= 550){
            selX+=60;
            boxNum++;
        }else if(notches == 1 && selX > 550){
            selX = 160;
            boxNum = 0;
        }else if(notches == -1 && selX >= 210){
            selX-=60;
            boxNum--;
        }else if(notches == -1 && selX < 210){
            selX = 580;
            boxNum = 8;
        }
        
        g.fillRect(x-bX, y-bY, 510, 85);
        
        g.setColor(new Color(90, 100, 120));
        
        g.fillRect(selX-bX, (y+10)-bY, 65, 65);
        
        g.setColor(Color.BLACK);
        
        g.drawRect((x+5)-bX, (y+5)-bY, 500, 75);
        
        x = 165;
        y = 465;
        
        int xPos, yPos;
        
        for(int i = 8; i > 0; i--){
            
            xPos = x-bX;
            yPos = y-bY;
            
            g.fillRect(xPos, yPos, 55, 55);
            
            x+=60;
        }
        
        x = 165;
        y = 465;
        
        xPos = x-bX;
        yPos = y-bY;
        
        hb.render(g, xPos, yPos);
        
    }
    
}