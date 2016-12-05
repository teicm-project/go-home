package makisp.gohome;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert.*;

/**
 * Created by Makis Papas on 5/12/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MarkerDistanceTest extends AndroidTestCase{

    GameActivity dst;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp(){
        MainActivity man;
        Intent intent2 = new Intent();
        intent2.setClass(appContext, MainActivity.class);
        man = main.getActivity();
        man = main.launchActivity(intent);
        LoginActivity.activeUser = 1;
    }

    @Rule
    public ActivityTestRule<GameActivity> distance = new ActivityTestRule<>(GameActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testDistance() {
        boolean marker;
        dst = distance.getActivity();
        intent.setClass(appContext, GameActivity.class);
        dst = distance.launchActivity(intent);
        marker = dst.markerDistance();

        Assert.assertFalse(marker);
    }
}
