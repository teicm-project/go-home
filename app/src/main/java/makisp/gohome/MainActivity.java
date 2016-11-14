package makisp.gohome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Button buttonAbout;
    public static DbCredentials db;
    public static DbInventory db2;

    public void init() {

        buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbCredentials(this);
        db2 = new DbInventory(this);
        db.getWritableDatabase();
        db2.getWritableDatabase();


        db.addMarkers(new Markers(1, 41.0911159, 23.5498778));
        db.addMarkers(new Markers(2, 41.0904367, 23.5504277));
        db.addMarkers(new Markers(3, 41.0895821, 23.5501715));
        db.addMarkers(new Markers(4, 41.0887493, 23.5496243));
        db.addMarkers(new Markers(5, 41.0890383, 23.5489913));

        Button buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
                }
        );
        init();
    }
}
