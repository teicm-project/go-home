package makisp.gohome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    public TextView textout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textout = (TextView)findViewById(R.id.ProfileView);

//Οποίος  είναι OnlineUser τον δείχνει στο  Textview
        textout.setText(LoginActivity.onlineUser);
//  Σε πηγαίνει στο  Login Activity
        Button ProfileToLogin = (Button)findViewById(R.id.btnaposindesi);
        ProfileToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });





    }
}
