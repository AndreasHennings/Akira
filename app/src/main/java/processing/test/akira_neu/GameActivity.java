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
    PShape enemyshape;

    float xMax; //Level size
    float yMax;

    StaticBlock[] staticBlock;
    public Enemy[] enemies;
    public Player player;

    String filenameLevel = "testlevel.svg";

    //view parameters
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
        enemyshape=loadShape("qualle.svg");

        //initializes level dimension
        xMax=level.width;
        yMax=level.height;



        initObjects();  //see below for details

    }

    /**********************************************************************************************/

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

    /**********************************************************************************************/

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

    /**********************************************************************************************/

    public void display()
    {
        background(0,0,255);

        drawBlocks();
        drawEnemies();
        drawPlayer();
        drawGui();
    }

    private void drawBlocks()
    {
        shapeMode(CORNER);
        for (int i=0; i<staticBlock.length; i++)
        {
            if (!(staticBlock[i].getX1()+viewX<0 || staticBlock[i].getX()+viewX>width || staticBlock[i].getY1()+viewY<0 ||  staticBlock[i].getY()+viewY>height))
            {
                shape(blockshape, staticBlock[i].getX() + viewX, staticBlock[i].getY() + viewY, staticBlock[i].getW(), staticBlock[i].getH());
            }
        }
    }

    private void drawEnemies()
    {
        shapeMode(CORNER);
        for (int i=0; i<enemies.length; i++)
        {
            if (!(enemies[i].getX1()+viewX<0 || enemies[i].getX()+viewX>width || enemies[i].getY1()+viewY<0 ||  enemies[i].getY()+viewY>height))
            {
                shape(enemyshape, enemies[i].getX() + viewX, enemies[i].getY() + viewY, enemies[i].getW(), enemies[i].getH());


                if (!(player.getX()>enemies[i].getX1() || player.getX1() < enemies[i].getX() || player.getY() >enemies[i].getY1() || player.getY1() < enemies[i].getY()))
                {
                    player.health--;
                }

            }
        }
    }

    private void drawPlayer()
    {
        shapeMode(CENTER);
        shape(playershape,player.getCenterX()+viewX, player.getCenterY()+viewY, player.facing*player.getW(), player.getH());
    }

    private void drawGui()
    {
        fill(255, 255, 255);
        textSize(height / 30);
        text("Health: " + player.getHealth() + "/100 * Gold: " + player.getGold() +"  "+ frameRate, width/20, height/20);

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
    }


    /**********************************************************************************************/


    public void mouseDragged()
    {

            float xS = mouseX - pmouseX;
            if (xS < 0)
            {
                player.facing = -1;
            }
            if (xS > 0)
            {
                player.facing = 1;
            }


            float yS = mouseY - pmouseY;

            player.setSpeed(xS, yS);

    }




}

