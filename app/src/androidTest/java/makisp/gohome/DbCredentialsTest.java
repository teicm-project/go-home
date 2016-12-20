package makisp.gohome;

import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Ευάγγελος Πετρόπουλος on 4/12/2016.
 */

public class DbCredentialsTest extends AndroidTestCase {
    private DbCredentials dbCredentials;
    private Credential credential;
    private List<Credential> credentialList;

    @Before
    public void setUp(){
        dbCredentials = new DbCredentials(mContext);
    }

    @After
    public void finish(){
        dbCredentials.close();
        mContext.deleteDatabase(DbCredentials.DATABASE_NAME);
    }

    @Test
    public void testAddGetCredentialIsNotNull() throws Exception{
        dbCredentials.addCredential(new Credential(1, "Ευάγγελος", "123456789", 1));
        credential = dbCredentials.getCredential(1);
        assertNotNull(credential);
    }

    @Test
    public void testAddGetCredentialExpectedValues() throws Exception{
        int actualId, actualProgress;
        String actualUsername, actualPassword;
        dbCredentials.addCredential(new Credential(1, "Ευάγγελος", "123456789", 1));
        credential = dbCredentials.getCredential(1);
        actualId = credential.getId();
        actualUsername = credential.getUsername();
        actualPassword = credential.getPassword();
        actualProgress = credential.getProgress();

        assertEquals(1, actualId);
        assertEquals("Ευάγγελος", actualUsername);
        assertEquals("123456789", actualPassword);
        assertEquals(1, actualProgress);
    }

    @Test
    public void testGetAllCredentialsIsNotNull() throws Exception{
        dbCredentials.addCredential(new Credential(1, "Ευάγγελος", "123456789", 1));
        credentialList = dbCredentials.getAllCredentials();

        assertNotNull(credentialList);
    }

    @Test
    public void testGetAllCredentialsIsInList() throws Exception{
        int actualId = 0, actualProgress = 0;
        String actualUsername = "", actualPassword = "";

        dbCredentials.addCredential(new Credential(1, "Ευάγγελος", "123456789", 1));
        credentialList = dbCredentials.getAllCredentials();

        for(Credential credential : credentialList) {
            actualId = credential.getId();
            actualUsername = credential.getUsername();
            actualPassword = credential.getPassword();
            actualProgress = credential.getProgress();
            break;
        }

        assertEquals(1, actualId);
        assertEquals("Ευάγγελος", actualUsername);
        assertEquals("123456789", actualPassword);
        assertEquals(1, actualProgress);
    }

    @Test
    public void testGetCredentialsCountIsNotNull() throws Exception{
        int count;
        count = dbCredentials.getCredentialsCount();

        assertNotNull(count);
    }

    @Test
    public void testGetCredentialsCountIs1() throws Exception{
        int count;
        count = dbCredentials.getCredentialsCount();

        assertEquals(1,count);
    }

    @Test
    public void testUpdateCredential1RowUpdated() throws Exception{
        int update;
        credential = new Credential(1, "Βαγγέλης", "987654321", 10);
        update = dbCredentials.updateCredential(credential);

        assertEquals(1,update);
    }

    @Test
    public void testUpdateCredential1RowUpdatedExpectedActual() throws Exception{
        Credential updatedCredential = dbCredentials.getCredential(1);

        assertEquals(1, updatedCredential.getId());
        assertEquals("Βαγγέλης", updatedCredential.getUsername());
        assertEquals("987654321", updatedCredential.getPassword());
        assertEquals(10, updatedCredential.getProgress());
    }

    @Test
    public void testUpdateCredentialExpectedActual() throws Exception{
        Credential credentialUpdate = new Credential(1, "Ευάγγελος", "123456789", 1);
        dbCredentials.updateCredential(credentialUpdate);
        Credential actualCredential = dbCredentials.getCredential(1);

        assertEquals(1, actualCredential.getId());
        assertEquals("Ευάγγελος", actualCredential.getUsername());
        assertEquals("123456789", actualCredential.getPassword());
        assertEquals(1, actualCredential.getProgress());
    }

    @Test
    public void testDeleteCredentialWithId1IsNull() throws Exception{
        Credential credentialToDelete = new Credential(1, "Ευάγγελος", "123456789", 1);
        dbCredentials.deleteCredential(credentialToDelete);
        credential = dbCredentials.getCredential(1);
        assertNull(credential);
    }

    @Test
    public void testAddGetItemIsNotNull() throws Exception{
        Inventory inventory;
        dbCredentials.addItem(new Inventory(1, "Φτιάρι"));
        inventory = dbCredentials.getItem(1);
        assertNotNull(inventory);
    }

    @Test
    public void testAddGetItemIsNull() throws Exception{
        Inventory inventory;
        dbCredentials.addItem(new Inventory(1, "Φτιάρι"));
        inventory = dbCredentials.getItem(0);
        assertNull(inventory);
    }

    @Test
    public void testAddGetItemExpectedValues() throws Exception{
        Inventory inventory;
        int actualId;
        String actualItem;
        dbCredentials.addItem(new Inventory(1, "Φτιάρι"));
        inventory = dbCredentials.getItem(1);
        actualId = inventory.getId();
        actualItem = inventory.getItem();

        assertEquals(1, actualId);
        assertEquals("Φτιάρι", actualItem);
    }

    @Test
    public void testGetAllItemsIsNotNull() throws Exception{
        List<Inventory> itemsList;
        dbCredentials.addItem(new Inventory(1, "Φτιάρι"));
        itemsList = dbCredentials.getAllItems();

        assertNotNull(itemsList);
    }

