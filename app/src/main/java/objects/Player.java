package objects;

import processing.core.PShape;



/**
 * Created by aend on 12.08.15.
 */
public class Player extends AbstractDynamicObject
{
    public int facing;
    public int health;
    private int gold;

    public Player(PShape shape)
    {
        super(shape);
        health=100;
        gold=0;
        facing=1;
    }

    public float getCenterX()
    {
        return x+(w/2);
    }

    public float getCenterY()
    {
        return y+(h/2);
    }

    public int getHealth()
    {
        return health;
    }

    public int getGold()
    {
        return gold;
    }

    public void update()
    {
        super.update();
        ySpeed+=0.1;
    }



    public void collideBlock(AbstractStaticObject other)
    {
        if (other.name=='e') {health--;}
        if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()||y+ySpeed>other.getY1()||y+h+ySpeed<other.getY()))
        {
            if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()))
            {
                xSpeed=0;
            }

            if (!(y+ySpeed>other.getY1())||y+h+ySpeed<other.getY())
            {
                ySpeed=0;
            }


        }

    }

    public void collideEnemy(Enemy other)
    {
        if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()||y+ySpeed>other.getY1()||y+h+ySpeed<other.getY()))
        {
            health--;
            if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()))
            {
                xSpeed=0;
            }

            if (!(y+ySpeed>other.getY1()||y+h+ySpeed<other.getY()))
            {
                ySpeed=0;
            }
        }


    }



}
