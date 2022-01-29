import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class HotbarObjects extends Canvas{
    
    /*ID
     * Hand = 0
       WoodenPickaxe = 1
       WoodenShovel = 2
       StonePickaxe = 3
       StoneShovel = 4
       IronPickaxe = 5
       IronShovel = 6
       */
    
    boolean toolsCrafted[] = {true, true, false, false, false, false, false, false};
    
    int boxNum[] = new int[10];
    
    int box = 0;
    int ID = 1;
    
    BufferedImage toolPic[] = {ImageIO.read(new FileInputStream("WoodenPickaxe.png")), ImageIO.read(new FileInputStream("WoodenShovel.png")),
                                ImageIO.read(new FileInputStream("StonePickaxe.png")), ImageIO.read(new FileInputStream("StoneShovel.png")), 
                                ImageIO.read(new FileInputStream("IronPickaxe.png")), ImageIO.read(new FileInputStream("IronShovel.png")), null, null};
    
    public HotbarObjects() throws Exception{}
    
    public void render(Graphics g, int x, int y) throws Exception{
        
        ID = 1;
        box = 0;
        
        
         while(ID <= toolsCrafted.length){
             
            if(toolsCrafted[ID-1]){
                
                g.drawImage(toolPic[ID-1], x, y, 55, 55, this);
                
                boxNum[box] = ID;
            
                box++;
                x+=60;
                
            }
            
            ID++;
        }
            
    }
    
}