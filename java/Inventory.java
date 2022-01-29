import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Inventory extends Canvas{
    
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
     *          Bedrock = 11
     *          
     *          Raw Pork = 50
     *          Raw Beef = 51
     */
    BufferedImage stoneShovel = ImageIO.read(new FileInputStream("StoneShovel.png"));
    BufferedImage stonePick = ImageIO.read(new FileInputStream("StonePickaxe.png"));
    
    BufferedImage sprite = ImageIO.read(new FileInputStream("BoxySprite.jpg"));
    
    BufferedImage dirt = ImageIO.read(new FileInputStream("DirtBlock.jpg"));
    BufferedImage cobble = ImageIO.read(new FileInputStream("Cobblestone.jpg"));
    BufferedImage coal = ImageIO.read(new FileInputStream("CoalDrop.png"));
    BufferedImage iron = ImageIO.read(new FileInputStream("IronOre.jpg"));
    BufferedImage furnace = ImageIO.read(new FileInputStream("FurnaceOff.jpg"));
    
    BufferedImage rawPork = ImageIO.read(new FileInputStream("RawPork.png"));
    BufferedImage rawBeef = ImageIO.read(new FileInputStream("RawBeef.png"));
    
    int invBox[] = new int[16];
    
    int stoneC = 0;
    int dirtC = 0;
    int coalC = 0;
    int ironC = 0;
    int goldC = 0;
    int diamondC = 0;
    int cobbleC = 0;
    int furnaceC = 0;
    
    int rawPorkC = 0;
    int rawBeefC = 0;
    
    int cbX, cbY;
    
    int blockCraft[] = new int[9];
    
    public Inventory() throws Exception{}
    
    public void render(Graphics g, int x, int y){
        
        g.fillRect(100-x, 100-y, 600, 400);
        g.drawImage(sprite, 200-x, 150-y, 75, 150, this);
        
        g.setColor(Color.BLACK);
        
        g.drawRect(395-x, 135-y, 170, 170);
        
        int craftX = 400, craftY = 140;
        int countCraft = 0;
        
        cbX = craftX-x;
        cbY = craftY-y;
        
        while(craftX != 400 || craftY != 305){
            g.drawRect(craftX-x, craftY-y, 50, 50);
            
            if(blockCraft[countCraft] == 9){
                
                g.drawImage(cobble, craftX-x, craftY-y, 50, 50, this);
                
            }else if(blockCraft[countCraft] == 1){
                
                g.drawImage(dirt, craftX-x, craftY-y, 50, 50, this);
                
            }
            
            craftX+=55;
            
            if(craftX == 565){
                craftY+=55;
                craftX = 400;
            }
            
            countCraft++;
            
        }
        
        if(blockCraft[0] == 0 && blockCraft[1] == 9 && blockCraft[2] == 0 && 
        blockCraft[3] == 0 && blockCraft[4] == 9 && blockCraft[5] == 0 && 
        blockCraft[6] == 0 && blockCraft[7] == 9 && blockCraft[8] == 0){
            
            g.drawImage(stoneShovel, 605-x, 190-y, 55, 55, this);
            
        }else if(blockCraft[0] == 9 && blockCraft[1] == 9 && blockCraft[2] == 9 && 
        blockCraft[3] == 0 && blockCraft[4] == 9 && blockCraft[5] == 0 && 
        blockCraft[6] == 0 && blockCraft[7] == 9 && blockCraft[8] == 0){
            
            g.drawImage(stonePick, 605-x, 190-y, 55, 55, this);
            
        }else if(blockCraft[0] == 9 && blockCraft[1] == 9 && blockCraft[2] == 9 && 
        blockCraft[3] == 9 && blockCraft[4] == 0 && blockCraft[5] == 9 && 
        blockCraft[6] == 9 && blockCraft[7] == 9 && blockCraft[8] == 9){
            
            g.drawImage(furnace, 605-x, 190-y, 55, 55, this);
            
        }
        
        g.drawRect(605-x, 190-y, 55, 55);
        
        g.drawRect(600-x, 185-y, 65, 65);
        
        g.drawRect(150-x, 320-y, 470, 150);
        
        craftX = 165;
        craftY = 340;
        
        while(craftX > 165 || craftY < 450){
            
            g.drawRect(craftX-x, craftY-y, 50, 50);
            craftX+=55;
            
            if(craftX > 595){
                craftY+= 60;
                craftX = 165;
            }
            
        }
        
        g.drawRect(105-x, 105-y, 590, 390);
        
        int nextBoxX = 165, nextBoxY = 340;
        int arrBox = 0;
        
        int totBox = 1;
        int rem = 0;
        
        int counter = 0;
        
        g.setFont(new Font(null, Font.PLAIN, 20));
        if(cobbleC > 0){
            
            if(cobbleC > 16){
                totBox = cobbleC / 16;
                
                rem = cobbleC % 16;
                
            }
            
            if(cobbleC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(cobble, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(cobbleC < 16 && rem == 0){
                    g.drawString(Integer.toString(cobbleC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 9;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
        
        ///////////////////////////////////////////////////
        totBox = 1;
        rem = 0;
        
        
        if(dirtC > 0){
             
            if(dirtC > 16){
                totBox = dirtC / 16;
                
                rem = dirtC % 16;
                
            }
            
            if(dirtC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(dirt, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(dirtC < 16 && rem == 0){
                    g.drawString(Integer.toString(dirtC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 3;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
        ///////////////////////////////////////////////////
        
        totBox = 1;
        rem = 0;
        
        
        if(rawPorkC  > 0){
             
            if(rawPorkC > 16){
                totBox = rawPorkC / 16;
                
                rem = rawPorkC % 16;
                
            }
            
            if(rawPorkC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(rawPork, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(rawPorkC < 16 && rem == 0){
                    g.drawString(Integer.toString(rawPorkC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 50;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
        ///////////////////////////////////////////////////
        
        totBox = 1;
        rem = 0;
        
        
        if(rawBeefC  > 0){
             
            if(rawBeefC > 16){
                totBox = rawBeefC / 16;
                
                rem = rawBeefC % 16;
                
            }
            
            if(rawBeefC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(rawBeef, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(rawBeefC < 16 && rem == 0){
                    g.drawString(Integer.toString(rawBeefC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 51;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
        ///////////////////////////////////////////////////
        
        totBox = 1;
        rem = 0;
        
        
        if(coalC  > 0){
             
            if(coalC > 16){
                totBox = coalC / 16;
                
                rem = coalC % 16;
                
            }
            
            if(coalC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(coal, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(coalC < 16 && rem == 0){
                    g.drawString(Integer.toString(coalC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 51;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
        ///////////////////////////////////////////////////
        
        totBox = 1;
        rem = 0;
        
        
        if(ironC  > 0){
             
            if(ironC > 16){
                totBox = ironC / 16;
                
                rem = ironC % 16;
                
            }
            
            if(ironC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(iron, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(coalC < 16 && rem == 0){
                    g.drawString(Integer.toString(ironC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 51;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
        ///////////////////////////////////////////////////
        
        totBox = 1;
        rem = 0;
        
        
        if(furnaceC  > 0){
             
            if(furnaceC > 16){
                totBox = furnaceC / 16;
                
                rem = furnaceC % 16;
                
            }
            
            if(furnaceC < 16){
                counter = 1;
            }else if(rem > 0){
                counter = totBox + 1;
            }else{
                counter = totBox;
            }
            
            
            while(counter > 0){
            
                g.drawImage(furnace, nextBoxX-x, nextBoxY-y, 50, 50, this);
                if(coalC < 16 && rem == 0){
                    g.drawString(Integer.toString(furnaceC), (nextBoxX+35)-x, (nextBoxY+45)-y);
                }else{
                    
                    if(counter == 1 && rem > 0){
                        g.drawString(Integer.toString(rem), (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }else{
                        g.drawString("16", (nextBoxX+35)-x, (nextBoxY+45)-y);
                    }
                    
                }
                invBox[arrBox] = 12;
                
                nextBoxX+=55;
                
                if(nextBoxX > 595){
                    nextBoxY+=60;
                    nextBoxX = 165;
                }
                arrBox++;
                counter--;
            }
             
        }
        
    }
    
}