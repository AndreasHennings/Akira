package objects;

import processing.core.PShape;

/**
 * Created by judith on 11.08.2015.
 */
public class Enemies extends Obj
{
    PShape shape;
    double xSpeed;
    double ySpeed;
    public Enemies(PShape shape, double x,double y,double w,double h)
    {
        super(x,y,w,h);
        this.shape=shape;
        xSpeed=1;
        ySpeed=0;
    }

    public void update()
    {
        shape.translate((float)xSpeed,(float)ySpeed);

    }
}
