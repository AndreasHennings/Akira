package objects;

import processing.core.PShape;

/**
 * Created by aend on 04.09.15.
 */
public class Gold
{
    PShape goldshape;
    float x;
    float y;
    boolean available;

    public Gold(PShape goldshape, float x, float y)
    {
        this.goldshape=goldshape;
        this.x=x;
        this.y=y;
        available = true;
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public float getW() {return goldshape.getWidth();}
    public float getH() {return goldshape.getHeight();}
    public float getX1() {return x+goldshape.getWidth();}
    public float getY1() {return y+goldshape.getHeight();}
    public PShape getImg() {return goldshape;}
    public boolean getAvailable() {return available;}
    public void setAvailable(boolean a) {this.available=a;}
}
