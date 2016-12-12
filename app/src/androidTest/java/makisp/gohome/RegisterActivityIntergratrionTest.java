package makisp.gohome;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kevintso on 12/7/2016.
 */

public class RegisterActivityIntergratrionTest extends ActivityInstrumentationTestCase2 <RegisterActivity> {

    public RegisterActivityIntergratrionTest() {
        super(RegisterActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    // τεστ για το textview username
    @SmallTest
    public void testTextUsername(){

        TextView uss = (TextView)getActivity().findViewById(R.id.TextUserName);
        assertNotNull(uss);
    }

    // τεστ για το textview password
    @SmallTest
    public void testTextPassword(){

        TextView pass = (TextView)getActivity().findViewById(R.id.TextPassword);
        assertNotNull(pass);
    }

    // Τεστ για το κουμπί της αποσύνδεσης
    @SmallTest
    public void testBtnPiswSthnSundesh(){

        Button btn = (Button) getActivity().findViewById(R.id.buttonsyndesh);
        assertNotNull(btn);
    }

    // Τεστ για το κουμπί της εγραφης
    @SmallTest
    public void testBtnEgrafh(){

        Button btn = (Button) getActivity().findViewById(R.id.buttonEgrafh);
        assertNotNull(btn);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
