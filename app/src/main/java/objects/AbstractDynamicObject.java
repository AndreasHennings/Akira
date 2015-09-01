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

    public float getCenterX()
    {
        return x+(w/2);
    }

    public float getCenterY()
    {
        return y+(h/2);
    }


    public AbstractDynamicObject(PShape shape)
    {
        super(shape);
        xSpeed=0;
        ySpeed=0;
    }

    public void setSpeed(float xSpeed, float ySpeed)
    {
        this.xSpeed+=xSpeed;
        this.ySpeed+=ySpeed;
    }

    public void update()
    {
        x+=xSpeed;
        y+=ySpeed;
        shape.translate(xSpeed,ySpeed);
    }

}
