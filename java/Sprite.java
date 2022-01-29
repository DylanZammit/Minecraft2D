import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Sprite extends Canvas{
    
    BufferedImage spR = ImageIO.read(new FileInputStream("BoxySprite.jpg"));
    BufferedImage spL = ImageIO.read(new FileInputStream("BoxySpriteLeft.jpg"));
    
    
    int health = 20;
    int hunger = 20;
    
    Hunger hg = new Hunger(hunger);
    Health he = new Health(health);
    
    boolean start = false;
    boolean spriteDir = true;
    boolean move = false;
    int x = 125, y = -900;
    int arrY = 79;
    
    int heightFall = 0;
    
    int IDX, IDY;
    
    public Sprite() throws Exception{}
    
    public boolean isMiddle(){
        
        if(x == 400){
            return true;
        }else{
            return false;
        }
        
    }
    
    public void render(Graphics g, int blockID[][], int blocksX, int blocksY){
        
        
        if(!start){
            
            while(blockID[5][arrY] == 0){
                arrY--;
                y+=25;
            }
            
            start = true;
        }
        
        if(spriteDir){
            
            if((blockID[((x-blocksX)/25)-1][((1125-y)/25)-1] != 0 && blockID[((x-blocksX)/25)-1][((1125-y)/25)-1] != 9) && 
            (blockID[((x-blocksX)/25)-1][((1125-y)/25)] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)] == 9) && 
            (blockID[((x-blocksX)/25)-1][((1125-y)/25)+1] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)+1] == 9)){
                
                y-=25;
                g.drawImage(spR, (x - blocksX)-25, y, 25, 50, this);
            }else if((blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 9)){
                
                y+=25;
                
                if((blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 9)){
                    heightFall++;
                }else{
                    heightFall = 0;
                }
                
                g.drawImage(spR, (x - blocksX)-25, y, 25, 50, this);
            }else if(blockID[((x-blocksX)/25)-1][((1125-y)/25)] != 0 && blockID[((x-blocksX)/25)-1][((1125-y)/25)] != 9){
                    
                x-=25;
                
                g.drawImage(spR, (x - blocksX)-25, y, 25, 50, this);
            }else{
                g.drawImage(spR, (x - blocksX)-25, y, 25, 50, this);
            }
            
        }else{
            
            if((blockID[((x-blocksX)/25)-1][((1125-y)/25)-1] != 0 && blockID[((x-blocksX)/25)-1][((1125-y)/25)-1] != 9) && 
            (blockID[((x-blocksX)/25)-1][((1125-y)/25)] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)] == 9) && 
            (blockID[((x-blocksX)/25)-1][((1125-y)/25)+1] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)+1] == 9)){
                y-=25;
                g.drawImage(spL, (x - blocksX)-25, y, 25, 50, this);
            }else if((blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 9)){
                
                y+=25;
                
                if((blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 0 || blockID[((x-blocksX)/25)-1][((1125-y)/25)-2] == 9)){
                    heightFall++;
                }else{
                    
                    heightFall = 0;
                    
                }
                g.drawImage(spL, (x - blocksX)-25, y, 25, 50, this);
            }else if(blockID[((x-blocksX)/25)-1][((1125-y)/25)] != 0 && blockID[((x-blocksX)/25)-1][((1125-y)/25)] != 9){
                x+=25;
                g.drawImage(spL, (x - blocksX)-25, y, 25, 50, this);
            }else{
                g.drawImage(spL, (x - blocksX)-25, y, 25, 50, this);
            }
            
        }
        
        if(heightFall > 2){
            he.health-=2;
            he.full = false;
        }
        
        he.render(g, blocksX, blocksY, hg);
        hg.render(g, blocksX, blocksY);
        
        
    }
    
}