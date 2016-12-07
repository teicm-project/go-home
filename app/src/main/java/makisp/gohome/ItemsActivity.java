package makisp.gohome;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import static makisp.gohome.LoginActivity.loggedIn;


public class ItemsActivity extends AppCompatActivity {
    public DbInventory myDb = new DbInventory(this);
    public TextView textout;

    ListView listameantikimena;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!loggedIn){
            startActivity(new Intent(ItemsActivity.this, MainActivity.class));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Invetory e = new Invetory();
        e.setActiveUser("p");
        e.setItem("ΟΠΛΟ");
        myDb.addItem(e);

        int i= 0;
        String [] items = new String[1000];

        List<Invetory> invetories = myDb.getAllItems();
        for(Invetory invetory : invetories){

            if(invetory.getActiveUser().equals(LoginActivity.onlineUser)){

                items[i] = invetory.getItem();
                i++;
            }
        }


        textout = (TextView)findViewById(R.id.ena);
        textout.setText(items[0]);
    }

    @Override
    protected void onResume() {
        if(!loggedIn){
            startActivity(new Intent(ItemsActivity.this, MainActivity.class));
        }
        super.onResume();
    }
}



