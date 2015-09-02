package objects;

import processing.core.PShape;

/**
 * Created by aend on 02.09.15.
 */
public class AbstractObject
{
    PShape shape;
    PShape img;

    public char type;

    //position and dimension
    float x;
    float y;
    float w;
    float h;

    float x1;
    float y1;

    float xSpeed;
    float ySpeed;

    public AbstractObject(PShape shape, PShape img)
    {
        this.shape = shape;
        this.img = img;
        type='0';


        float[] params = shape.getParams();
        this.x=params[0];
        this.y=params[1];
        this.w=params[2];
        this.h=params[3];

        xSpeed=0;
        ySpeed=0;

    }

    public char getType() {return type;}

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

        if (xSpeed > 10)
        {
            xSpeed = 10;
        }
        if (xSpeed < -10)
        {
            xSpeed = -10;
        }
    }

    public void setYSpeed(float ySpeed)
    {
        this.ySpeed = ySpeed;

        if (ySpeed > 10)
        {
            ySpeed = 10;
        }
        if (ySpeed < -10)
        {
            ySpeed = -10;
        }
    }


    public void update()
    {

        x+=xSpeed;
        y+=ySpeed;

        shape.translate(xSpeed, ySpeed);

    }


}
