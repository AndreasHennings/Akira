package processing.test.akira_neu;

import processing.core.*;


public class GameActivity extends PApplet
{



    GameEngine gameEngine;
    boolean gameRunning;

    PShape level;
    String filename = "testlevel.svg";

    //view parameters

    float viewX;
    float viewY;

    public void setup()
    {
        noLoop();
        level=loadShape(filename);

        gameEngine = new GameEngine(level);
        gameRunning = true;



        viewX=0;
        viewY=0;
    }

    public void draw()
    {


            gameEngine.display();

    }

    public class GameEngine
    {
        public GameEngine(PShape level)
        {

        }

        public void display()
        {
            shape(level, viewX, viewY);

        }

    }


}
