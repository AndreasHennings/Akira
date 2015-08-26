package objects;

import processing.core.PShape;

/**
 * Created by aend on 08.08.15.
 */
public abstract class AbstractStaticObject
{
    PShape shape;

    //position and dimension
    float x;
    float y;
    float w;
    float h;

    public AbstractStaticObject(PShape shape)
    {
        this.shape = shape;

        float[] params = shape.getParams();
        this.x=params[0];
        this.y=params[1];
        this.w=params[2];
        this.h=params[3];
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getX1()
    {
        return x+w;
    }

    public float getY1()
    {
        return y+h;
    }

    public float getCenterX()
    {
        return x+(w/2);
    }

    public float getCenterY()
    {
        return y+(h/2);
    }

}
