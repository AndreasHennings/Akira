package objects;

import processing.*;
import processing.core.PShape;
import processing.test.akira_neu.GameActivity;

/**
 * Created by aend on 16.08.15.
 */
public abstract class AbstractDynamicObject extends AbstractStaticObject
{
    float xSpeed;
    float ySpeed;

    // size of level:

    float xMax;
    float yMax;

    public AbstractDynamicObject(PShape shape, float xMax, float yMax)
    {
        super(shape);
        this.xMax=xMax;
        this.yMax=yMax;
    }

    public void setX(float newX)
    {
        //shape.translate(newX-x,0);
        x= newX;
    }

    public void setY(float newY)
    {
        //shape.translate(0,newY-y);
        y= newY;
    }


    void move(float xSpeed, float ySpeed)
    {
        setX(x+xSpeed);
        setY(y+ySpeed);
    }

}
