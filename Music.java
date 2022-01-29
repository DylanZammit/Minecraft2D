import javax.media.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Music{
    
    File f=new File("MinecraftMusic.wav"); 
    Player p = Manager.createRealizedPlayer(f.toURI().toURL());
         
    public Music() throws Exception{
        
        p.start();
         
    }
}
