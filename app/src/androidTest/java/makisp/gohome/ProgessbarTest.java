package makisp.gohome;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.AndroidTestCase;
import android.widget.ProgressBar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Makis Papas on 21/12/2016.
 */

public class ProgessbarTest extends AndroidTestCase{

    @Rule
    public ActivityTestRule<GameActivity> scenarios = new ActivityTestRule<>(GameActivity.class);

    GameActivity gamea;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp()
    {

        intent.setClass(appContext, GameActivity.class);
        gamea = scenarios.getActivity();
        scenarios.launchActivity(intent);

    }

    //Is Progressbar Not Null Test
    @Test
    public void NotNullTest(){

        ProgressBar progb = (ProgressBar) gamea.findViewById(R.id.barHorizontal);
        assertNotNull(progb);
    }

    //Espresso UI Testing

    //Is Progressbar Enabled Test
    @Test
    public void isEnabledTest(){

        onView(withId(R.id.barHorizontal)).check(matches(isEnabled()));
    }

    //Is Progressbar Not Enabled Test
    @Test
    public void isNotEnabledTest(){

        onView(withId(R.id.barHorizontal)).check(matches(not(isEnabled())));
    }

    //Is Progressbar Displayed Test
    @Test
    public void isDisplayedTest(){

        onView(withId(R.id.barHorizontal)).check(matches(isDisplayed()));
    }

    //Is Progressbar Not Displayed Test
    @Test
    public void isNotDisplayedTest(){

        onView(withId(R.id.barHorizontal)).check(matches(not(isDisplayed())));
    }

}
