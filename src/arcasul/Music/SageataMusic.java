package arcasul.Music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author Lydya0103
 */
public class SageataMusic extends Thread{
    private static final String ArrowMusic = "resources" + File.separator + "ArrowSound.mp3";
    
    @Override
    public void run() {
        // aici trebuie numele si calea musicSageata
        File fl = new File(ArrowMusic);
        try {
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player;            
            player = new Player(bis);
            player.play();
           
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
