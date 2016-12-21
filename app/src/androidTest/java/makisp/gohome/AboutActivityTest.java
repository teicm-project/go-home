package makisp.gohome;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Efi Gkaragkatza on 20/12/2016.
 */

public class AboutActivityTest
{

    @Rule
    public ActivityTestRule<AboutActivity> about = new ActivityTestRule<>(AboutActivity.class);

    AboutActivity abt;
    Intent intent = new Intent();
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void SetUp()
    {

        intent.setClass(appContext, ScenarioActivity.class);
        abt=about.getActivity();
        about.launchActivity(intent);

    }


    @Test
    public void TestTextVIew11()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView11);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew2()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView2);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew3()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView3);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew4()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView4);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew5()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView5);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew6()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView6);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew7()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView7);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew8()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView8);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew9()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView9);
        assertNotNull(tv);

    }

    @Test
    public void TestTextVIew10()
    {

        TextView tv = (TextView) abt.findViewById(R.id.textView10);
        assertNotNull(tv);

    }

}