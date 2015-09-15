package objects;

import java.util.ArrayList;

import processing.core.PShape;
import processing.test.akira_neu.GameActivity;
import processing.test.akira_neu.GameConfig;


/**
 * Created by judith on 11.08.15.
 */
public class Enemy extends AbstractObject
{

    private int level;

    public Enemy(PShape shape, PShape img, int level)
    {

        super(shape, img);
        this.level=level;
    }

    public void update(ArrayList<StaticBlock> vb, Player player)
    {
        findPlayer(player);
        super.update(vb);
    }

    private void findPlayer(Player player)
    {
        setXSpeed((player.getCenterX() - getCenterX()) * GameConfig.ENEMY_ACCELERATION* level);
        setYSpeed((player.getCenterY() - getCenterY()) * GameConfig.ENEMY_ACCELERATION * level);
    }

}