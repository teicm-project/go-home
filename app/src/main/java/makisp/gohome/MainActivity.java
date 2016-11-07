package makisp.gohome;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static DbCredentials db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbCredentials(this);
        db.getWritableDatabase();
        
        Button buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
                }
        );
    }
}
