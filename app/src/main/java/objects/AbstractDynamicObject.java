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
        shape.translate(newX-x,0);
        x= newX;
    }

    public void setY(float newY)
    {
        shape.translate(0,newY-y);
        y= newY;
    }


    void move(float xSpeed, float ySpeed)
    {
        if (x<0)
        {
            setX(10);
            xSpeed=10;
        }

        if (x+w>xMax)
        {
            setX(xMax-10);
            xSpeed=-10;
        }

        if (y<0)
        {
            setY(10);
            ySpeed=10;
        }

        if (y+h>yMax)
        {
            setY(yMax-10);
            ySpeed=-10;
        }

        setX(x+xSpeed);
        setY(y+ySpeed);
        /*
        x+=xSpeed;
        y+=ySpeed;
        shape.translate(xSpeed, ySpeed);
        */
    }

}
