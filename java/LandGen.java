import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import java.applet.*;

public class LandGen extends Canvas implements Runnable, KeyListener, MouseListener, MouseWheelListener{
  // public class LandGen extends Applet implements Runnable, KeyListener, MouseListener, MouseWheelListener{ 
    
    
    Thread t = new Thread(this);
    
    BufferedImage grass;;
    BufferedImage dirt;
    BufferedImage stone;
    
    BufferedImage mossy;
    BufferedImage cobble;
    BufferedImage bedrock;
    
    BufferedImage furnace;
    
    BufferedImage coal;
    BufferedImage iron;
    BufferedImage gold;
    BufferedImage diamond;
    
    Land l = new Land();
    Hills h = new Hills();
    SetCursor sc = new SetCursor();
    Sprite s = new Sprite();
    //Music mu = new Music();
    Mobs m = new Mobs();
    SaveFile sf = new SaveFile();
    LoadFile lf = new LoadFile();
    MenuScreen ms = new MenuScreen();
    Inventory i = new Inventory();
    Furnace f = new Furnace();
    Hotbar hb = new Hotbar();
    /*Land l;
    Hills h;
    SetCursor sc;
    Sprite s;
    //Music mu = new Music();
    Mobs m;
    SaveFile sf;
    LoadFile lf;
    MenuScreen ms;
    Inventory i;
    Furnace f;
    Hotbar hb;*/
    
    boolean renderDone = false;
    boolean menuScreen = false;
    boolean furnaceO = false;
    boolean hueInc = false;
    
    int backX = 0, backY = 0;
    int blocksX = 0, blocksY = 0;
    
    int r = 0;
    int g = 130;
    int b = 255;
    
    int IDX, IDY;
    
    int time = 0;
    
    int notches = 0;
    
    int blockID[][] = new int[80][80];
    Image dbImage;
    Graphics dbg;
    BufferedImage bf;
    
    /*public void init(){
        try{
            
            l = new Land();
            h = new Hills();
            sc = new SetCursor();
            s = new Sprite();
            //Music mu = new Music();
            m = new Mobs();
            sf = new SaveFile();
            lf = new LoadFile();
            ms = new MenuScreen();
            i = new Inventory();
            f = new Furnace();
            hb = new Hotbar();
            addKeyListener(this);
            addMouseWheelListener(this);
            addMouseListener(this);
            
            t.start();
        }catch(Exception e){}
    }*/
    
    public LandGen() throws Exception{
        
        addKeyListener(this);
        addMouseWheelListener(this);
        addMouseListener(this);
        
        t.start();
        
    }
    
    boolean endGame = false;
    
    public void run(){
        
        while(true){
            
            try{
                
                render();
                repaint();
                
                if(sf.save){
                    sf = new SaveFile(this);
                }
                
                if(lf.load){
                    lf = new LoadFile(this);
                }
                
                if(endGame){ 
                    setVisible(true);
                    System.exit(0);
                }
                
                Thread.sleep(20);
                
            }catch(Exception e){
            
                //e.printStackTrace();
                
            }
        }
        
    }
    
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void paint(Graphics g){
        
        try{
            
            g.drawImage(bf, blocksX, blocksY, null);
            
        }catch(Exception e){}
        
    }
    
    boolean movePic = false;
    
    /*BufferedImage grass = ImageIO.read(new FileInputStream("GrassBlock.jpg"));
    BufferedImage dirt = ImageIO.read(new FileInputStream("DirtBlock.jpg"));
    BufferedImage stone = ImageIO.read(new FileInputStream("StoneBlock.jpg"));
    
    BufferedImage mossy = ImageIO.read(new FileInputStream("Mossy.jpg"));
    BufferedImage cobble = ImageIO.read(new FileInputStream("Cobblestone.jpg"));
    BufferedImage bedrock = ImageIO.read(new FileInputStream("BedrockBlock.jpg"));
    
    BufferedImage furnace = ImageIO.read(new FileInputStream("FurnaceOff.jpg"));
    
    BufferedImage coal = ImageIO.read(new FileInputStream("CoalOre.jpg"));
    BufferedImage iron = ImageIO.read(new FileInputStream("IronOre.jpg"));
    BufferedImage gold = ImageIO.read(new FileInputStream("GoldOre.jpg"));
    BufferedImage diamond = ImageIO.read(new FileInputStream("DiamondOre.jpg"));*/
    
    
    
