import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Pigs extends Canvas{
    
    PigHealth p = new PigHealth(); 
    
    BufferedImage pig = ImageIO.read(new FileInputStream("Pig.jpg"));
    BufferedImage pigL = ImageIO.read(new FileInputStream("PigL.jpg"));
    
    int x = ((int) (Math.random()*60)) * 25;
    int y = -875;
    int arrY = 79;
    
    int heightFall = 0;
    
    int moveRand;
    boolean moveL = false;
    
    boolean start = false;
    
    boolean dead = false;
    
    public Pigs() throws Exception{}
    
    public void render(Graphics g, int blockID[][], Inventory i){
        
       if(!dead){
           
           if(!start){
               while(blockID[x/25][arrY] == 0){
                   arrY--;
                   y+=25;
               }
               start = true;
           }
           
           moveRand = (int) (Math.random()*100)+1;
           
           if(moveRand > 95){
               
               if((int)(Math.random()*2) == 0 && x < 625){
                   
                   if((blockID[(x/25)+1][((1125-y)/25)] == 0 || blockID[(x/25)+1][((1125-y)/25)] == 9) || 
                   (blockID[(x/25)+1][((1125-y)/25)] != 0 && blockID[(x/25)+1][((1125-y)/25)] != 9) && 
                   (blockID[(x/25)+1][((1125-y)/25)+1] == 0 || blockID[(x/25)+1][((1125-y)/25)+1] == 9)){
                       x+=25;
                   }
                   
                   if(blockID[(x/25)][((1100-y)/25)] == 0 || blockID[(x/25)][((1100-y)/25)] == 9){
                       y+=25;
                   }else if(blockID[(x/25)][((1125-y)/25)] != 0 && blockID[(x/25)][((1125-y)/25)] != 9 && blockID[(x/25)+1][((1125-y)/25)] != 0 && blockID[(x/25)+1][((1125-y)/25)] != 9){
                       y-=25;
                   }
                   
                   moveL = false;
                   g.drawImage(pig, x, y, 25, 25, this);
               }else if(x > 0){
                   if((blockID[(x/25)-1][((1125-y)/25)] == 0 || blockID[(x/25)-1][((1125-y)/25)] == 9) || 
                   (blockID[(x/25)-1][((1125-y)/25)] != 0 && blockID[(x/25)-1][((1125-y)/25)] != 9) && 
                   (blockID[(x/25)-1][((1125-y)/25)+1] == 0 || blockID[(x/25)-1][((1125-y)/25)+1] == 9)){
                       x-=25;
                   }
                   
                   if(blockID[(x/25)][((1100-y)/25)] == 0 || blockID[(x/25)][((1100-y)/25)] == 9){
                       y+=25;
                   }else if(blockID[(x/25)][((1125-y)/25)] != 0 && blockID[(x/25)][((1125-y)/25)] != 9 && blockID[(x/25)][((1125-y)/25)] != 0 && blockID[(x/25)+1][((1125-y)/25)] != 9){
                       y-=25;
                   }
                   
                   moveL = true;
                   g.drawImage(pigL, x, y, 25, 25, this);
               }
               
           }else{
               
               if(blockID[(x/25)][((1100-y)/25)] == 0 || blockID[(x/25)][((1100-y)/25)] == 9){
                   y+=25;
                   heightFall++;
                   
                   if(heightFall > 2){
                       p.health--;
                       
                       if(p.health == 0){
                           dead = true;
                           i.rawPorkC++;
                       }
                       
                   }
                    
               }else{
                   heightFall = 0;
               }
               
               if(!moveL){
                   g.drawImage(pig, x, y, 25, 25, this);
               }else{
                   g.drawImage(pigL, x, y, 25, 25, this);
               }
           }
        }
    }
    
}