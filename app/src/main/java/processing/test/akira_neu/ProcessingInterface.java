package processing.test.akira_neu;

import processing.core.*;


public class ProcessingInterface extends PApplet
{
    public void setup()
    {   noLoop();
        PShape s=loadShape("testlevel.svg");
        shape(s);
    }
}
