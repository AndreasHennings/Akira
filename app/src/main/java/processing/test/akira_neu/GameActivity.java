package processing.test.akira_neu;

import java.util.ArrayList;

import objects.Enemy;
import objects.Obj;
import objects.Player;
import objects.StaticBlock;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;


public class GameActivity extends PApplet
{
    GameEngine gameEngine; //inner class

    PShape level;  //Declaring a new .svg file. Level contains all objects of a level
    PImage image;  //Declaring a new .jpg file which  will be used as a background


    //filenames still hardcoded for testing purposes. Will be replaced later

    String filenameLevel = "testlevel.svg";
    String filenameImage = "testbild.jpg";


    //view parameters

    float scaleFactor;  //scale of view
    float viewX;  // parameters needed for scrolling view. Determine the position of the
    float viewY;  // upper left corner of the level

    int h5;

    public void setup() //everything inside will be done just once
    {
        orientation(LANDSCAPE);

        //load resources from assets folder
        image=loadImage(filenameImage);
        level=loadShape(filenameLevel);


        size(displayWidth, displayHeight);
        h5=height/5;

        gameEngine = new GameEngine();


        //scaleFactor=(float)0.5;  //scale factor of level. Can be adjusted to fit to different screen sizes
        viewX=0;  //start view at 0,0 will later be adjusted to player position
        viewY=0;
    }

    //This method is the game loop.Will be repeated during gameplay
    public void draw()
    {
        gameEngine.run();
    }


     /********************************************************************************************/


    public class GameEngine  //inner class
    {
        StaticBlock[] staticBlock;
        Enemy[] enemies;
        Player player;

        ArrayList<Obj> allObjs;      //  a list containing all objects in a level
        //ArrayList<Obj>visibleObjs;  //  visible objects for collision testing
        //ArrayList<Obj>dynamicObjs;  //  dynamic objects for position updating

        public GameEngine()
        {
            initObjects();
        }

        /*///////////////////////////////////////////////////////////////////////////////////////*/

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

        /*///////////////////////////////////////////////////////////////////////////////////////*/

        public void run()
        {
            update();
            display();
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

            //scale(scaleFactor);

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
            fill(0,0,255,100);
            rect(0, height - h5, h5, h5);
            rect(width-h5,height-h5,h5,h5);

            fill(0,255,0,100);
            rect(0,height-2*h5,h5,h5);
            rect(width-h5,height-2*h5,h5,h5);

            fill(255,0,0,100);
            rect(h5,height-h5,h5,h5);
            rect(width-2*h5,height-h5,h5,h5);




        }


    }


}
