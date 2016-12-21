package makisp.gohome;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ItemsActivity extends AppCompatActivity {
    public DbCredentials myDb = new DbCredentials(this);

    public ListView listameantikimena;
    public ArrayAdapter<String> adapter;
    public ArrayList<String>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        //Μεταβλητή για να μάθω πόσες θέσεις έχει ο πίνακας
        int i= 0;
        String [] items = new String[10];

        // Δημιουργία οπζεκτ για να ελέγχω την λίστα
        listameantikimena = (ListView) findViewById(R.id.lista);

        // λίστα
        List<Inventory> invetories = myDb.getAllItems();
        for(Inventory invetory : invetories){
        // ευρεση του χριστη
            Log.i("Inventory: ", String.valueOf(invetory.getActiveUser()));
            Log.i("Active: ", String.valueOf(LoginActivity.activeUser));
            if(invetory.getActiveUser().equals(LoginActivity.activeUser)){
                items[i] = invetory.getItem();
                i++;
            }
        }

        // Καινούριο στρινκγ
        String [] newItems = new String[i];
        int kapa;

        // Μεταφορά των στοιχειών του πίνακα items στον πίνακα newItems
         for(kapa=0;kapa<i;kapa++){
         newItems[kapa] = items[kapa];
        }

       // addapter  για την εμφανιση στην λιστα
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newItems);
        listameantikimena.setAdapter(adapter);


    }
}



