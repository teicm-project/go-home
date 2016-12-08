package makisp.gohome;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ListView;

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

     @SmallTest
     public void testListaTestingifNotNull(){

         ListView lv = (ListView)getActivity().findViewById(R.id.lista);
         assertNotNull(lv);
     }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}


