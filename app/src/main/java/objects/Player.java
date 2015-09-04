package objects;

import java.util.ArrayList;

import processing.core.PShape;
import processing.test.akira_neu.GameActivity;


/**
 * Created by aend on 12.08.15.
 */
public class Player extends AbstractObject
{
    public int facing;
    public int health;
    private int gold;
    public final static int MAXHEALTH =255;

    public Player(PShape shape, PShape img)
    {
        super(shape, img);
        health=MAXHEALTH;
        gold=0;
        facing=1;
        type='p';
    }

    public int getHealth()
    {
        return health;
    }

    public int getGold()
    {
        return gold;
    }

    public void update(ArrayList<StaticBlock>vb, ArrayList<Enemy> ve, ArrayList<Gold> vg)
    {
        setYSpeed(ySpeed+(float)0.3);

        collideEnemy(ve);
        collideGold(vg);
        super.update(vb);
    }



    public void collideEnemy(ArrayList<Enemy> others)
    {
        for (int i=0; i<others.size(); i++)
        {
            Enemy other= others.get(i);

            if (!(x + xSpeed > other.getX1() || getX1() + xSpeed < other.getX() || y + ySpeed > other.getY1() || getY1() + ySpeed < other.getY()))
            {
                health--;
                break;

            }
        }
    }

    public void collideGold(ArrayList<Gold> others)
    {
        for (int i=0; i<others.size(); i++)
        {
            Gold other= others.get(i);

            if (!(x + xSpeed > other.getX1() || getX1() + xSpeed < other.getX() || y + ySpeed > other.getY1() || getY1() + ySpeed < other.getY()))
            {
                gold++;
                other.setAvailable(false);
                break;
            }
        }
    }




}
