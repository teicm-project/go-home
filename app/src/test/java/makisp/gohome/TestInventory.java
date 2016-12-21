package makisp.gohome;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ευάγγελος Πετρόπουλος on 20/12/2016.
 */

///// JUnit Tests /////
public class TestInventory {
    Inventory inventory = new Inventory();
    Inventory usernameInventory = new Inventory(1, "Ευάγγελος", "Φτιάρι");
    Inventory IDInventory = new Inventory(1, "Κατσαβίδι");

    @Test
    public void testInventoryIsNull(){
        int expectedID = 0;
        int actualID = inventory.getId();
        String actualActiveUser = inventory.getActiveUser();
        String actualItem = inventory.getActiveUser();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertNull(actualActiveUser);
        Assert.assertNull(actualItem);
    }

    @Test
    public void testUsernameInventoryIsNotNull(){
        int expectedID = 1;
        int actualID = usernameInventory.getId();
        String expectedActiveUser = "Ευάγγελος";
        String actualActiveUser = usernameInventory.getActiveUser();
        String expectedItem = "Φτιάρι";
        String actualItem = usernameInventory.getItem();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(expectedActiveUser, actualActiveUser);
        Assert.assertEquals(expectedItem, actualItem);
    }

    @Test
    public void testIDInventoryIsNotNull(){
        int expectedID = 1;
        int actualID = IDInventory.getId();
        String expectedActiveUser = null;
        String actualActiveUser = IDInventory.getActiveUser();
        String expectedItem = "Κατσαβίδι";
        String actualItem = IDInventory.getItem();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(expectedActiveUser, actualActiveUser);
        Assert.assertEquals(expectedItem, actualItem);
    }

    @Test
    public void testSetInventory(){
        inventory.setId(10);
        inventory.setActiveUser("Σπύρος");
        inventory.setItem("Όπλο");

        int expectedID = 10;
        int actualID = inventory.getId();
        String expectedActiveUser = "Σπύρος";
        String actualActiveUser = inventory.getActiveUser();
        String expectedItem = "Όπλο";
        String actualItem = inventory.getItem();

        Assert.assertEquals(expectedID, actualID);
        Assert.assertEquals(expectedActiveUser, actualActiveUser);
        Assert.assertEquals(expectedItem, actualItem);
    }

}
