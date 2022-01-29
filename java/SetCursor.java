import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SetCursor{
    
    BufferedImage dirt = ImageIO.read(new FileInputStream("DirtBlock.jpg"));
    BufferedImage cobble = ImageIO.read(new FileInputStream("Cobblestone.jpg"));
    BufferedImage def = ImageIO.read(new FileInputStream("default_cursor.png"));
    BufferedImage furnace = ImageIO.read(new FileInputStream("FurnaceOff.jpg"));
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    
    Cursor dirtCrs = toolkit.createCustomCursor(dirt, new Point(0, 0), "Cursor");
    Cursor cobbleCrs = toolkit.createCustomCursor(cobble, new Point(0, 0), "Cursor");
    Cursor defCrs = toolkit.createCustomCursor(def, new Point(0, 0), "Cursor");
    Cursor furnaceCrs = toolkit.createCustomCursor(furnace, new Point(0, 0), "Cursor");
    
    boolean dirtPressed = false;
    boolean cobblePressed = false;
    boolean defPressed = true;
    boolean furnacePressed = false;
    
    public SetCursor()throws Exception{}
    
    public Cursor setCursor(){
        
        if(dirtPressed){
            return dirtCrs;
        }else if(cobblePressed){
            return cobbleCrs;
        }else if(furnacePressed){
            return furnaceCrs;
        }else{
            return defCrs;
        }
        
    }
    
}