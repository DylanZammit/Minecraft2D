import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Hunger extends Canvas{
    
    BufferedImage fHunger = ImageIO.read(new FileInputStream("FullHunger.png"));
    BufferedImage hHunger = ImageIO.read(new FileInputStream("HalfHunger.png"));
    BufferedImage eHunger = ImageIO.read(new FileInputStream("EmptyHunger.png"));
    
    int hunger;
    int temp;
    
    int hungerCounter = 0;
    
    boolean full = true;
    public Hunger(int hunger) throws Exception{
        
        this.hunger = hunger;
        
    }
    
    public void render(Graphics g, int blocksX, int blocksY){
        
        if(hunger > 20){
            hunger = 20;
        }
        
        if(hungerCounter >= 500){
            hunger--;
            hungerCounter = 0;
        }
        
        hungerCounter++;
        
        if(hunger < 20){
            
            full = false;
            
        }else{
            full = true;
        }
        
        
        if(full){
            
            int counter = hunger;
            int x = 530;
            
            while(counter > 0){
                
                
                g.drawImage(fHunger, x-blocksX, 430-blocksY, 15, 15, this);
                
                counter-=2;
                
                x+=20;
                
            }
            
        }else if(hunger > 0){
        
            int counter = hunger;
            int x = 530;
            int totalH = 0;
            
            temp = 20 - hunger;
            
            if(temp % 2 == 1){
                temp--;
            }
            
            while(temp > 0){
                
                g.drawImage(eHunger, x-blocksX, 430-blocksY, 15, 15, this);
                
                x+=20;
                
                temp-=2;
                
                totalH++;
                
            }
            
            if(hunger % 2 == 1){
                g.drawImage(hHunger, x-blocksX, 430-blocksY, 15, 15, this);
                x+=20;
                totalH++;
            }
            
            while(totalH < 10){
                
                g.drawImage(fHunger, x-blocksX, 430-blocksY, 15, 15, this);
                
                counter-=2;
                
                totalH++;
                
                x+=20;
                
            }
            
        }else{
        
            JOptionPane.showMessageDialog(this, "Game Over. You died from fall damage!!");
            setVisible(false);
            System.exit(0);
        }
        
    }
    
}