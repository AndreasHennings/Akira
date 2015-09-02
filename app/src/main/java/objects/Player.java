package objects;

import java.util.ArrayList;

import processing.core.PShape;



/**
 * Created by aend on 12.08.15.
 */
public class Player extends AbstractObject
{
    public int facing;
    public int health;
    private int gold;

    public Player(PShape shape, PShape img)
    {
        super(shape, img);
        health=100;
        gold=0;
        facing=1;
    }

    public int getHealth()
    {
        return health;
    }

    public int getGold()
    {
        return gold;
    }

    public void update(ArrayList<AbstractObject>others)
    {

        setYSpeed(ySpeed+(float)0.3);
        collision(others);
        super.update();
    }



    public void collision(ArrayList<AbstractObject> others)
    {
        for (int i=0; i<others.size(); i++)
        {
            AbstractObject other= others.get(i);

            if (!(x+xSpeed > other.getX1() || getX1()+xSpeed < other.getX() || y+ySpeed > other.getY1() || getY1()+ySpeed < other.getY()))
            {
                if (other.type == 'e')
                {
                    img.setVisible(false);
                    health--;
                    img.setVisible(true);
                }

                if (other.type == 'b')
                {
                    if (!(x + xSpeed > other.getX1() || getX1() + xSpeed < other.getX()))
                    {
                        setXSpeed(0);
                    }

                    if (!(y + ySpeed > other.getY1()) || getY1() + ySpeed < other.getY())
                    {
                        setYSpeed(0);
                    }
                }
            }
        }
    }
}
