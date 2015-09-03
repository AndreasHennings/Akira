package processing.test.akira_neu;



import java.util.ArrayList;

import objects.AbstractObject;
import objects.Enemy;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    PShape level;  //Declaring a new .svg file. 'Level' contains all information about
    PShape playershape;
    PShape blockshape;
    PShape enemyshape;


    StaticBlock[] staticBlock;
    public Enemy[] enemies;
    public Player player;


    ArrayList<Enemy> visibleEnemies;
    ArrayList<StaticBlock> visibleBlocks;


    String filenameLevel = "testlevel2.svg";

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



        initObjects();  //see below for details

    }

    /**********************************************************************************************/

    //This method is the game loop.Will be repeated during gameplay
    public void draw()
    {
        visibleBlocks=getVisibleBlocks();
        visibleEnemies=getVisibleEnemies();

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
        player.update(visibleBlocks, visibleEnemies);
    }

    private void updateEnemies()
    {
        for (int i=0; i<visibleEnemies.size();i++)
        {
            Enemy e = visibleEnemies.get(i);
            e.update(visibleBlocks, player);
        }
    }



    /**********************************************************************************************/

    public void display()
    {
        background(0, 0, 255);

        drawObjects();

        drawPlayer();
        drawGui();
    }

    private void drawObjects()
    {
        shapeMode(CORNER);
        for (int i=0; i<visibleBlocks.size(); i++)
        {
            StaticBlock sb = visibleBlocks.get(i);
            shape(sb.getImg(), sb.getX() + viewX, sb.getY() + viewY, sb.getW(), sb.getH());
        }

        for (int i=0; i<visibleEnemies.size(); i++)
        {
            Enemy e = visibleEnemies.get(i);
            shape(e.getImg(), e.getX() + viewX, e.getY() + viewY, e.getW(), e.getH());
        }
    }



    private void drawPlayer()
    {
        shapeMode(CENTER);
        shape(player.getImg(), player.getCenterX() + viewX, player.getCenterY() + viewY, player.facing * player.getW(), player.getH());
    }

    private void drawGui()
    {
        fill(255, 255, 255);
        textSize(height / 30);
        text("Health: " + player.getHealth() + "/100 * Gold: " + player.getGold() + "  " + frameRate, width / 20, height / 20);

        shape(level, width - width / 5, height - height / 5, width / 5, height / 5);

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
                enemies[i] = new Enemy(allEnemies[i], enemyshape);
            }
        }
    }

    private void initPlayer()
    {
        PShape playerShape=level.findChild("player");
        player=new Player(playerShape, playershape);
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

            player.setXSpeed(xS);
            player.setYSpeed(yS);

    }

    ArrayList<Enemy> getVisibleEnemies()
    {
        ArrayList<Enemy> result = new ArrayList<Enemy>();

        for (int i=0; i<enemies.length; i++)
        {
            if (!(enemies[i].getX1() + viewX < -100 || enemies[i].getX() + viewX > width+100 || enemies[i].getY1() + viewY < -100 || enemies[i].getY() + viewY > height+100))
            {
                result.add(enemies[i]);
            }
        }
        return result;
    }

    ArrayList<StaticBlock> getVisibleBlocks()
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
}

