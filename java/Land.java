import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Land extends Canvas{
    
    /*Block-ID: Air - 0
     *          Stone - 1 -- 7
     *          Grass - 2 -- 2
     *          Dirt - 3 -- 2
     *          Coal - 4
     *          Iron - 5
     *          Gold - 6
     *          Diamond - 7
     *          Mossy - 8
     *          Cobble - 9
     *          Chest - 10
     *          Bedrock - 11
     *          Furnace - 12
     */
    
    public Land() throws Exception{}
    
    BufferedImage grass = ImageIO.read(new FileInputStream("GrassBlock.jpg"));
    BufferedImage dirt = ImageIO.read(new FileInputStream("DirtBlock.jpg"));
    BufferedImage stone = ImageIO.read(new FileInputStream("StoneBlock.jpg"));
    BufferedImage bedrock = ImageIO.read(new FileInputStream("BedrockBlock.jpg"));
    
    BufferedImage mossy = ImageIO.read(new FileInputStream("Mossy.jpg"));
    BufferedImage cobble = ImageIO.read(new FileInputStream("Cobblestone.jpg"));
    BufferedImage chest = ImageIO.read(new FileInputStream("Chest.jpg"));
    
    BufferedImage back = ImageIO.read(new FileInputStream("Background.jpg"));
    
    BufferedImage coal = ImageIO.read(new FileInputStream("CoalOre.jpg"));
    BufferedImage iron = ImageIO.read(new FileInputStream("IronOre.jpg"));
    BufferedImage gold = ImageIO.read(new FileInputStream("GoldOre.jpg"));
    BufferedImage diamond = ImageIO.read(new FileInputStream("DiamondOre.jpg"));
    
    int x = 0, y = 300;
    int dunX = 0, dunY = 300;
    
    int arrX = 0, arrY = 33; 
    
    int randX = (int) (Math.random()*70) * 25;
    int randY = (int) (Math.random()*20) * 25 + 300;
    
    boolean layer1 = true;
    float f = 55, h = 70;
    float oreProb = 80, coalProb = 90, ironProb = 110, diamProb = 150;
    
    boolean dungeonDone = false;
    
    public void render(Graphics g, int blockID[][]){
        
        while(arrX != 63 || arrY !=0){
            
            float rand = (float)(Math.random()*100);
            
            if(y > 1005){
                
                g.drawImage(bedrock, x, y, 25, 25, this);
                
                blockID[arrX][arrY] = 11;
            }else{
                
                if(rand > f){
                    g.drawImage(dirt, x, y, 25, 25, this);
                    blockID[arrX][arrY] = 3;
                }else if(rand > oreProb){
                    
                    int rand1 = (int)(Math.random()*100);
                    
                    if(rand1 > 95 && y > 850){
                        g.drawImage(diamond, x, y, 25, 25, this);
                        blockID[arrX][arrY] = 7;
                    }else if(rand1 > 90 && y > 700){
                        g.drawImage(gold, x, y, 25, 25, this);
                        blockID[arrX][arrY] = 6;
                    }else if(rand1 > 80){
                        g.drawImage(iron, x, y, 25, 25, this);
                        blockID[arrX][arrY] = 5;
                    }else if(rand1 > 70){
                        g.drawImage(coal, x, y, 25, 25, this);
                        blockID[arrX][arrY] = 4;
                    }else{
                        g.drawImage(stone, x, y, 25, 25, this);
                        
                        blockID[arrX][arrY] = 1;
                    }
                    
                }else if(rand > h){
                    g.drawImage(stone, x, y, 25, 25, this);
                    blockID[arrX][arrY] = 1;
                }else{
                    g.drawImage(stone, x, y, 25, 25, this);
                    blockID[arrX][arrY] = 1;
                }
                
            }
            
            if(x < 1575){
                x+=25;
                arrX++;
            }else{
                
                arrY--;
                arrX = 0;
                y+=25;
                x = 0;
                
                f+=3;
                h-=3;
                oreProb-=3;
                
                if(coalProb > 75){
                    coalProb-=10;
                }
                
                if(ironProb > 80){
                    ironProb-=10;
                }
                
                if(diamProb > 90){
                    diamProb-=10;
                }
                
            }
            
        }
        
        /*int startX, startY;
        int dunDimX, dunDimY;
        
        while(!dungeonDone){
            
            
            if(dunX >= randX && dunY >= randY){
                
                dunDimX = 0;
                dunDimY = 0;
                
                boolean firstLine = true;
                boolean chestDone = false;
                
                int chestRand = (int) (Math.random()*3)+1;
                
                while(dunDimX != 0 || dunDimY != 150){
                    
                    if(firstLine || dunDimX == 0 || dunDimX == 125 || dunDimY == 125){
                        g.drawImage(mossy, randX+dunDimX, randY+dunDimY, 25, 25, this);
                        //blockID[(randX+dunDimX)/25][((randY+dunDimY)/25)-5] = 8;
                        //blockID[x/25][((575-y)/25)] = 11;
                    }else{
                        g.drawImage(cobble, randX+dunDimX, randY+dunDimY, 25, 25, this);
                        //blockID[x/25][((575-y)/25)] = 11;
                        //blockID[(randX+dunDimX)/25][((randY+dunDimY)/25)-5] = 9;
                    }
                    
                    dungeonDone = true;
                    
                    if(dunDimX < 125){
                        dunDimX+=25;
                    }else{
                        dunDimX = 0;
                        dunDimY+=25;
                        firstLine = false;
                    }
                }
                
                g.drawImage(chest, randX+chestRand*25, randY+100, 25, 25, this);
                //blockID[x/25][((575-y)/25)] = 11;
                //blockID[(randX+dunDimX)/25][((randY+dunDimY)/25-5)] = 10;
                
            }
            
            if(dunX < 1575){
                dunX+=25;
            }else{
                dunY+=25;
                dunX = 0;
            }
            
        }*/
        
    }
    
}