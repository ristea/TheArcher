package arcasul.Music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author Lydya0103
 */
public class CollisionMusic extends Thread{
    private static final String COLLISION_MUSIC = "resources" + File.separator + "BalloonPopSoundEffect.mp3";
    
    @Override
    public void run() {       
        File fl = new File(COLLISION_MUSIC);
        try {
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player;            
            player = new Player(bis);
            player.play();
            Thread.currentThread().join();            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
