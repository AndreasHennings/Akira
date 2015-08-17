package processing.test.akira_neu;



import objects.Enemy;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    PShape level;  //Declaring a new .svg file. Level contains all objects of a level


    float h5;

    StaticBlock[] staticBlock;
    public Enemy[] enemies;
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
        h5=height/5;

        //load resources from assets folder

        level=loadShape(filenameLevel);


        initObjects();

        viewX=width/2-player.getCenterX();
        viewY=height/2-player.getCenterY();

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
        viewX=width/2-player.getCenterX();
        viewY=height/2-player.getCenterY();
        /*
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
        */
    }

    private void update()
    {
        player.update(staticBlock);

        for (int i=0; i<enemies.length; i++)
        {

            enemies[i].update(staticBlock);
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
        textSize(height / 20);
        text("Health: " + player.getHealth() + "/100 * Gold: " + player.getGold() + "    FrameCount: " + frameCount, 20, 50);
    }

    private void drawButtons()
    {
        fill(0,0,255,50);
        rect(0, height - 2 * h5, h5, h5);
        rect(width - h5, height - 2 * h5, h5, h5);

        fill(0, 255, 0, 50);
        rect(0, height - h5, h5, h5);
        rect(width - h5, height - h5, h5, h5);



    }


    /**********************************************************************************************/


    private void initObjects()
    {
        initBlocks();
        initEnemies();
        initPlayer();
       // initButtons();
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
                float rnd = random (-5,5);
                enemies[i] = new Enemy(allEnemies[i],rnd);
            }
        }
    }

    private void initPlayer()
    {
        PShape playerShape=level.findChild("player");
        player=new Player(playerShape);
    }


    /**********************************************************************************************/


    public void mouseDragged()
    {
        player.setSpeed(mouseX-pmouseX,mouseY-pmouseY);


    }


    public void mousePressed()
    {

            if (mouseX < h5 && mouseY > height - h5)
            {
               background(255);
            }

    }
}

