package makisp.gohome;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.AndroidTestCase;
import android.widget.Button;
import android.widget.TextView;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


/**
 *
 * Created by Stergios Tselios on 16/12/2016.
 *
 */


public class ScenarioActivityTest extends AndroidTestCase {

    @Rule
    public ActivityTestRule<ScenarioActivity> scenarios = new ActivityTestRule<>(ScenarioActivity.class);

    ScenarioActivity scn;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp()
    {

        intent.setClass(appContext, ScenarioActivity.class);
        scn=scenarios.getActivity();
        scenarios.launchActivity(intent);

    }

    //testing για το button choice 1
    @Test
    public void TestButtonChoice1 ()
    {

        Button btn = (Button)scn.findViewById(R.id.button_choice1);
        assertNotNull(btn);

    }

    //testing για το button choice 2
    @Test
    public void TestButtonChoice2 ()
    {

        Button btn = (Button)scn.findViewById(R.id.button_choice2);
        assertNotNull(btn);

    }

    //testing για το Scenario_Title Text View
    @Test
    public void TestScenarioTittleTextVIew()
    {

        TextView tv = (TextView) scn.findViewById(R.id.scenario_title);
        assertNotNull(tv);

    }

    //testing για το Scenario_Story Text View
    @Test
    public void TestScenarioStoryTextVIew()
    {

        TextView tv = (TextView) scn.findViewById(R.id.buttonLogin);
        assertNotNull(tv);

    }




}
