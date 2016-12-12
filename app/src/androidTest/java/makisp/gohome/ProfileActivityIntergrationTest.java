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

   // Τεστ για το textview που δείχνει το όνομα  του ον λινέ χριστή
    @SmallTest
    public void testProfileView(){
        TextView tv = (TextView) getActivity().findViewById(R.id.ProfileView);
        assertNotNull(tv);
    }

   // Τεστ για το κουμπί της αποσύνδεσης
    @SmallTest
    public void testBtnAposindesh(){
        Button btn = (Button)getActivity().findViewById(R.id.btnaposindesi);
        assertNotNull(btn);
        }

    //τεστ  το textview  προφιλ
    @SmallTest
    public void testOnoma(){
        TextView onoma = (TextView) getActivity().findViewById(R.id.textView14);
        assertNotNull(onoma);
    }

    //τεστ  το textview ονομα
    @SmallTest
    public void testprofil(){
        TextView profil = (TextView) getActivity().findViewById(R.id.textView);
        assertNotNull(profil);
    }



    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
