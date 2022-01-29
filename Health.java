import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

public class Health extends Canvas{
    
    int health;
    int temp;
    
    int regenCounter = 0;
    
    boolean full = true;
    
    BufferedImage fullH = ImageIO.read(new FileInputStream("FullHeart.png"));
    BufferedImage halfH = ImageIO.read(new FileInputStream("HalfHeart.png"));
    BufferedImage emptyH = ImageIO.read(new FileInputStream("EmptyHeart.png"));
    
    public Health(int health) throws Exception{
    
        this.health = health;
        
    }
    
    public void render(Graphics g, int blocksX, int blocksY, Hunger hg){
        
        if(health < 20){
            if(hg.hunger > 17){
                if(regenCounter == 100){
                    health++;
                    regenCounter = 0;
                }
                
                regenCounter++;
            }
        }
        
        if(health == 20){
            
            int counter = health;
            int x = 30;
            
            while(counter > 0){
                
                x+=20;
                
                g.drawImage(fullH, x-blocksX, 430-blocksY, 15, 15, this);
                
                counter-=2;
                
            }
            
        }else if(health > 0){
        
            int counter = health;
            int x = 30;
            
            while(counter >= 0){
                
                x+=20;
                
                g.drawImage(fullH, x-blocksX, 430-blocksY, 15, 15, this);
                
                counter-=2;
                
            }
            
            if(counter == -1){
                g.drawImage(halfH, x-blocksX, 430-blocksY, 15, 15, this);
                x+=20;
            }
            
            temp = 20 - health;
            
            if(temp % 2 == 1){
                temp--;
            }
            
            while(temp > 0){
                
                g.drawImage(emptyH, x-blocksX, 430-blocksY, 15, 15, this);
                
                x+=20;
                
                temp-=2;
                
            }
            
            
        }else{
            
            JOptionPane.showMessageDialog(null, "Game Over. You died from fall damage!!");
            setVisible(false);
            System.exit(0);
        }
        
    }
    
}