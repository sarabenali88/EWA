package app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;


public class ImageTest {

    Image image1, image2;
    @BeforeEach
    void setUp(){
        image1 = new Image();
        image2 = new Image();
    }

    @Test
    public void createSampleImageTest(){
        image1 = Image.createSampleImage(1000);
        image2 = Image.createSampleImage(1001);
        assertNotSame(image1, image2);
    }

}
