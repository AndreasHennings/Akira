package objects;

import processing.core.PShape;

/**
 * Created by judith on 11.08.15.
 */
public class Enemies extends Obj
{

    double xSpeed;
    double ySpeed;

    public Enemies(PShape shape)
    {
        super(shape);
        xSpeed = 1;
        ySpeed = 0;
    }

    public void update()
    {
        shape.translate((float) xSpeed, (float) ySpeed);
    }
}
