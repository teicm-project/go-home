package makisp.gohome;

import android.app.Instrumentation;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

import makisp.gohome.LoginActivity;
import makisp.gohome.ProfileActivity;
import makisp.gohome.R;

/**
 *
 * Created by Stergios Tselios on 16/12/2016.
 *
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule
            = new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void checkProfileBackButton() {
        String String_To_Type = "test123";

        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(ProfileActivity.class.getName(), null, false);

        // Press the button.
        onView(withId(R.id.textUsername)).perform(typeText(String_To_Type),
                closeSoftKeyboard());
        onView(withId(R.id.textPassword)).perform(typeText(String_To_Type),
                closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());





        //Watch for the timeout
        ProfileActivity nextActivity = (ProfileActivity) getInstrumentation()
                .waitForMonitorWithTimeout(activityMonitor, 5000);

        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();



    }



}
