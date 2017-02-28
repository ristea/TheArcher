package arcasul.Entity;

import arcasul.GUI.GUI;

/**
 *
 * @author Lydya0103
 */
public class Player implements EntityI{
    private static final int x = 5;
    private static int VelY = 0;
    
    private int y;
    
    public Player()
    {
        y = GUI.HEIGHT / 2;
    }
    
    public void update()
    {
        if(y > 0 && y < GUI.HEIGHT - 40 )
            y += VelY;
        if(y <= 0)
            y = 1;
        if(y>= GUI.HEIGHT - 40)
            y = GUI.HEIGHT - 41;
    } 
    
    public void movePlayer(String move) {
        switch (move) {
            case "UP":
                VelY = -10;
                break;
            case "DOWN":
                VelY = 10;
                break;
            case "STOP":
                VelY = 0;
                break;
            default:
                break;
        }
    }    

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
