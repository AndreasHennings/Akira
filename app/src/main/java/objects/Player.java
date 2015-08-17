package objects;

import processing.core.PShape;


/**
 * Created by aend on 12.08.15.
 */
public class Player extends AbstractDynamicObject
{
    private int health;
    private int gold;

    public Player(PShape shape)
    {
        super(shape);
        health=100;
        gold=0;
    }

    public void update(StaticBlock[]staticBlock)
    {
        super.update(staticBlock);
        xSpeed*=0.99;
    }

    public int getHealth()
    {
        return health;
    }

    public int getGold()
    {
        return gold;
    }

    public void setSpeed(float xSpeed, float ySpeed)
    {
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
    }



}
