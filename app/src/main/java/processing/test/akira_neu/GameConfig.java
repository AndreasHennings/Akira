package processing.test.akira_neu;

/**
 * Created by aend on 14.09.15.
 */
public class GameConfig
{
    //Level
    public static final int START_LEVEL = 5;
    public static final int MIN_GOLD = 10;
    public static final int MAX_GOLD = 20;

    //AbstractObjects
    public static final float MAX_SPEED = 50;
    public static final float BOUNCE = (float) -0.1;

    //Player
    public final static int MAXHEALTH =100;
    public final static float GRAVITY = (float) 0.3;

    //Gold
    public static final int BORDER_DISTANCE = 200;
    public static final int BLOCK_DISTANCE = 30;

    //Enemies
    public static final int ENEMY_SENSOR = 50;
    public static final float ENEMY_ACCELERATION = (float) 0.01;

    //GUI
    public static final float PROPORTIONAL_TEXTSIZE = (float) 0.025;
    public static final int TEXT_XPOS = 20;
    public static final int TEXT_YPOS = 50;
    public static final float MINIMAP_SIZE = (float) 0.2;

}
