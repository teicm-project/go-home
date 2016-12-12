package makisp.gohome;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by kevintso on 12/8/2016.
 */

public class ItemsActivityIntergrationTest extends ActivityInstrumentationTestCase2<ItemsActivity> {
    public ItemsActivityIntergrationTest() {
        super(ItemsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    //τεστ  το listview
     @SmallTest
     public void testListaTestingifNotNull(){

         ListView lv = (ListView)getActivity().findViewById(R.id.lista);
         assertNotNull(lv);
     }

    //τεστ  το textview  αντικειμενα
    @SmallTest
    public void testTextTestingifNotNull(){

        TextView mv = (TextView) getActivity().findViewById(R.id.textView15);
        assertNotNull(mv);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}


