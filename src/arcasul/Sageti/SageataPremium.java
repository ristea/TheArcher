package arcasul.Sageti;

import arcasul.GUI.GUI;

/**
 *
 * @author Lydya0103
 */
public class SageataPremium extends Sageata{
    private static final int VEL_X = 7;
    
    public SageataPremium(int _y) {
        super(_y);
    }

    @Override
    public void update() {
        x += VEL_X;
        if(x >= GUI.WIDTH + 50)
        {
            setIsFlying(false);
            setIsCollided(true);
        }
    }

    @Override
    public String getId() {
        return "SAGEATA_PREMIUM";
    }
}
