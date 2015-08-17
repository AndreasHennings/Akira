package objects;

import processing.core.PShape;

/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends AbstractDynamicObject
{


    public Enemy(PShape shape, float rnd)
    {
        super(shape);
        xSpeed = rnd;
        ySpeed = 0;
    }

    public void update(StaticBlock[]staticBlock)
    {
        super.update(staticBlock);
    }




}