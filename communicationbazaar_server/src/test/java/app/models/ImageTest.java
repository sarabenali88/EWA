package app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImageTest {

    Image image1, image1a;

    @BeforeEach
    void setup(){
       this.image1 = Image.createSampleImage(1001);
       this.image1a = Image.createSampleImage(1001);
    }

    @Test
    void createSampleImageCreatesTwoDifferentImages(){
        assertNotSame(this.image1.hashCode(), this.image1a.hashCode());
    }
}
