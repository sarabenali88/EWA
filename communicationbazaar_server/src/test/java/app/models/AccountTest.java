package app.models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for our relation between Image and Account
 *
 * @author Jonathan
 */
public class AccountTest {

    /**
     * A test that checks whether you can associate an Image
     */
    @Test
    public void testAssociateImage() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        assertTrue(account.associateImage(image));
        assertTrue(account.getImages().contains(image));
        assertEquals(account, image.getImageMaker());
    }

    /**
     * A test that checks whether you can dissociate an Image
     */
    @Test
    public void testDissociateImage() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        account.associateImage(image);

        assertTrue(account.dissociateImage(image));
        assertFalse(account.getImages().contains(image));
        assertNull(image.getImageMaker());
    }

    /**
     * A test that checks whether you can associate an Image twice
     */
    @Test
    public void testAssociateImageTwice() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        assertTrue(account.associateImage(image));
        assertFalse(account.associateImage(image)); // Association already exists

        assertTrue(account.getImages().contains(image));
        assertEquals(account, image.getImageMaker());
    }

    /**
     * A test that checks whether you can dissociate a non associated image.
     */
    @Test
    public void testDissociateNonAssociatedImage() {
        Account account = Account.createSampleAccount();
        Image image = Image.createSampleImage(1);

        assertFalse(account.dissociateImage(image)); // No association to dissociate

        assertFalse(account.getImages().contains(image));
        assertNull(image.getImageMaker());
    }

}
