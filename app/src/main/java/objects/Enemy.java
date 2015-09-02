package objects;

import processing.core.PShape;


/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends AbstractObject
{


    public Enemy(PShape shape, PShape img)
    {
        super(shape, img);
        type='e';
    }

}