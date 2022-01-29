import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class LoadFile extends JFrame implements ActionListener{
    
    private JTextField txtName;
    
    String title;
    
    boolean load;
    
    public LoadFile() throws Exception{}
    
    LandGen lg;
    
    public LoadFile(LandGen lg) throws Exception{
    
        this.lg = lg;
        
        setTitle("WorldName");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        
        txtName = new JTextField();
        txtName.setBounds(50, 25, 200, 20);
        add(txtName);
        
        txtName.addActionListener(this);
    
    }
    
    public void actionPerformed(ActionEvent e){
        
        try{
        
            if(e.getSource() == txtName){
                
                title = txtName.getText();
                
                FileReader f = new FileReader(title + ".txt");
                BufferedReader fr = new BufferedReader(f);
                
                int n;
                for(int i = 0; i < 80; i++){
                    for(n = 0; n < 80; n++){
                        
                        lg.blockID[i][n] = Integer.parseInt(fr.readLine());
                        
                    }
                }
                
                lg.s.he.health = Integer.parseInt(fr.readLine());
                lg.s.hg.hunger = Integer.parseInt(fr.readLine());
                lg.s.x = Integer.parseInt(fr.readLine());
                lg.s.y = Integer.parseInt(fr.readLine());
                lg.blocksX = Integer.parseInt(fr.readLine());
                lg.blocksY = Integer.parseInt(fr.readLine());
                
                lg.i.stoneC = Integer.parseInt(fr.readLine());
                lg.i.dirtC = Integer.parseInt(fr.readLine());
                lg.i.coalC = Integer.parseInt(fr.readLine());
                lg.i.ironC = Integer.parseInt(fr.readLine());
                lg.i.goldC = Integer.parseInt(fr.readLine());
                lg.i.diamondC = Integer.parseInt(fr.readLine());
                lg.i.cobbleC = Integer.parseInt(fr.readLine());
                lg.i.rawPorkC = Integer.parseInt(fr.readLine());
                lg.i.furnaceC = Integer.parseInt(fr.readLine());
                
                lg.m.pigNum = Integer.parseInt(fr.readLine());
                
                for(int x = 0; x < lg.m.pigNum; x++){
                    
                    lg.m.p[x].dead = Boolean.parseBoolean(fr.readLine());
                
                    lg.m.p[x].x = Integer.parseInt(fr.readLine());
                    lg.m.p[x].y = Integer.parseInt(fr.readLine());
                    lg.m.p[x].p.health = Integer.parseInt(fr.readLine());
                        
                }
                
                lg.m.cowNum = Integer.parseInt(fr.readLine());
                
                for(int x = 0; x < lg.m.cowNum; x++){
                    
                    lg.m.c[x].dead = Boolean.parseBoolean(fr.readLine());
                    
                    lg.m.c[x].x = Integer.parseInt(fr.readLine());
                    lg.m.c[x].y = Integer.parseInt(fr.readLine());
                    lg.m.c[x].c.health = Integer.parseInt(fr.readLine());
                        
                }
                
                lg.r = Integer.parseInt(fr.readLine());
                lg.g = Integer.parseInt(fr.readLine());
                lg.b = Integer.parseInt(fr.readLine());
                
                load = false;
                
                setVisible(false);
                
                fr.close();
                
            }
            
        }catch(IOException ex){
            
            JOptionPane.showMessageDialog(null, "Error while loading! Check if file name exists.");
            
        }
        
    }
    
}