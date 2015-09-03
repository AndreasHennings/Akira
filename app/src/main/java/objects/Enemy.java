package objects;

import java.util.ArrayList;

import processing.core.PShape;


/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends AbstractObject
{


    public Enemy(PShape shape, PShape img)
    {
        super(shape, img);
    }

    public void update(ArrayList<StaticBlock> vb, Player player)
    {
        findPlayer(player);
        super.update(vb);
    }

    private void findPlayer(Player player)
    {
        setXSpeed((player.getCenterX() - getCenterX()) * (float) 0.03);
        setYSpeed((player.getCenterY()- getCenterY())*(float)0.03);
    }

}