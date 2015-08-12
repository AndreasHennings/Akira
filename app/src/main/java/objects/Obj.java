package objects;

import processing.core.PShape;

/**
 * Created by aend on 08.08.15.
 */
public abstract class Obj
{
    PShape shape;
    String id;

    //position and dimension
    float x;
    float y;
    float w;
    float h;


    public Obj (PShape shape)
    {
        this.shape = shape;

        id=shape.getName();

        float[] params = shape.getParams();

        this.x=params[0];
        this.y=params[1];
        this.w=params[2];
        this.h=params[3];
    }

}
