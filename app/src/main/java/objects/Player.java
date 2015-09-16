package objects;

import java.util.ArrayList;

import processing.core.PShape;
import processing.test.akira_neu.GameActivity;
import processing.test.akira_neu.GameConfig;


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
        health= GameConfig.MAXHEALTH;
        gold=0;
        facing=1;

    }

    public void setX(float x)
    {
        this.x=x;
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
        setYSpeed(ySpeed*GameConfig.DRAG+GameConfig.GRAVITY);
        setXSpeed(xSpeed*GameConfig.DRAG);

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
