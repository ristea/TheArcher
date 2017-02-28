package arcasul.Level;

import arcasul.Balon.Balon;
import arcasul.Music.CollisionMusic;
import arcasul.Sageti.Sageata;
import java.awt.Rectangle;

/**
 *
 * @author Lydya0103
 */
public class Collision {
    private static final int SAGEATA_HEIGHT = 32, SAGEATA_WIDTH = 96;
    private static final int BALON_HEIGHT = 32, BALON_WIDTH = 32;   
    private static CollisionMusic collisionMusic;
    
    public boolean areColliding(Sageata sageata, Balon balon)
    {
        Rectangle sageataRect = new Rectangle(sageata.getX(), sageata.getY(), SAGEATA_WIDTH, SAGEATA_HEIGHT);
        Rectangle balonRect = new Rectangle(balon.getX(), balon.getY(), BALON_WIDTH, BALON_HEIGHT);
        
        if(sageata.getId().equals("SAGEATA_STANDARD"))
        {
            if(sageataRect.intersects(balonRect))
            {
                collisionMusic = new CollisionMusic();
                collisionMusic.start();
                sageata.setIsFlying(false);
                sageata.setIsCollided(true);
                balon.setIsFlying(false);
                balon.setIsCollided(true);
                return true;
            }            
            else
                return false;
        }
        else
        {
            // Aici verifica sagetile Premium, care trec prin toate baloanele
            if(sageataRect.intersects(balonRect))
            {
                collisionMusic = new CollisionMusic();
                collisionMusic.start();
                balon.setIsFlying(true);
                balon.setIsCollided(false);
                return true;
            }            
            else
                return false;
        }
    }
}
