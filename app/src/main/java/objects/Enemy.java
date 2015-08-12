package objects;

import processing.core.PShape;

/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends Obj
{

    float xSpeed;
    float ySpeed;

    public Enemy(PShape shape)
    {
        super(shape);
        xSpeed = 0;
        ySpeed = 0;
    }

    public void update()
    {
        move();
    }

    public void move()
    {
        x+=xSpeed;
        y+=ySpeed;
        shape.translate(xSpeed,ySpeed);
    }
}