    public void render() throws Exception{
        
        grass = ImageIO.read(new FileInputStream("GrassBlock.jpg"));
        dirt = ImageIO.read(new FileInputStream("DirtBlock.jpg"));
        stone = ImageIO.read(new FileInputStream("StoneBlock.jpg"));
        
        mossy = ImageIO.read(new FileInputStream("Mossy.jpg"));
        cobble = ImageIO.read(new FileInputStream("Cobblestone.jpg"));
        bedrock = ImageIO.read(new FileInputStream("BedrockBlock.jpg"));
        
        furnace = ImageIO.read(new FileInputStream("FurnaceOff.jpg"));
        
        coal = ImageIO.read(new FileInputStream("CoalOre.jpg"));
        iron = ImageIO.read(new FileInputStream("IronOre.jpg"));
        gold = ImageIO.read(new FileInputStream("GoldOre.jpg"));
        diamond = ImageIO.read(new FileInputStream("DiamondOre.jpg"));
        
        
        if(bf == null){
            bf = (BufferedImage) createImage(this.getWidth()*2, this.getHeight()*2);
        }
        
        dbg = bf.getGraphics();
    
        h.render(dbg, blockID);
        l.render(dbg, blockID);
        
        int n;
        for(int i = 0; i < 80; i++){
            for(n = 0; n < 80; n++){
                
                if(blockID[i][n] == 0){
                    dbg.setColor(new Color(r, g, b));
                    dbg.fillRect(i*25, 1125-(n*25), 25, 25);
                }else if(blockID[i][n] == 1){
                    dbg.drawImage(stone, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 2){
                    dbg.drawImage(grass, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 3){
                    dbg.drawImage(dirt, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 4){
                    dbg.drawImage(coal, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 5){
                    dbg.drawImage(iron, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 6){
                    dbg.drawImage(gold, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 7){
                    dbg.drawImage(diamond, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 8){
                    dbg.drawImage(mossy, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 9){
                    dbg.drawImage(cobble, i*25, 1125-(n*25), 25, 25, this);
                }else if(blockID[i][n] == 12){
                    dbg.drawImage(furnace, i*25, 1125-(n*25), 25, 25, this);
                }
                
            }
            n = 0;
        }
            
        dbg.setColor(Color.GRAY);
        
        s.render(dbg, blockID, blocksX, blocksY);
        
        m.render(dbg, blockID, i);
        
        s.move = false;
        if(inventory){
            i.render(dbg, blocksX, blocksY);
        }else if(furnaceO){
            f.render(dbg, blocksX, blocksY);
        }else{
            hb.render(dbg, blocksX, blocksY, notches);
        }
        
        if(menuScreen){
            ms.render(dbg, blocksX, blocksY);
        }
        
        notches = 0;
        
        time++;
        
        if(time % 200 == 0 && hueInc){
            
            if(g < 50){
                g+=3;
                b+=6;
            }else if(g >= 50){
                r+=4;
                g+=+3;
            }
            
            if(g >= 60){
                hueInc = false;
            }
            
        }else if(time % 200 == 0 && !hueInc){
            if(r > 0){
                r-=4;
                g-=3;
            }else if(g > 0){
                g-=3;
                b-=6;
            }
            
            if(b <= 10){
                hueInc = true;
            }
            
        }
        
        setCursor(sc.setCursor());
    }
    
    public void keyPressed(KeyEvent e){
        
        if(!menuScreen){
            
            if(!inventory){
                if(e.getKeyCode() == e.VK_A){
                    
                    s.hg.hungerCounter++;
                    
                    s.spriteDir = false;
                    
                    if(s.isMiddle() && blocksX < 0){
                        blocksX+=25;
                    }else{
                        if(s.x > 0){
                            s.x-=25;
                        }
                    }
                    
                    movePic = true;
                    s.move = true;
                    
                }else if(e.getKeyCode() == e.VK_D){
                    
                    s.hg.hungerCounter++;
                    
                    s.spriteDir = true;
                    
                    if(s.isMiddle() && blocksX > -750){
                        blocksX-=25;
                    }else{
                        if(s.x < 775){
                            s.x+=25;
                        }
                    }
                    
                    s.move = true;
                    movePic = true;
                    
                }else if(e.getKeyCode() == e.VK_S){
                    
                    if(blocksY > -490){
                        blocksY-=25;
                    }
                    
                }else if(e.getKeyCode() == e.VK_W){
                    
                    if(blocksY < 500){
                        blocksY+=25;
                    }
                }
                
            }
        }
        
        if(e.getKeyCode() == e.VK_ESCAPE && menuScreen){
                
            menuScreen = false;
            
        }else if(e.getKeyCode() == e.VK_ESCAPE && !menuScreen){
            
            menuScreen = true;
            
        }
        
    }
    
    boolean inventory = false;
    
    public void keyReleased(KeyEvent e){
    
        if(e.getKeyCode() == e.VK_E && !menuScreen){
            
            if(!inventory && !furnaceO){
                inventory = true;
            }else if(inventory){
                inventory = false;
            }
            
            if(furnaceO){
                furnaceO = false;
            }
            
        }
        
    }
    public void keyTyped(KeyEvent e){}
    
    public void mousePressed(MouseEvent e){
        
        //System.out.println(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23]);
        IDX = ((-blocksX + (e.getX() - (e.getX()%25)))/25);
        IDY = ((((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23);
        
        int posClickedX = (-blocksX + (e.getX() - (e.getX()%25)));
        int spritePosX = (s.x-blocksX)-25;
        
        int posClickedY = (-blocksY + (e.getY() - (e.getY()%25)));
        int spritePosY = (s.y-blocksY)-25;
        
        if(!menuScreen){
        
            
            if(!inventory && sc.defPressed){
            
                boolean mobHit = false;
                
                for(int n = 0; n < m.p.length && !mobHit; n++){
                    if((spritePosX >= posClickedX-25 && spritePosX <= posClickedX+25) &&
                        (posClickedY >= spritePosY-25 && posClickedY <= spritePosY+75)){
                        if(posClickedX == m.p[n].x && posClickedY == m.p[n].y){
                            m.p[n].p.health--;
                            mobHit = true;
                            
                            if(m.p[n].p.health == 0){
                               m.p[n].dead = true;
                               i.rawPorkC++;
                           }
                            
                        }
                    }
                }
                
                for(int n = 0; n < m.c.length && !mobHit; n++){
                    if((spritePosX >= posClickedX-25 && spritePosX <= posClickedX+25) &&
                        (posClickedY >= spritePosY-25 && posClickedY <= spritePosY+75)){
                        if(posClickedX == m.c[n].x && posClickedY == m.c[n].y){
                            m.c[n].c.health--;
                            mobHit = true;
                            
                            if(m.c[n].c.health == 0){
                               m.c[n].dead = true;
                               i.rawBeefC++;
                           }
                            
                        }
                    }
                }
                
                if((spritePosX >= posClickedX-25 && spritePosX <= posClickedX+25) &&
                (posClickedY >= spritePosY-25 && posClickedY <= spritePosY+75)){
                    
                    if(hb.hb.boxNum[hb.boxNum] == 1){
                
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 10){
                            i.cobbleC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 10){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 15){
                            
                            i.coalC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 20){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(hb.hb.boxNum[hb.boxNum] == 2){
                        
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 20){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 7){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 30){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 30){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(hb.hb.boxNum[hb.boxNum] == 3){
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 6){
                            i.cobbleC++;
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 10){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 10){
                            
                            i.coalC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 15){
                            
                            i.ironC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(hb.hb.boxNum[hb.boxNum] == 4){
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 20){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 5){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 30){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 35){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(hb.hb.boxNum[hb.boxNum] == 5){
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 4){
                            i.cobbleC++;
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 10){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 7){
                            
                            i.coalC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 15){
                            
                            i.ironC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                }else if(hb.hb.boxNum[hb.boxNum] == 6){
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 20){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 3){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 30){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 35){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(hb.hb.boxNum[hb.boxNum] == 7){
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 2){
                            i.cobbleC++;
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 20){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 4){
                            
                            i.coalC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 7){
                            
                            i.ironC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }else if(hb.hb.boxNum[hb.boxNum] == 8){
                        if(blockID[IDX][IDY] == 1
                        && e.getClickCount() == 20){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                                
                            }else if(blockID[(-blocksX + (e.getX() - (e.getX()%25)))/25][(((550-(e.getY() - (e.getY()%25)))/25) + (blocksY/25))+23] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(((blockID[IDX][IDY] == 2) ||
                        (blockID[IDX][IDY] == 3))
                        && e.getClickCount() == 1){
                            i.dirtC++;
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 4 && e.getClickCount() == 30){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }else if(blockID[IDX][IDY] == 5 && e.getClickCount() == 35){
                            
                            if(e.getY() < 300){
                            
                                blockID[IDX][IDY] = 0;
                                
                            }else if(blockID[IDX][IDY] != 0){
                                
                                blockID[IDX][IDY] = 9;
                                
                            }
                        }
                    }
            }else if(!inventory){
                
                /*int posClickedX = (-blocksX + (e.getX() - (e.getX()%25)));
                int spritePosX = (s.x-blocksX)-25;
                
                int posClickedY = (-blocksY + (e.getY() - (e.getY()%25)));
                int spritePosY = (s.y-blocksY)-25;*/
                
                if((spritePosX >= posClickedX-25 && spritePosX <= posClickedX+25) &&
                (posClickedY >= spritePosY-25 && posClickedY <= spritePosY+75)){
                    
                    if(e.getModifiers() == InputEvent.BUTTON1_MASK){
                        
                        if(sc.dirtPressed && i.dirtC > 0 && ((blockID[IDX][IDY] == 0)
                        || (blockID[IDX][IDY] == 9))){
                            
                            blockID[IDX][IDY] = 3;
                            i.dirtC--;
                            
                        }else if(sc.cobblePressed && i.cobbleC > 0 && (blockID[IDX][IDY] == 0)){
                            
                            blockID[IDX][IDY] = 9;
                            i.cobbleC--;
                            
                        }else if(sc.furnacePressed && i.furnaceC > 0 && (blockID[IDX][IDY] == 0)){
                            
                            blockID[IDX][IDY] = 12;
                            i.furnaceC--;
                            
                        }
                    }
                    
                    if(blockID[IDX-1][IDY-2] != 0){
                    
                        movePic = true;
                        
                    }
                    
                }
            }else if(inventory){
                
                int arr = 0;
                
                int xPos = 165, yPos = 340;
                
                boolean loopEnd = false;
                
                if(sc.defPressed){
                
                    try{
                        if(e.getModifiers() == InputEvent.BUTTON1_MASK){
                            while(arr < 16){
                                
                                if(e.getX() > xPos && e.getX() < xPos+55 && e.getY() > yPos && e.getY() < yPos+55){
                                    
                                    if(i.invBox[arr] == 3){
                                        
                                        sc.dirtPressed = true;
                                        sc.cobblePressed = false;
                                        sc.furnacePressed = false;
                                        sc.defPressed = false;
                                        
                                    }else if(i.invBox[arr] == 9){
                                        
                                        sc.dirtPressed = false;
                                        sc.cobblePressed = true;
                                        sc.furnacePressed = false;
                                        sc.defPressed = false;
                                        
                                    }else if(i.invBox[arr] == 12){
                                        
                                        sc.dirtPressed = false;
                                        sc.cobblePressed = false;
                                        sc.furnacePressed = true;
                                        sc.defPressed = false;
                                        
                                    }
                                    
                                    loopEnd = true;
                                    
                                }
                                
                                xPos+=55;
                                
                                if(xPos > 595){
                                    xPos = 165;
                                    yPos+=60;
                                }
                                
                                arr++;
                                
                            }
                        }
                        
                        
                        if(e.getModifiers() == InputEvent.BUTTON3_MASK){
                            
                            while(arr < 16){
                                
                                if(e.getX() > xPos && e.getX() < xPos+55 && e.getY() > yPos && e.getY() < yPos+55){
                                    
                                    if(i.invBox[arr] == 50 && s.hg.hunger < 20){
                                        
                                        s.hg.hunger+=3;
                                        i.rawPorkC--;
                                        
                                    }
                                    
                                    loopEnd = true;
                                    
                                }
                                
                                xPos+=55;
                                
                                if(xPos > 595){
                                    xPos = 165;
                                    yPos+=60;
                                }
                                
                                arr++;
                                
                            }
                            
                        }
                        
                        arr = 0;
                        
                        xPos = 165;
                        yPos = 340;
                        
                        if(e.getModifiers() == InputEvent.BUTTON3_MASK){
                            
                            while(arr < 16){
                                
                                if(e.getX() > xPos && e.getX() < xPos+55 && e.getY() > yPos && e.getY() < yPos+55){
                                    
                                    if(i.invBox[arr] == 51 && s.hg.hunger < 20){
                                        
                                        s.hg.hunger+=3;
                                        i.rawBeefC--;
                                        
                                    }
                                    
                                    loopEnd = true;
                                    
                                }
                                
                                xPos+=55;
                                
                                if(xPos > 595){
                                    xPos = 165;
                                    yPos+=60;
                                }
                                
                                arr++;
                                
                            }
                            
                        }
                    }catch(Exception err){}
                }
                
                if(inventory){
                    
                    if(!sc.defPressed){
                    
                        if(e.getModifiers() == InputEvent.BUTTON1_MASK){
                        
                            int x = 400, y = 140;
                            int countCraft = 0;
                            
                            while(x != 400 || y != 305){
                            
                                
                                if(e.getX() > x && e.getX() < (x+50) && e.getY() > y && e.getY() < (y+50)){
                                    
                                    if(sc.cobblePressed){
                                        i.blockCraft[countCraft] = 9;
                                    }else if(sc.dirtPressed){
                                        i.blockCraft[countCraft] = 1;
                                    }
                                    
                                }
                                
                                x+=55;
                        
                                if(x == 565){
                                    y+=55;
                                    x = 400;
                                }
                                
                                countCraft++;
                            }
                        }
                    }else{
                        if(e.getModifiers() == InputEvent.BUTTON3_MASK){
                            int x = 400, y = 140;
                            int countCraft = 0;
                            
                            while(x != 400 || y != 305){
                            
                                if(e.getX() > x && e.getX() < (x+50) && e.getY() > y && e.getY() < (y+50)){
                                    i.blockCraft[countCraft] = 0;
                                }
                                
                                x+=55;
                        
                                if(x == 565){
                                    y+=55;
                                    x = 400;
                                }
                                
                                countCraft++;
                            }
                        }else if(e.getModifiers() == InputEvent.BUTTON1_MASK){
                            
                            if(e.getX() > 605 && e.getX() < 655 && e.getY() > 190 && e.getY() < 240){
                            
                                if(i.blockCraft[0] == 0 && i.blockCraft[1] == 9 && i.blockCraft[2] == 0 && 
                                i.blockCraft[3] == 0 && i.blockCraft[4] == 9 && i.blockCraft[5] == 0 && 
                                i.blockCraft[6] == 0 && i.blockCraft[7] == 9 && i.blockCraft[8] == 0 && i.cobbleC >= 3){
                                    
                                    i.cobbleC-=3;
                                    hb.hb.toolsCrafted[3] = true;
                                    
                                    for(int n = 0; n < i.blockCraft.length; n++){
                                        i.blockCraft[n] = 0;
                                    }
                                    
                                }else if(i.blockCraft[0] == 9 && i.blockCraft[1] == 9 && i.blockCraft[2] == 9 && 
                                i.blockCraft[3] == 0 && i.blockCraft[4] == 9 && i.blockCraft[5] == 0 && 
                                i.blockCraft[6] == 0 && i.blockCraft[7] == 9 && i.blockCraft[8] == 0 && i.cobbleC >= 5){
                                    
                                    i.cobbleC-=5;
                                    hb.hb.toolsCrafted[2] = true;
                                    
                                    for(int n = 0; n < i.blockCraft.length; n++){
                                        i.blockCraft[n] = 0;
                                    }
                                    
                                }else if(i.blockCraft[0] == 9 && i.blockCraft[1] == 9 && i.blockCraft[2] == 9 && 
                                i.blockCraft[3] == 9 && i.blockCraft[4] == 0 && i.blockCraft[5] == 9 && 
                                i.blockCraft[6] == 9 && i.blockCraft[7] == 9 && i.blockCraft[8] == 9 && i.cobbleC >= 8){
                                    
                                    i.cobbleC-=8;
                                    i.furnaceC++;
                                    
                                    for(int n = 0; n < i.blockCraft.length; n++){
                                        i.blockCraft[n] = 0;
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
            
            if((spritePosX >= posClickedX-25 && spritePosX <= posClickedX+25) &&
                (posClickedY >= spritePosY-25 && posClickedY <= spritePosY+75)){
                if(e.getModifiers() == InputEvent.BUTTON3_MASK){
                    
                    if(blockID[IDX][IDY] == 12){
                        furnaceO = true;
                    }
                    
                }
            }
    
            if(e.getModifiers() == InputEvent.BUTTON3_MASK && !sc.defPressed){
                
                sc.dirtPressed= false;
                sc.cobblePressed = false;
                sc.furnacePressed = false;
                sc.defPressed = true;
            
            }
            if(e.getModifiers() == InputEvent.BUTTON1_MASK && sc.dirtPressed && i.dirtC == 0){
                
                sc.dirtPressed= false;
                sc.cobblePressed = false;
                sc.furnacePressed = false;
                sc.defPressed = true;
                
            }
            if(e.getModifiers() == InputEvent.BUTTON1_MASK && sc.cobblePressed && i.cobbleC == 0){
                
                sc.dirtPressed= false;
                sc.cobblePressed = false;
                sc.furnacePressed = false;
                sc.defPressed = true;
                
            }
            if(e.getModifiers() == InputEvent.BUTTON1_MASK && sc.furnacePressed && i.furnaceC == 0){
                
                sc.dirtPressed= false;
                sc.cobblePressed = false;
                sc.furnacePressed = false;
                sc.defPressed = true;
                
            }
            
        }else{
            
            if(e.getX() > 200 && e.getX() < 600 && e.getY() > 75 && e.getY() < 145){
                menuScreen = false;
            }else if(e.getX() > 200 && e.getX() < 600 && e.getY() > 175 && e.getY() < 245 && !lf.load){
                lf.load = true;
            }else if(e.getX() > 200 && e.getX() < 600 && e.getY() > 275 && e.getY() < 345 && !sf.save){
                sf.save = true;
            }else if(e.getX() > 200 && e.getX() < 600 && e.getY() > 375 && e.getY() < 445){
                endGame = true;
            }
            
        }
        
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    
    public void mouseWheelMoved(MouseWheelEvent e){
        notches = e.getWheelRotation();
    }

}