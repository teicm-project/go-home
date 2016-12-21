package makisp.gohome;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by kevintso on 12/9/2016.
 */


@RunWith(AndroidJUnit4.class)

public class EsspresoUiTest {

    @Rule public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testCheckIfNameWorksInProfileActivity() {
        onView(withId(R.id.buttonStart)).perform(click());

        onView(withId(R.id.textUsername)).perform(typeText("p"),
                closeSoftKeyboard());
        onView(withId(R.id.textPassword)).perform(typeText("p"),
                closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.buttonContinue)).perform(click());

        onView(withId(android.R.id.button2)).perform(click());

        onView(withId(R.id.buttonProfile)).perform(click());

        onView(withId(R.id.ProfileView)).check(matches(withText("p")));

    }

    @Test
    public void testCheckIfItemsDisplayedWellInItemsActivity(){

        onView(withId(R.id.buttonStart)).perform(click());

        onView(withId(R.id.textUsername)).perform(typeText("p"),
                closeSoftKeyboard());
        onView(withId(R.id.textPassword)).perform(typeText("p"),
                closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.buttonContinue)).perform(click());

        onView(withId(android.R.id.button2)).perform(click());

        onView(withId(R.id.buttonItems)).perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.lista))
                .atPosition(1).check(matches(withText("maxeri")));

    }

    @Test
    public void testCheckIfRegisterWorkCorrect(){

        onView(withId(R.id.buttonStart)).perform(click());

        onView(withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.TextUserName)).perform(typeText("lamda"),
                closeSoftKeyboard());

        onView(withId(R.id.TextPassword)).perform(typeText("lamda"),
                closeSoftKeyboard());

        onView(withId(R.id.buttonEgrafh)).perform(click());

        onView(withId(R.id.buttonsyndesh)).perform(click());

        onView(withId(R.id.textUsername)).perform(typeText("lamda"),
                closeSoftKeyboard());
        onView(withId(R.id.textPassword)).perform(typeText("lamda"),
                closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.buttonContinue)).perform(click());

        onView(withId(android.R.id.button2)).perform(click());


    }



}
