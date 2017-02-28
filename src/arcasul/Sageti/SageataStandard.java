package arcasul.Sageti;

import arcasul.GUI.GUI;

/**
 *
 * @author Lydya0103
 */
public class SageataStandard extends Sageata{
    private static final int VelX = 7;
    
    public SageataStandard(int _y) {
        super(_y);
    }

    @Override
    public void update() {
        x += VelX;
        if(x >= GUI.WIDTH + 50)
        {
            setIsFlying(false);
            setIsCollided(true);
        }
    }

    @Override
    public String getId() {
        return "SAGEATA_STANDARD";
    }
}
