package processing.test.akira_neu;


import objects.Enemy;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    PShape level;  //Declaring a new .svg file. Level contains all objects of a level
    PShape buttons;
     //filenames still hardcoded for testing purposes. Will be replaced later

    StaticBlock[] staticBlock;
    Enemy[] enemies;
    public Player player;

    String filenameLevel = "testlevel.svg";
    //view parameters

    float scaleFactor;  //scale of view
    float viewX;  // parameters needed for scrolling view. Determine the position of the
    float viewY;  // upper left corner of the level





    public void setup() //everything inside will be done just once
    {
        orientation(LANDSCAPE);
        size(displayWidth, displayHeight);

        //load resources from assets folder

        level=loadShape(filenameLevel);
        buttons=loadShape("buttons.svg");

        initObjects();

        viewX=0;  //start view at 0,0 will later be adjusted to player position
        viewY=0;
    }

    //This method is the game loop.Will be repeated during gameplay
    public void draw()
    {
        scroll();
        update();
        display();
    }

    private void scroll()
    {
        if (viewX>width/2-player.getCenterX())
        {
            viewX--;
        }

        if (viewY>height/2-player.getCenterY())
        {
            viewY--;
        }

        if (viewX<width/2-player.getCenterX())
        {
            viewX++;
        }

        if (viewY<height/2-player.getCenterY())
        {
            viewY++;
        }
    }

    private void update()
    {
        for (int i=0; i<enemies.length; i++)
        {
            enemies[i].update();
        }
    }

    public void display()
    {
        background(120, 40, 100);
        shape(level, viewX, viewY);
        drawGui();
    }

    private void drawGui()
    {
        drawStatusBar();
        drawButtons();
    }

    private void drawStatusBar()
    {
        fill(255, 255, 255);
        textSize(40);
        text("Health: " + player.getHealth() + "/100 * Gold: " + player.getGold(), 20, 50);
    }

    private void drawButtons()
    {


    }


    /*
    ************************************************************************************************
     */

    private void initObjects()
    {
    initBlocks();
    initEnemies();
    initPlayer();
    }

    private void initBlocks()
    {
        PShape blocksShape = level.findChild("blocks");

        if (blocksShape != null && blocksShape.getChildCount()>0)
        {
            staticBlock = new StaticBlock[blocksShape.getChildCount()];
            PShape[]allBlocks=blocksShape.getChildren();

            for (int i= 0; i<allBlocks.length; i++)
            {
                staticBlock[i]= new StaticBlock(allBlocks[i]);
            }
        }
    }

    private void initEnemies()
    {
        PShape enemiesShape = level.findChild("enemies");

        if (enemiesShape != null && enemiesShape.getChildCount()>0)
        {
            enemies = new Enemy[enemiesShape.getChildCount()];
            PShape[] allEnemies = enemiesShape.getChildren();

            for (int i = 0; i<allEnemies.length; i++)
            {
                float rnd = random (-2,2);
                enemies[i] = new Enemy(allEnemies[i],rnd);
            }
        }
    }

    private void initPlayer()
    {
        PShape playerShape=level.findChild("player");
        player=new Player(playerShape);
    }
}

