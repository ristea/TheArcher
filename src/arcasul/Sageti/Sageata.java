package arcasul.Sageti;

import arcasul.Entity.EntityI;

/**
 *
 * @author Lydya0103
 */
public abstract class Sageata implements EntityI{
    protected int x,y;        
    protected boolean isFlying;
    protected boolean isCollided;
    protected boolean ArrowInArch;
    
    public Sageata(int _y)
    {
        y = _y;
        isFlying = false;
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
        if(getArrowType() == 1)            
            return;
        else
            isCollided = _isCollided;
    }
    
    public boolean getArrowInArch() {
        return ArrowInArch;
    }
    
    public void setArrowInArch(boolean _ArrowInArch) {
        ArrowInArch = _ArrowInArch;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    public void setY(int _y) {
        y = _y;
    }

    public abstract String getId();
    
    public abstract void update();

    public int getArrowType() {
        if (this instanceof SageataStandard) 
            return 0;
        else 
            return 1;   
    }
}
