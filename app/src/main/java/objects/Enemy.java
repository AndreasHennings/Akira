package objects;

import processing.core.PShape;


/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends AbstractDynamicObject
{

    public Enemy(PShape shape)
    {
        super(shape);
        name='e';
        xSpeed = 0;
        ySpeed = 0;
    }

    public void update(float xMove, float yMove)
    {
        xSpeed+=xMove;
        ySpeed+=yMove;
        move(xSpeed, ySpeed);
    }

}