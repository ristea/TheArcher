package arcasul.Balon;

import arcasul.GUI.GUI;

/**
 *
 * @author Lydya0103
 */
public class Balon10 extends Balon {
    private static final int POINTS = 10;
    
    public Balon10(int _VelY) {
        super(_VelY);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

    @Override
    public void update() {
        
        if(y >= GUI.HEIGHT + 50)
        {
            setIsFlying(false);
            setIsCollided(true);
        }
        else
            y += VelY;
    }   
}
