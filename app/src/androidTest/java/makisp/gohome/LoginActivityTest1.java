package makisp.gohome;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 *
 * Created by Stergios Tselios on 16/12/2016.
 *
 */

public class LoginActivityTest1 {

    @Rule
    public ActivityTestRule<LoginActivity> login = new ActivityTestRule<>(LoginActivity.class);

    LoginActivity lgin;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp(){
        intent.setClass(appContext, ScenarioActivity.class);
        lgin=login.getActivity();
        login.launchActivity(intent);
    }

    //testing για το Title Text View
    @Test
    public void TestTitleLogin(){
        TextView tv = (TextView) lgin.findViewById(R.id.textView15);
        assertNotNull(tv);
    }

    //testing για το Username Text View
    @Test
    public void TestUsernameTextView(){
        TextView tv = (TextView) lgin.findViewById(R.id.textUsername);
        assertNotNull(tv);
    }

    //testing για το Password Text View
    @Test
    public void TestPasswordTextView(){
        TextView tv = (TextView) lgin.findViewById(R.id.textPassword);
        assertNotNull(tv);
    }

    //testing για το login button
    @Test
    public void TestLogInButton (){
        Button btn = (Button)lgin.findViewById(R.id.buttonLogin);
        assertNotNull(btn);
    }

    //testing για το Register button
    @Test
    public void TestRegisterButton (){
        Button btn = (Button)lgin.findViewById(R.id.buttonRegister);
        assertNotNull(btn);
    }

}

