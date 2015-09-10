package processing.test.akira_neu;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//Start screen with menu

public class MainActivity extends Activity
{
    Button button;  //start button
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startGame = new Intent(MainActivity.this, GameActivity.class);
                startActivity(startGame);
            }
        });

        Button descriptionButton = (Button)findViewById(R.id.description_button);

        descriptionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent showDescript = new Intent(MainActivity.this, DescriptionActivity.class);
                startActivity(showDescript);
            }
        });

    }


}
