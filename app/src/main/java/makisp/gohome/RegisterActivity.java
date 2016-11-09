package makisp.gohome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static makisp.gohome.R.id.TextUserName;

public class RegisterActivity extends AppCompatActivity {
    EditText editUserName,editPassword;
    Button btnAddData,btnAddData2;
    DbCredentials myDb = new DbCredentials(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        ////// Βήμα 1 με αυτά τα βήματα εισάγουμε μεταβλητέ στην βάση ///////
        editUserName = (EditText)findViewById(TextUserName);
        editPassword = (EditText)findViewById(R.id.TextPassword);
        btnAddData = (Button)findViewById(R.id.buttonEgrafh);
        btnAddData2 = (Button)findViewById(R.id.buttonsyndesh);


        Button Egrafh = (Button)findViewById(R.id.buttonEgrafh);
        Egrafh.setOnClickListener(new View.OnClickListener() {


             @Override
             public void onClick(View v) {


                 ////// Βήμα 2 εισάγουμε τα στοιχεία σε μεταβλητές Στρινγ για να μπορούν να μπουν στον βάση////////

                 String usernamestrr = editUserName.getText().toString();
                 String passwordstrr = editPassword.getText().toString();

                 editUserName.setText("");
                 editPassword.setText("");

                 String Username_String =   myDb.SearchUsername(usernamestrr);
                 if(Username_String.equals(usernamestrr))
                 {
                     Toast temp = Toast.makeText(RegisterActivity.this,"Ο χρήστης υπάρχει ήδη!",Toast.LENGTH_LONG);
                     temp.show();

                     editUserName.setText("");
                     editPassword.setText("");

                 }else
                 {
//       Βήμα 3 εισάγουμε τα στοιχεία στον πίνακα

                     Credential credential = new Credential();
                     credential.setUsername(usernamestrr);
                     credential.setPassword(passwordstrr);

                     Toast temp = Toast.makeText(RegisterActivity.this,"Επιτυχείς εγγραφή!",Toast.LENGTH_LONG);
                     temp.show();

                   //  Βήμα 4
                     myDb.addCredential(credential);
                 }

                 }
            }
        );
        // button Που ανοίγει άλλο Activity

        Button RegisterToLogin = (Button)findViewById(R.id.buttonsyndesh);
        RegisterToLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        }
        );


    }



}
