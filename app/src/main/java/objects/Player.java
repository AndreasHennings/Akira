package objects;

import processing.core.PShape;


/**
 * Created by aend on 12.08.15.
 */
public class Player extends Obj
{
    private int health;
    private int gold;

    public Player(PShape shape)
    {
        super(shape);
        health=100;
        gold=0;
    }

    public int getHealth()
    {
        return health;
    }

    public int getGold()
    {
        return gold;
    }

    public void move(float xSpeed, float ySpeed)
    {
        x+=xSpeed;
        y+=ySpeed;
        shape.translate(xSpeed,ySpeed);
    }

}
