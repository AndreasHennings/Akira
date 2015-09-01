package objects;


import processing.core.PShape;
import processing.test.akira_neu.GameActivity;

/**
 * Created by aend on 08.08.15.
 */
public abstract class AbstractStaticObject
{
    PShape shape;
    char name;

    //position and dimension
    float x;
    float y;
    float w;
    float h;

    float x1;
    float y1;

    public AbstractStaticObject(PShape shape)
    {
        this.shape = shape;
        name='0';

        float[] params = shape.getParams();
        this.x=params[0];
        this.y=params[1];
        this.w=params[2];
        this.h=params[3];

        x1=x+w;
        y1=y+h;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getW()
    {
        return w;
    }

    public float getH()
    {
        return h;
    }

    public float getX1()
    {
        return x+w;
    }

    public float getY1()
    {
        return y+h;
    }





}
