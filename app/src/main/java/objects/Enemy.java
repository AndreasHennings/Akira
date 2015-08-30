package objects;

import processing.core.PShape;
import processing.test.akira_neu.GameActivity;

/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends AbstractDynamicObject
{

    public Enemy(PShape shape, float xMax, float yMax)
    {
        super(shape, xMax, yMax);
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