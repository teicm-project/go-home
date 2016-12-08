package makisp.gohome;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static makisp.gohome.R.id.test;

/**
 * Created by kevintso on 12/8/2016.
 */

public class ProfileActivityIntergrationTest extends ActivityInstrumentationTestCase2<ProfileActivity>
{
    public ProfileActivityIntergrationTest() {
        super(ProfileActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }


    @SmallTest
    public void testBtnAposindesh(){
        Button btn = (Button)getActivity().findViewById(R.id.btnaposindesi);
        assertNotNull(btn);
        }

    @SmallTest
    public void testProfileView(){
        TextView tv = (TextView) getActivity().findViewById(R.id.ProfileView);
        assertNotNull(tv);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
