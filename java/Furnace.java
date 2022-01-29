import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;

public class Furnace extends Canvas{
    
    public Furnace() throws Exception{}
    
    public void render(Graphics g, int blocksX, int blocksY){
        
        
        g.fillRect(150-blocksX, 150-blocksY, 600, 400);
        
        g.setColor(Color.BLACK);
        
        g.drawRect(105-blocksX, 105-blocksY, 590, 390);
        
        g.drawRect(150-blocksX, 150-blocksY, 55, 55);
        g.drawRect(150-blocksX, 230-blocksY, 55, 55);
        
    }
    
}