package makisp.gohome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static makisp.gohome.LoginActivity.loggedIn;

public class ProfileActivity extends AppCompatActivity {

    public TextView textout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textout = (TextView)findViewById(R.id.ProfileView);

//Οποίος  είναι OnlineUser τον δείχνει στο  Textview
        textout.setText(LoginActivity.onlineUser);

//  Σε πηγαίνει στο  Login Activity (αποσύνδεση)
        Button ProfileToLogin = (Button)findViewById(R.id.btnaposindesi);
        ProfileToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setMessage("Είσαι σίγουρος ότι θες να αποσυνδεθείς;")
                        .setCancelable(false)
                        .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                loggedIn = false;
                                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Οχι", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();

            }
        });





    }
}
