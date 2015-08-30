package objects;

import processing.core.PShape;
import processing.test.akira_neu.GameActivity;


/**
 * Created by aend on 12.08.15.
 */
public class Player extends AbstractDynamicObject
{
    public int facing;
    public int health;
    private int gold;

    public Player(PShape shape, float xMax, float yMax)
    {
        super(shape, xMax, yMax);
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

    /*
    public float getXSpeed() { return xSpeed;}
    public float getYSpeed() { return ySpeed;}

    */

    public void setSpeed(float xSpeed, float ySpeed)
    {
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
    }

    public void update(StaticBlock[]staticBlock, Enemy[] enemies)
    {
        for (int i = 0; i < staticBlock.length; i++)
        {
            collideBlock(staticBlock[i]);
        }


        for (int i = 0; i < enemies.length; i++)
        {
            collideEnemy(enemies[i]);
        }


        move(xSpeed, ySpeed);

        ySpeed+=0.1;
    }

    private void collideBlock(StaticBlock other)
    {
        if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()||y+ySpeed>other.getY1()||y+h+ySpeed<other.getY()))
        {
            if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()))
            {
                xSpeed=0;
            }

            if (!(y+ySpeed>other.getY1()||y+h+ySpeed<other.getY()))
            {
                ySpeed=0;

            }
            move(xSpeed, ySpeed);
        }
    }

    private void collideEnemy(Enemy other)
    {
        /*
        if (!(x+xSpeed>other.getX1()||x+w+xSpeed<other.getX()||y+ySpeed>other.getY1()||y+h+ySpeed<other.getY()))
        {
            health--;
        }
        */
    }



}