    @Test
    public void testGetAllItemsIsInList() throws Exception{
        List<Inventory> itemsList;
        int actualId = 0;
        String actualItem = "";

        dbCredentials.addItem(new Inventory(1, "Φτιάρι"));
        itemsList = dbCredentials.getAllItems();

        for(Inventory items : itemsList) {
            actualId = items.getId();
            actualItem = items.getItem();
            break;
        }

        assertEquals(1, actualId);
        assertEquals("Φτιάρι", actualItem);
    }

    @Test
    public void testGetItemsCountIsNotNull() throws Exception{
        int count;
        count = dbCredentials.getItemsCount(1);

        assertNotNull(count);
    }

    @Test
    public void testGetItemsOfActiveUserCountIsGreaterThan1() throws Exception{
        int count;
        boolean greater = false;
        count = dbCredentials.getItemsCount(1);

        if(count > 0)
            greater = true;

        assertTrue(greater);
    }

    @Test
    public void testGetItemsOfActiveUserCountIsLessThan1() throws Exception{
        int count;
        boolean greater = true;
        count = dbCredentials.getItemsCount(2);

        if(!(count > 0))
            greater = false;

        assertFalse(greater);
    }

    @Test
    public void testUpdateItemRowsUpdated() throws Exception{
        Inventory inventory;
        int update;
        boolean updated = false;
        inventory = new Inventory(1, "Μαχαίρι");
        update = dbCredentials.updateItem(inventory);

        if(update > 0)
            updated = true;
        assertTrue(updated);
    }

    @Test
    public void testUpdateItem1RowsUpdatedExpectedActual() throws Exception{
        Inventory updatedItem = dbCredentials.getItem(1);

        assertEquals(1, updatedItem.getId());
        assertEquals("Φτιάρι", updatedItem.getItem());
    }

    @Test
    public void testUpdateItemsExpectedActual() throws Exception{
        Inventory inventoryUpdate = new Inventory(1, "Φτιάρι");
        dbCredentials.updateItem(inventoryUpdate);
        Inventory actualItem = dbCredentials.getItem(1);

        assertEquals(1, actualItem.getId());
        assertEquals("Φτιάρι", actualItem.getItem());
    }

    @Test
    public void testDeleteItemWithId1IsNull() throws Exception{
        Inventory itemToDelete = new Inventory(1, "Φτιάρι");
        dbCredentials.deleteItem(itemToDelete);
        Inventory item = dbCredentials.getItem(1);
        assertNull(item);
    }

    @Test
    public void testAddMarkerIsNotNull(){
        Markers markers = new Markers();
        markers.setId(1);
        markers.setLatitude(59856.5);
        markers.setLongitude(48595.5);
        dbCredentials.addMarkers(markers);

        Assert.assertNotNull(dbCredentials.getMarker(1));
    }

    @Test
    public void testGetMarkerExpected(){
        Markers expectedMarker = new Markers();
        expectedMarker.setId(1);
        expectedMarker.setLatitude(59856.5);
        expectedMarker.setLongitude(48595.5);
        dbCredentials.addMarkers(expectedMarker);

        Markers actualMarker = dbCredentials.getMarker(1);

        Assert.assertNotNull(actualMarker);
        Assert.assertEquals(expectedMarker.getId(), actualMarker.getId());
        Assert.assertEquals(expectedMarker.getLatitude(), actualMarker.getLatitude(), expectedMarker.getLatitude()-actualMarker.getLatitude());
        Assert.assertEquals(expectedMarker.getLongitude(), actualMarker.getLongitude(), expectedMarker.getLongitude()-actualMarker.getLongitude());
    }

    @Test
    public void testGetAllMarkersIsNotNull(){
        dbCredentials.addMarkers(new Markers(2, 568.5, 598.4));
        List<Markers> markersList = dbCredentials.getAllMarkers();

        assertNotNull(markersList);
    }

    @Test
    public void testGetAllMarkersIsInList() throws Exception{
        int actualId = 0;
        double actualLatitude=0, actualLongitude=0;
        dbCredentials.addMarkers(new Markers(3, 568.5, 598.4));
        List<Markers> markersList = dbCredentials.getAllMarkers();

        for(Markers markers : markersList) {
            actualId = markers.getId();
            actualLatitude = markers.getLatitude();
            actualLongitude = markers.getLongitude();
            break;
        }

        assertEquals(1, actualId);
        assertEquals(59856.5, actualLatitude);
        assertEquals(48595.5, actualLongitude);
    }

    @Test
    public void testGetMarkersCountIsNotNull() throws Exception{
        int count;
        count = dbCredentials.getMarkersCount();

        assertNotNull(count);
    }

    @Test
    public void testGetMarkersCountIs3() throws Exception{
        int count;
        count = dbCredentials.getMarkersCount();

        assertEquals(3,count);
    }

    @Test
    public void testUpdateMarker1RowUpdated() throws Exception{
        int update;
        Markers marker = new Markers(1, 56.5, 89.5);
        update = dbCredentials.updateMarkers(marker);

        assertEquals(1,update);
    }

    @Test
    public void testUpdateMarker1RowUpdatedExpectedActual() throws Exception{
        Markers updatedMarker = dbCredentials.getMarker(1);

        assertEquals(1, updatedMarker.getId());
        assertEquals(56.5, updatedMarker.getLatitude());
        assertEquals(89.5, updatedMarker.getLongitude());
    }

    /**
     * Created by Iwanna Pantoyla on 6/12/2016.
     */

    @Test
    public void testDeleteMarkers() throws Exception{
        Markers deleteMarker = new Markers(1,1);
        dbCredentials.deleteMarkers(deleteMarker);
        Markers marker = dbCredentials.getMarker(1);
        assertNull(marker);
    }
}
