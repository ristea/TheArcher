package arcasul.Balon;

import arcasul.Entity.EntityI;
import arcasul.GUI.GUI;
import java.util.Random;

/**
 *
 * @author Lydya0103
 */
public abstract class Balon implements EntityI{
    protected int x,y;
    protected int VelY;
    protected boolean isFlying;
    protected boolean isCollided;
    
    public Balon(int _VelY)
    {
        setRandomPozitieX();
        VelY = _VelY;
        isFlying = false;
        isCollided = false;
        y = 0;
    }
    
    private void setRandomPozitieX()
    {
        Random rand = new Random();
        x = rand.nextInt(GUI.WIDTH - 100) + 100;
    }
    
    public boolean getIsFlying() {
        return isFlying;
    }
    
    public void setIsFlying(boolean _isFlying) {
        isFlying = _isFlying;
    }
    
    public boolean getIsCollided() {
        return isCollided;
    }
    
    public void setIsCollided(boolean _isCollided) {
        isCollided = _isCollided;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    public int getBaloonType()
    {
        if (this instanceof Balon5) 
            return 0;
        if (this instanceof Balon10) 
            return 1;
        else 
            return 2;
    }
    
    public abstract int getPoints();
    public abstract void update();
}
