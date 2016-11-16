package makisp.gohome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ScenarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        Button button_choice1 = (Button) findViewById(R.id.button_choice1);

        button_choice1.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        GameActivity.progress++;
                        String log = " " + GameActivity.progress;
                        Log.d("progress::", log );
                        MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                        finish();
                    }
                }
        );

        Button button_choice2 = (Button) findViewById(R.id.button_choice2);

        button_choice2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        GameActivity.progress++;
                        String log = " " + GameActivity.progress;
                        Log.d("progress::", log );
                        MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                        finish();
                    }
                }
        );


    }
}
