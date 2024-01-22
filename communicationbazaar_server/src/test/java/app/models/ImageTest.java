/**
 * Test for Image modal
 * @author Sara Benali
 */
package app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ImageTest {

    Image image1, image2;
    @BeforeEach
    void setUp(){
        image1 = new Image();
        image2 = new Image();
    }

    /**
     * This test tests that two images are not the same when they are created
     * @author Sara Benali
     */
    @Test
    public void createSampleImageTest(){
        image1 = Image.createSampleImage(1000);
        image2 = Image.createSampleImage(1001);
        assertNotSame(image1, image2);
    }

}
