import java.awt.*;

public class MenuScreen extends Canvas{
    
    int my;
    
    String tags[] = {"Resume Game", "Load Game", "Save Game", "Exit Game"};
    
    public MenuScreen() throws Exception{}
    
    public void render(Graphics g, int x, int y){
        
        my = 75;
        
        g.setColor(new Color(0, 0, 0, 200));
        
        g.fillRect(0, 0, 800-x, 600-y);
        
        g.setFont(new Font(null, Font.PLAIN, 15));
        
        g.drawString("Game Paused", 350-x, 50-y);
        
        g.setFont(new Font(null, Font.PLAIN, 20));
        
        for(int i = 0; i < 4; i++){
            
            g.setColor(Color.GRAY);
            
            g.fillRect(200-x, my-y, 400, 70);
            
            g.setColor(Color.BLACK);
            
            g.drawString(tags[i], 340-x, my-y+40);
            
            my+=100;
            
        }
        
    }
    
}