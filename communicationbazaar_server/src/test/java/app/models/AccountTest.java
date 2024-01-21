package app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testAssociateImage() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        assertTrue(account.associateImage(image));
        assertTrue(account.getImages().contains(image));
        assertEquals(account, image.getImageMaker());
    }

    @Test
    public void testDissociateImage() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        account.associateImage(image);

        assertTrue(account.dissociateImage(image));
        assertFalse(account.getImages().contains(image));
        assertNull(image.getImageMaker());
    }

    @Test
    public void testAssociateImageTwice() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        assertTrue(account.associateImage(image));
        assertFalse(account.associateImage(image)); // Association already exists

        assertTrue(account.getImages().contains(image));
        assertEquals(account, image.getImageMaker());
    }

    @Test
    public void testDissociateNonAssociatedImage() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        assertFalse(account.dissociateImage(image)); // No association to dissociate

        assertFalse(account.getImages().contains(image));
        assertNull(image.getImageMaker());
    }

}
