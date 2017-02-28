package arcasul.Balon;

import arcasul.GUI.GUI;

/**
 *
 * @author Lydya0103
 */
public class Balon5 extends Balon {
    private static final int POINTS = 5;
    
    public Balon5(int _VelY) {
        super(_VelY);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

    @Override
    public void update() {
        y += VelY;
        if(y >= GUI.HEIGHT + 50)
        {
            setIsFlying(false);
            setIsCollided(true);
        }
    }    
}
