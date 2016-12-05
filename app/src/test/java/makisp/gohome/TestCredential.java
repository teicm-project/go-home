package makisp.gohome;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Ευάγγελος Πετρόπουλος on 26/11/2016.
 */

public class TestCredential {
    Credential credential = new Credential(0, "Ευάγγελος", "123456", 1);
    Credential setCredential = new Credential();

    @Test
    public void testGetId() throws Exception {
        int id = credential.getId();
        assertEquals(0,id);
    }

    @Test
    public void testGetUsername() throws Exception {
        String username = credential.getUsername();
        assertEquals("Ευάγγελος", username);
    }

    @Test
    public void testGetPassword() throws Exception {
        String password = credential.getPassword();
        assertEquals("123456", password);
    }

    @Test
    public void testGetProgress() throws Exception {
        int progress = credential.getProgress();
        assertEquals(1, progress);
    }

    @Test
    public void testSetGetId() throws Exception {
        setCredential.setId(0);
        int id = credential.getId();
        assertEquals(0,id);
    }

    @Test
    public void testSetGetUsername() throws Exception {
        setCredential.setUsername("Ευάγγελος");
        String username = credential.getUsername();
        assertEquals("Ευάγγελος", username);
    }

    @Test
    public void testSetGetPassword() throws Exception {
        setCredential.setPassword("123456");
        String password = credential.getPassword();
        assertEquals("123456", password);
    }

    @Test
    public void testSetGetProgress() throws Exception {
        setCredential.setProgress(1);
        int progress = credential.getProgress();
        assertEquals(1, progress);
    }

    @Test
    public void testGetIdIsNotNull() throws Exception {
        int id = credential.getId();
        assertNotNull(id);
    }

    @Test
    public void testGetUsernameIsNotNull() throws Exception {
        String username = credential.getUsername();
        assertNotNull(username);
    }

    @Test
    public void testGetPasswordIsNotNull() throws Exception {
        String password = credential.getPassword();
        assertNotNull(password);
    }

    @Test
    public void testGetProgressIsNotNull() throws Exception {
        int progress = credential.getProgress();
        assertNotNull(progress);
    }
}
