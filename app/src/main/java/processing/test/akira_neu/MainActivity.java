package processing.test.akira_neu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//Start screen with menu

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Context context = getActivity();

        SharedPreferences sP = getPreferences(Context.MODE_PRIVATE);
        final int defaultLevel= getResources().getInteger(R.integer.startLevel);
        final int levelnr = sP.getInt("Level", defaultLevel);


        Button startButton = (Button) findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startGame = new Intent(MainActivity.this, GameActivity.class);
                startGame.putExtra("Level",defaultLevel);
                startActivity(startGame);
            }
        });

        Button resumeButton = (Button) findViewById(R.id.button);

        resumeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent resumeGame = new Intent(MainActivity.this, GameActivity.class);
                resumeGame.putExtra("Level", levelnr);
                startActivity(resumeGame);
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
