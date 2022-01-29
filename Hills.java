import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Hills extends Canvas{
    
    /*Block-ID: Air - 0
     *          Stone - 1
     *          Grass - 2
     *          Dirt - 3
     *          Coal - 4
     *          Iron - 5
     *          Gold - 6
     *          Diamond - 7
     *          Mossy - 8
     *          Cobble - 9
     *          Chest - 10
     *          Bedrock = 11
     */
    
    BufferedImage grass = ImageIO.read(new FileInputStream("GrassBlock.jpg"));
    BufferedImage dirt = ImageIO.read(new FileInputStream("DirtBlock.jpg"));
    BufferedImage stone = ImageIO.read(new FileInputStream("StoneBlock.jpg"));
    
    BufferedImage coal = ImageIO.read(new FileInputStream("CoalOre.jpg"));
    BufferedImage iron = ImageIO.read(new FileInputStream("IronOre.jpg"));
    BufferedImage back = ImageIO.read(new FileInputStream("Background.jpg"));
    
    
    int numHills = 13, count = 0;
    int rand;
    
    public Hills() throws Exception{}
    
    public void render(Graphics g, int blockID[][]){
        
        while(count < numHills){
            
            boolean hillDone = false;
            boolean topHill = true;
            boolean outGrass = true;
            
            int arrX = 0, arrY = 39;
            
            int hillX = 0, hillY = 150;
            int hillChance = 0, hillStart = 0, hillWidth = (int) (Math.random()*8)+2, hillCounter = 0, hillSteps = (int) (Math.random()*3)+1;
            
            
            while(hillX < 2000 && hillY < 300){
                
                if(hillX == 0){
                    hillChance = (int) (Math.random()*4);
                }
                
                if(!hillDone && hillChance > 1){
                    hillStart = (int) (Math.random()*80);
                    hillStart *= 25;
                    hillDone = true;
                }else if(hillDone && hillX >= hillStart){
                    
                    if((hillCounter < hillSteps) || topHill || hillCounter > hillWidth-(hillSteps+1)){
                        g.drawImage(grass, hillX, hillY, 25, 25, this);
                        blockID[arrX][arrY] = 2;
                    }else if(outGrass || hillCounter+1 > hillWidth-(hillSteps+1)){
                        g.drawImage(dirt, hillX, hillY, 25, 25, this);
                        blockID[arrX][arrY] = 3;
                        outGrass = false;
                    }else{
                        
                        rand = (int) (Math.random()*100);
                        
                        if(rand > 85){
                            g.drawImage(coal, hillX, hillY, 25, 25, this);
                            blockID[arrX][arrY] = 4;
                        }else if(rand >5){
                            g.drawImage(stone, hillX, hillY, 25, 25, this);
                            blockID[arrX][arrY] = 1;
                        }else{
                            g.drawImage(iron, hillX, hillY, 25, 25, this);
                            blockID[arrX][arrY] = 5;
                        }
                    }
                    
                    hillCounter++;
                }
                
                if(hillCounter < hillWidth && hillX < 1575){
                    hillX+=25;
                    arrX++;
                }else{
                    arrX = 0;
                    arrY--;
                    hillX = 0;
                    hillY+=25;
                    hillStart-=hillSteps*25;
                    hillWidth = hillCounter+(hillSteps*2);
                    hillCounter = 0;
                    topHill = false;
                    outGrass = true;
                }
                
            }
           
            count++;
            
        }
    }
    
}