package makisp.gohome;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.ProgressBar;
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

public class GameActivityTest {

    @Rule
    public ActivityTestRule<GameActivity> game = new ActivityTestRule<>(GameActivity.class);

    GameActivity gm;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp() {
        intent.setClass(appContext, ScenarioActivity.class);
        gm = game.getActivity();
        game.launchActivity(intent);
    }

    //testing για το button profile
    @Test
    public void TestButtonProfile (){
        Button btn = (Button)gm.findViewById(R.id.buttonProfile);
        assertNotNull(btn);
    }

    //testing για το button items
    @Test
    public void TestButtonItems (){
        Button btn = (Button)gm.findViewById(R.id.buttonItems);
        assertNotNull(btn);
    }

    //testing για το textview name
    @Test
    public void TestNameTextView() {
        TextView tv = (TextView) gm.findViewById(R.id.textView12);
        assertNotNull(tv);
    }

    //testing για το textview progress
    @Test
    public void ProgressTextView() {
        TextView tv = (TextView) gm.findViewById(R.id.textView13);
        assertNotNull(tv);
    }

    //testing για το progress bar
    @Test
    public void ProgressBar() {
        ProgressBar pb = (ProgressBar) gm.findViewById(R.id.barHorizontal);
        assertNotNull(pb);
    }

}
