package processing.test.akira_neu;



import objects.Enemy;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    PShape level;  //Declaring a new .svg file. Level contains all objects of a level
    PShape playershape;
    PShape blockshape;

    float xMax; //Level size
    float yMax;

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
       // orientation(LANDSCAPE);

        size(displayWidth, displayHeight);
        //creates width und height variables representing physical display size

        smooth();

        //load resources from assets folder

        level=loadShape(filenameLevel);
        playershape=loadShape("playershape.svg");
        blockshape=loadShape("blockshape.svg");

        //initializes level dimension
        xMax=level.width;
        yMax=level.height;



        initObjects();  //see below for details

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

    }

    private void update()
    {
        updatePlayer();
        updateEnemies();
    }

    private void updatePlayer()
    {
        player.update(staticBlock, enemies);
    }

    private void updateEnemies()
    {
        for (int i=0; i<enemies.length; i++)
        {
            float xMove = random((float)-0.1,(float)0.1);
            float yMove = random((float)-0.1,(float)0.1);

            if (enemies[i].getX1()<0||enemies[i].getX()>xMax)
            {
                enemies[i].setX(random(2100,3200));
            }

            if (enemies[i].getY1()<0||enemies[i].getY()>yMax)
            {
                enemies[i].setY(random(1600,3000));
            }

            enemies[i].update(xMove,yMove);
        }

    }

    public void display()
    {
        background(120, 40, 100);
        //scale((float)0.3);
        shapeMode(CORNER);
        shape(level, viewX, viewY);

        for (int i=0; i<staticBlock.length; i++)
        {
            shape (blockshape, staticBlock[i].getX()+viewX, staticBlock[i].getY()+viewY, staticBlock[i].getW(), staticBlock[i].getH());

        }

        shapeMode(CENTER);
        shape(playershape,width/2,height/2);






        drawGui();
    }

    private void drawGui()
    {
        drawStatusBar();

    }

    private void drawStatusBar()
    {
        fill(255, 255, 255);
        textSize(height / 20);
        text("Health: " + player.getHealth() + "/100 * Gold: " + player.getGold() +"  "+ frameRate, 20, 50);
    }




    /**********************************************************************************************/


    private void initObjects()
    {
        initBlocks();
        initEnemies();
        initPlayer();

    }

    private void initBlocks()
    {
        PShape blocksShape = level.findChild("blocks");  //find PShape blocks, a group a single blocks

        if (blocksShape != null && blocksShape.getChildCount()>0)  //if you find one, and it has more than 0 children
        {
            staticBlock = new StaticBlock[blocksShape.getChildCount()];  //init array with number of children

            PShape[]allBlocks=blocksShape.getChildren();  //PShape array of single Blocks

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
                enemies[i] = new Enemy(allEnemies[i], xMax, yMax);
            }
        }
    }

    private void initPlayer()
    {
        PShape playerShape=level.findChild("player");
        player=new Player(playerShape, xMax, yMax);
        playerShape.setVisible(false);

    }


    /**********************************************************************************************/


    public void mouseDragged()
    {
        float xS=mouseX-pmouseX;
        float yS=mouseY-pmouseY;

        player.setSpeed(xS,yS);
    }




}

