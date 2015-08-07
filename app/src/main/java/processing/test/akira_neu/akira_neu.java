package processing.test.akira_neu;

import processing.core.*;


public class akira_neu extends PApplet
{
    public void setup()
    {
        PShape s=loadShape("testlevel.svg");
        shape(s);
        noLoop();
    }

    static public void main(String[] passedArgs)
    {
        String[] appletArgs = new String[]{"akira_neu"};
        if (passedArgs != null)
        {
            PApplet.main(concat(appletArgs, passedArgs));
        }

        else

        {
            PApplet.main(appletArgs);
        }
    }
}
