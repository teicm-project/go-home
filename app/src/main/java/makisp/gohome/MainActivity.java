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


        db.addMarkers(new Markers(1, 41.088811, 23.547403));
        db.addMarkers(new Markers(2, 41.089043, 23.548937));
        db.addMarkers(new Markers(3, 41.0900613,23.5523832));
        db.addMarkers(new Markers(4, 41.087889, 23.550977));
        db.addMarkers(new Markers(5, 41.090249, 23.550382));
        db.addMarkers(new Markers(6, 41.088811, 23.547403));
        db.addMarkers(new Markers(7, 41.088811, 23.547403));
        db.addMarkers(new Markers(8, 41.088811, 23.547403));
        db.addMarkers(new Markers(9, 41.088811, 23.547403));
        db.addMarkers(new Markers(10, 41.090847, 23.545691));

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
