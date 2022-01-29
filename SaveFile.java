import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class SaveFile extends JFrame implements ActionListener{
    
    String title;
    
    boolean save;
    
    private JTextField txtName;
    
    public SaveFile() throws Exception{}
    
    LandGen lg;
    
    public SaveFile(LandGen lg) throws Exception{
        
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
                
                FileWriter f = new FileWriter(title + ".txt", false);
            
                int n;
                for(int i = 0; i < 80; i++){
                    for(n = 0; n < 80; n++){
                        f.write(Integer.toString(lg.blockID[i][n]) + "\r\n");
                    }
                }
                
                f.write(Integer.toString(lg.s.he.health) + "\r\n");
                f.write(Integer.toString(lg.s.hg.hunger) + "\r\n");
                f.write(Integer.toString(lg.s.x) + "\r\n");
                f.write(Integer.toString(lg.s.y) + "\r\n");
                f.write(Integer.toString(lg.blocksX) + "\r\n");
                f.write(Integer.toString(lg.blocksY) + "\r\n");
                
                f.write(Integer.toString(lg.i.stoneC) + "\r\n");
                f.write(Integer.toString(lg.i.dirtC) + "\r\n");
                f.write(Integer.toString(lg.i.coalC) + "\r\n");
                f.write(Integer.toString(lg.i.ironC) + "\r\n");
                f.write(Integer.toString(lg.i.goldC) + "\r\n");
                f.write(Integer.toString(lg.i.diamondC) + "\r\n");
                f.write(Integer.toString(lg.i.cobbleC) + "\r\n");
                f.write(Integer.toString(lg.i.rawPorkC) + "\r\n");
                f.write(Integer.toString(lg.i.furnaceC) + "\r\n");
                
                f.write(Integer.toString(lg.m.pigNum) + "\r\n");
                
                for(int x = 0; x < lg.m.pigNum; x++){
                    
                    f.write(Boolean.toString(lg.m.p[x].dead) + "\r\n");
                
                    f.write(Integer.toString(lg.m.p[x].x) + "\r\n");
                    f.write(Integer.toString(lg.m.p[x].y) + "\r\n");
                    f.write(Integer.toString(lg.m.p[x].p.health) + "\r\n");
                        
                }
                
                f.write(Integer.toString(lg.m.cowNum) + "\r\n");
                
                for(int x = 0; x < lg.m.cowNum; x++){
                    
                    f.write(Boolean.toString(lg.m.c[x].dead) + "\r\n");
                
                    f.write(Integer.toString(lg.m.c[x].x) + "\r\n");
                    f.write(Integer.toString(lg.m.c[x].y) + "\r\n");
                    f.write(Integer.toString(lg.m.c[x].c.health) + "\r\n");
                        
                }
                
                f.write(Integer.toString(lg.r) + "\r\n");
                f.write(Integer.toString(lg.g) + "\r\n");
                f.write(Integer.toString(lg.b) + "\r\n");
                
                save = false;
                f.close();
                
                setVisible(false);
                
                JOptionPane.showMessageDialog(null, "Saved!");
                
            }
        }catch(IOException ex){
        
            JOptionPane.showMessageDialog(null, "Error while saving!!");
        
        }
        
    }
    
}