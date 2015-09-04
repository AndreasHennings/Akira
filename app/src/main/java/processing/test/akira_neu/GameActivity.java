package processing.test.akira_neu;



import java.util.ArrayList;

import objects.Enemy;
import objects.Gold;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    boolean gameRunning;
    PShape level;  //Declaring a new .svg file. 'Level' contains all information about
    PShape playershape;
    PShape blockshape;
    PShape enemyshape;
    PShape goldshape;


    StaticBlock[] staticBlock;
    Enemy[] enemies;
    Player player;
    Gold[] goldcoins = new Gold[100];



    ArrayList<Enemy> visibleEnemies;
    ArrayList<StaticBlock> visibleBlocks;
    ArrayList<Gold> visibleGold;


    String filenameLevel = "testlevel2.svg";

    //view parameters
    float viewX;  // parameters needed for scrolling view. Determine the position of the
    float viewY;  // upper left corner of the level


    public void setup() //everything inside will be done just once
    {
       // orientation(LANDSCAPE);

        gameRunning=true;
        size(displayWidth, displayHeight);
        //creates width und height variables representing physical display size


        //load resources from assets folder

        level=loadShape(filenameLevel);
        playershape=loadShape("playershape.svg");
        blockshape=loadShape("blockshape.svg");
        enemyshape=loadShape("qualle.svg");
        goldshape=loadShape("gold.svg");



        initObjects();  //see below for details

    }

    /**********************************************************************************************/

    //This method is the game loop.Will be repeated during gameplay
    public void draw()
    {
        if (gameRunning)
        {
            visibleBlocks = getVisibleBlocks();
            visibleEnemies = getVisibleEnemies();
            visibleGold = getVisibleGolds();


            scroll();
            update();
            display();
        }

        else
        {
            gameOver();
        }
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
        if (player.health<player.MAXHEALTH && (frameCount%100==0)) {player.health++;}
        if (player.health<1||player.getGold()==goldcoins.length) {gameRunning=false;}//
        player.update(visibleBlocks, visibleEnemies, visibleGold);
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
        drawGolds();
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

    public void drawGolds()
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
        textSize(height / 30);
        text("Health: " + player.getHealth() + "/"+player.MAXHEALTH+" * Gold: " + player.getGold() + "/" + goldcoins.length + "   " + frameRate, width / 20, height / 20);

        shape(level, width - width / 5, height - height / 5, width / 5, height / 5);

    }


    /**********************************************************************************************/


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
                enemies[i] = new Enemy(allEnemies[i], enemyshape);
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
            float x = random (100, level.getWidth()-100);
            float y = random(100, level.getHeight()-100);

            boolean free = true;

            for (int j =0; j<staticBlock.length; j++)
            {

                if (!(staticBlock[j].getX1()< x-30 || staticBlock[j].getX() > x+30 || staticBlock[j].getY1()  < y-30 || staticBlock[j].getY()  > y+30))
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
            exit();
        }
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

    ArrayList<Gold> getVisibleGolds()
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

    private void gameOver()
    {
           background(random(0,255), random(0,255), random(0,255));
            fill(255, 0, 0);
            textSize(height / 10);
            text("GAME OVER!",30,height/2-height/10);
            textSize(height/20);
            int score = player.getGold()*100+player.getHealth();
            text("Your Score: " + score, 30, height / 2);
            text("Touch Screen to exit",30,height/2+height/10);


    }
}

