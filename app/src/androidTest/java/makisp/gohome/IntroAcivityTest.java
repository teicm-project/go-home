package makisp.gohome;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;


/**
 * Created by Stamatis Dachretzis
 */


public class IntroAcivityTest
{
    @Rule
    public ActivityTestRule<IntroActivity> intro = new ActivityTestRule<>(IntroActivity.class);

    IntroActivity intr;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp()
    {

        intent.setClass(appContext, ScenarioActivity.class);
        intr=intro.getActivity();
        intro.launchActivity(intent);

    }

    @Test
    public void TestButtonContinue ()
    {

        Button btn = (Button)intr.findViewById(R.id.buttonContinue);
        assertNotNull(btn);

    }

    @Test
    public void TestImageView()
    {

        ImageView iv = (ImageView) intr.findViewById(R.id.GifImageView);
        assertNotNull(iv);

    }

}
