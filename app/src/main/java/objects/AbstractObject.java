package objects;

import java.util.ArrayList;

import processing.core.PShape;
import processing.test.akira_neu.GameConfig;

/**
 * Created by aend on 02.09.15.
 */
public class AbstractObject
{
    PShape shape;
    PShape img;

    //position and dimension
    float x;
    float y;
    float w;
    float h;

    float xSpeed;
    float ySpeed;

    public AbstractObject(PShape shape, PShape img)
    {
        this.shape = shape;
        this.img = img;


        float[] params = shape.getParams();
        this.x=params[0];
        this.y=params[1];
        this.w=params[2];
        this.h=params[3];

        xSpeed=0;
        ySpeed=0;

    }



    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getW()
    {
        return w;
    }

    public float getH()
    {
        return h;
    }

    public float getX1()
    {
        return x+w;
    }

    public float getY1()
    {
        return y+h;
    }

    public float getCenterX()
    {
        return x+(w/2);
    }

    public float getCenterY()
    {
        return y+(h/2);
    }

    public PShape getImg() {return img;}



    public void setXSpeed(float xSpeed)
    {
        this.xSpeed = xSpeed;

        if (this.xSpeed > GameConfig.MAX_SPEED)
        {
            this.xSpeed = GameConfig.MAX_SPEED;
        }
        if (this.xSpeed < -GameConfig.MAX_SPEED)
        {
            this.xSpeed = -GameConfig.MAX_SPEED;
        }

    }

    public void setYSpeed(float ySpeed)
    {
        this.ySpeed = ySpeed;

        if (this.ySpeed > GameConfig.MAX_SPEED)
        {
            this.ySpeed = GameConfig.MAX_SPEED;
        }
        if (this.ySpeed < -GameConfig.MAX_SPEED)
        {
            this.ySpeed = -GameConfig.MAX_SPEED;
        }


    }


    public void update(ArrayList<StaticBlock> others)
    {
        collideBlock(others);


        x+=xSpeed;
        y+=ySpeed;

        shape.translate(xSpeed, ySpeed);
    }

    public void collideBlock(ArrayList<StaticBlock> others)
    {
        for (int i=0; i<others.size(); i++)
        {
            AbstractObject other= others.get(i);

            if (!(x + xSpeed > other.getX1() || getX1() + xSpeed < other.getX() || y + ySpeed > other.getY1() || getY1() + ySpeed < other.getY()))
            {

                if (!(x + xSpeed > other.getX1() || getX1() + xSpeed < other.getX()))
                {
                    setXSpeed(xSpeed*GameConfig.BOUNCE);
                }

                if (!(y + ySpeed > other.getY1()) || getY1() + ySpeed < other.getY())
                {
                    setYSpeed(ySpeed*GameConfig.BOUNCE);
                }

                break;
            }
        }
    }
}
