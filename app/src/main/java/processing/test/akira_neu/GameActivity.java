package processing.test.akira_neu;

import java.util.ArrayList;

import objects.Obj;
import processing.core.*;


public class GameActivity extends PApplet
{

    GameEngine gameEngine;

    PShape level;  //Declaring a new .svg file. Level contains all objects of a level
    PImage image;  //Declaring a new .jpg file which  will be used as a background

    //filenames still hardcoded for testing purposes. Will be replaced later

    String filenameLevel = "testlevel.svg";
    String filenameImage ="testbild.jpg";

    //view parameters

    float scaleFactor;  //scale of view
    float viewX;  // parameters needed for scrolling view. Determine the position of the
    float viewY;  // upper left corner of the level

    public void setup() //everything inside will be done just once
    {
        //load resources from assets folder
        image=loadImage(filenameImage);
        level=loadShape(filenameLevel);

        size(displayWidth, displayHeight);

        gameEngine = new GameEngine();


        scaleFactor=(float)0.5;  //scale factor of level. Can be adjusted to fit to different screen sizes
        viewX=0;  //start view at 0,0 will later be adjusted to player position
        viewY=0;
    }

    //This method is the game loop.Will be repeated during gameplay
    public void draw()
    {
        gameEngine.run();
    }


     /********************************************************************************************/


    public class GameEngine
    {
        ArrayList<Obj>allObjs;      //  a list containing all objects in a level
        ArrayList<Obj>visibleObjs;  //  visible objects for collision testing
        ArrayList<Obj>dynamicObjs;  //  dynamic objects for position updating

        public GameEngine()
        {
            initObjects();

        }

        private void initObjects()
        {
            initBlocks();
            //initEnemies();
            //initPlayer();

        }

        private void initBlocks()
        {
            PShape blocks = level.findChild("blocks");
            if (blocks != null && blocks.getChildCount()>0)
            {



            }
        }

        public void run()
        {
            display();
        }


        public void display()
        {
            image(image,0,0);
            scale(scaleFactor);
            shape(level, viewX, viewY);
        }



    }


}
