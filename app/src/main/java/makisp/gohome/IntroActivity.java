package makisp.gohome;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*** Created by Stamatis Dachretzis.*/

public class IntroActivity extends AppCompatActivity {
    public Button buttonContinue;


    ///// Event Handler για άνοιγμα του GameActivity /////


    public void init() {

        buttonContinue = (Button) findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, GameActivity.class));
            }
        });
    }


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();

        //Ήχος κατά την εκκίνηση του Activity
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.thunder);
        mp.start();

    }



}