package processing.test.akira_neu;



import java.util.ArrayList;

import objects.Enemy;
import objects.Gold;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    private boolean gameRunning;
    private boolean nextLevel;
    PImage bckgrndImg;

    private PShape level;  //Declaring a new .svg file. 'Level' contains all information about
    public int levelnr = GameConfig.START_LEVEL;

    private PShape playershape;
    private PShape blockshape;
    private PShape enemyshape;
    private PShape goldshape;

    private StaticBlock[] staticBlock;
    private Enemy[] enemies;
    private Player player;
    private Gold[] goldcoins;

    private ArrayList<Enemy> visibleEnemies;
    private ArrayList<StaticBlock> visibleBlocks;
    private ArrayList<Gold> visibleGold;



    //view parameters
    private float viewX;  // parameters needed for scrolling view. Determine the position of the
    private float viewY;  // upper left corner of the level




    public void setup() //everything inside will be done just once
    {
        orientation(LANDSCAPE);

        gameRunning=true;
        nextLevel=false;

        size(displayWidth, displayHeight);
        //creates width und height variables representing physical display size


        //load resources from assets folder
        String filenameLevel="Level"+levelnr+".svg";
        try
        {
            level = loadShape(filenameLevel);
        }

        catch(Exception e)
        {
            gameOver();
        }

        if (levelnr==GameConfig.START_LEVEL)
        {

            playershape = loadShape("mermaid.svg");
            blockshape = loadShape("blockshape.svg");
            enemyshape = loadShape("qualle.svg");
            goldshape = loadShape("gold.svg");

            bckgrndImg =loadImage("sea.png");

        }
        goldcoins = new Gold[((int) random(GameConfig.MIN_GOLD, GameConfig.MAX_GOLD))];

        initObjects();  //see below for details

    }

    /**********************************************************************************************/

    //This method is the game loop.Will be repeated during gameplay
    public void draw()
    {
        if (gameRunning)
        {
            update();
            display();
        }

        else
        {

            gameOver();
        }
    }

    /***********************************************************************************************
     *    INIT - SHAPES ARE READ FROM .SVG FILE AND NEW OBJECTS CREATED
     **********************************************************************************************/

    private void initObjects()
    {
        initBlocks();
        initEnemies();
        initPlayer();
        initGold();
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
                staticBlock[i]= new StaticBlock(allBlocks[i], blockshape);
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
                enemies[i] = new Enemy(allEnemies[i], enemyshape, levelnr);
            }
        }
    }

    private void initPlayer()
    {
        PShape playerShape=level.findChild("player");
        player=new Player(playerShape, playershape);
    }

    private void initGold()
    {
        int i=0;
        while (i<goldcoins.length)
        {
            float x = random (GameConfig.BORDER_DISTANCE, level.getWidth()- GameConfig.BORDER_DISTANCE);
            float y = random(GameConfig.BORDER_DISTANCE, level.getHeight()- GameConfig.BORDER_DISTANCE);

            boolean free = true;

            for (int j =0; j<staticBlock.length; j++)
            {

                if (!     (staticBlock[j].getX1() < x-GameConfig.BLOCK_DISTANCE
                        || staticBlock[j].getX()  > x+GameConfig.BLOCK_DISTANCE
                        || staticBlock[j].getY1() < y-GameConfig.BLOCK_DISTANCE
                        || staticBlock[j].getY()  > y+GameConfig.BLOCK_DISTANCE))
                {
                    free=false;
                }
            }

            if (free)
            {
                goldcoins[i] = new Gold(goldshape, x, y);
                i++;

            }

        }

    }


    /**********************************************************************************************/
    
    /**********************************************************************************************/


    private void update()
    {
        getAllVisibleElements();
        updatePlayer();
        updateEnemies();
        checkGameOver();
    }

    private void getAllVisibleElements()
    {
        visibleBlocks = getVisibleBlocks();
        visibleEnemies = getVisibleEnemies();
        visibleGold = getVisibleGolds();
    }

    private ArrayList<Enemy> getVisibleEnemies()
    {
        ArrayList<Enemy> result = new ArrayList<Enemy>();

        for (int i=0; i<enemies.length; i++)
        {
            if (!     (enemies[i].getX1() + viewX < -(GameConfig.ENEMY_SENSOR*levelnr)
                    || enemies[i].getX()  + viewX > width + (GameConfig.ENEMY_SENSOR*levelnr)
                    || enemies[i].getY1() + viewY < - (GameConfig.ENEMY_SENSOR*levelnr)
                    || enemies[i].getY()  + viewY > height + (GameConfig.ENEMY_SENSOR*levelnr)))
            {
                result.add(enemies[i]);
            }
        }
        return result;
    }

    private ArrayList<StaticBlock> getVisibleBlocks()
    {
        ArrayList<StaticBlock> result = new ArrayList<StaticBlock>();

        for (int i = 0; i < staticBlock.length; i++)
        {
            if (!(staticBlock[i].getX1() + viewX < 0 || staticBlock[i].getX() + viewX > width || staticBlock[i].getY1() + viewY < 0 || staticBlock[i].getY() + viewY > height))
            {
                result.add(staticBlock[i]);
            }
        }

        return result;
    }

    private ArrayList<Gold> getVisibleGolds()
    {
        ArrayList<Gold> result = new ArrayList<Gold>();

        for (int i = 0; i < goldcoins.length; i++)
        {
            if (!(goldcoins[i].getX1() + viewX < 0 || goldcoins[i].getX() + viewX > width || goldcoins[i].getY1() + viewY < 0 || goldcoins[i].getY() + viewY > height))
            {
                if (goldcoins[i].getAvailable())
                {
                    result.add(goldcoins[i]);
                }
            }
        }

        return result;
    }

    private void updatePlayer()
    {
        if (player.health < GameConfig.MAXHEALTH && (frameCount%100==0)) {player.health++;}

        player.update(visibleBlocks, visibleEnemies, visibleGold);
    }

    private void updateEnemies()
    {
        for (int i=0; i<visibleEnemies.size(); i++)
        {
            Enemy e = visibleEnemies.get(i);
            e.update(visibleBlocks, player);
        }
    }

    private void checkGameOver()
    {
        if (player.health<1)
        {gameRunning=false;}
        if (player.getGold()==goldcoins.length)
        {gameRunning=false;
            nextLevel=true;}//

    }

    /**********************************************************************************************/

    private void display()
    {

        background(0,0,255);
        image(bckgrndImg,viewX,viewY,level.width,level.height);
        scroll();
        drawBlocks();
        drawEnemies();
        drawGolds();
        drawPlayer();
        drawGui();
    }

    private void scroll()
    {
        if (player.getCenterX() > width/2 &&
            player.getCenterX() < level.width-width/2)
        {
            viewX = width / 2 - player.getCenterX();
        }

        if (player.getCenterY() > height/2 &&
            player.getCenterY() < level.height-height/2)
        {
            viewY = height / 2 - player.getCenterY();
        }
    }

    private void drawBlocks()
    {
        shapeMode(CORNER);
        for (int i = 0; i < visibleBlocks.size(); i++)
        {
            StaticBlock sb = visibleBlocks.get(i);
            shape(sb.getImg(), sb.getX() + viewX, sb.getY() + viewY, sb.getW(), sb.getH());
        }
    }

    private void drawEnemies()
    {

        for (int i=0; i<visibleEnemies.size(); i++)
        {
            Enemy e = visibleEnemies.get(i);
            shape(e.getImg(), e.getX() + viewX, e.getY() + viewY, e.getW(), e.getH());
        }
    }

    private void drawGolds()
    {
        shapeMode(CORNER);
        for (int i=0; i<visibleGold.size(); i++)
        {
            Gold g = visibleGold.get(i);
            shape(g.getImg(), g.getX() + viewX, g.getY() + viewY, g.getW(), g.getH());
        }
    }

    private void drawPlayer()
    {
        shapeMode(CENTER);
        shape(player.getImg(), player.getCenterX() + viewX, player.getCenterY() + viewY, player.facing * player.getW(), player.getH());
    }

    private void drawGui()
    {
        fill(255, player.health, player.health);
        textSize((int) (height * GameConfig.PROPORTIONAL_TEXTSIZE));
        text(
                "Level: "
                +levelnr
                +" * "
                +"Health: "
                + player.getHealth()
                + "/" + GameConfig.MAXHEALTH
                + " * Gold: "
                + player.getGold()
                + "/" + goldcoins.length
                , GameConfig.TEXT_XPOS, GameConfig.TEXT_YPOS);

        shapeMode(CORNER);
        shape(level, width - (width*GameConfig.MINIMAP_SIZE),
                     height - (height*GameConfig.MINIMAP_SIZE),
                     width*GameConfig.MINIMAP_SIZE,
                     height*GameConfig.MINIMAP_SIZE);


    }


    /**********************************************************************************************/




    /**********************************************************************************************/


    public void mouseDragged()
    {

        if (gameRunning)
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

            player.setXSpeed(xS);
            player.setYSpeed(yS);
        }

        else
        {

            if (nextLevel)
            {
                levelnr++;
                setup();
            }

            else
            {
                exit();
            }
        }
    }
/**************************************************************************************************************************************************************************/


/**********************************************************************************************************************************************************/

    private void gameOver()
    {
            background(0);
            fill(255, 0, 0);
            if (nextLevel==false)
            {
                textSize(height * GameConfig.PROPORTIONAL_TEXTSIZE *2);
                text("GAME OVER!", GameConfig.TEXT_XPOS, height / 2 - height / 10);
                textSize (height * GameConfig.PROPORTIONAL_TEXTSIZE);
                text("Wipe Screen to exit", GameConfig.TEXT_XPOS, height / 2 + height / 10);
            }

            if (nextLevel)
            {
                textSize(height * GameConfig.PROPORTIONAL_TEXTSIZE *2);
                text("LEVEL UP!", GameConfig.TEXT_XPOS, height / 2 - height / 10);
                textSize (height * GameConfig.PROPORTIONAL_TEXTSIZE);
                text("Wipe Screen to exit", GameConfig.TEXT_XPOS, height / 2 + height / 10);
            }



    }


}

