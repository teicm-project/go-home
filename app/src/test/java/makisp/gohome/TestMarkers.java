package makisp.gohome;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ευάγγελος Πετρόπουλος on 20/12/2016.
 */

///// JUnit Tests /////
public class TestMarkers {
    Markers marker = new Markers();
    Markers IDMarker = new Markers(1, 89.4, 78.3);
    Markers withoutIDMarkers = new Markers(102.5, 156.3);

    @Test
    public void testMarkerIsNull(){
        int expectedID = 0;
        int actualID = marker.getId();
        double actualLatitude = marker.getLatitude();
        double actualLongitude = marker.getLongitude();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(0.0, actualLatitude, 0.0-actualLatitude);
        Assert.assertEquals(0.0, actualLongitude, 0.0-actualLongitude);
    }

    @Test
    public void testIDMarkerIsNotNull(){
        int expectedID = 1;
        int actualID = IDMarker.getId();
        double expectedLatitude = 89.4;
        double actualLatitude = IDMarker.getLatitude();
        double expectedLongitude = 78.3;
        double actualLongitude = IDMarker.getLongitude();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(expectedLatitude, actualLatitude, expectedLatitude-actualLatitude);
        Assert.assertEquals(expectedLongitude, actualLongitude, expectedLongitude-actualLongitude);
    }

    @Test
    public void testWithoutIDMarkerIsNotNull(){
        int expectedID = 0;
        int actualID = withoutIDMarkers.getId();
        double expectedLatitude = 102.5;
        double actualLatitude = withoutIDMarkers.getLatitude();
        double expectedLongitude = 156.3;
        double actualLongitude = withoutIDMarkers.getLongitude();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(expectedLatitude, actualLatitude, expectedLatitude-actualLatitude);
        Assert.assertEquals(expectedLongitude, actualLongitude, expectedLongitude-actualLongitude);
    }

    @Test
    public void testSetInventory(){
        marker.setId(10);
        marker.setLatitude(555.5);
        marker.setLongitude(98.6);

        int expectedID = 10;
        int actualID = marker.getId();
        double expectedLatitude = 555.5;
        double actualLatitude = marker.getLatitude();
        double expectedLongitude = 98.6;
        double actualLongitude = marker.getLongitude();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(expectedLatitude, actualLatitude, expectedLatitude-actualLatitude);
        Assert.assertEquals(expectedLongitude, actualLongitude, expectedLongitude-actualLongitude);
    }
}
