package makisp.gohome;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public DbCredentials dbCredentials = new DbCredentials(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Key Listener για το κούμπι είσοδος.
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        // Για να περάσεις οτι κάνεις στο textview πρεπει να τα βάλεις εδώ.
                        TextView user = (TextView) findViewById(R.id.textUsername);
                        TextView pass = (TextView) findViewById(R.id.textPassword);
                        //περνά σε μία μεταβλητή τις τιμές των textview.
                        String username = user.getText().toString();
                        String password = pass.getText().toString();
                        // καλεί τη σύναρτηση.
                        CheckLoginInDb(username , password);
                    }
                }
        );
        //Key Listener για το κουμπί εγγραφή.
        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                    }
                }
        );
    }

   //Συνάτηση για έλεγχο στοιχείων στην βάση.
   public void CheckLoginInDb(String username, String password) {
       SQLiteDatabase db = dbCredentials.getWritableDatabase();
       String Query = "Select * from Credential where Username = ? and Password = ?";
       String[] Args = {username, password};
       Cursor cursor = db.rawQuery(Query, Args);
       if(cursor.getCount() <= 0){
           Toast.makeText(LoginActivity.this, "O χρήστης δεν βρέθηκε!", Toast.LENGTH_SHORT).show();
           cursor.close();
       }
       else{
           Toast.makeText(LoginActivity.this, "Καλωσήρθες " + username + "!", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(LoginActivity.this,IntroActivity.class));
           cursor.close();
       }
   }
}
