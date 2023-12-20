package app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ImageTest {

    Image image1, image2;
    Laptop laptop1;

    @BeforeEach
    void setUp(){
        this.laptop1 = new Laptop(1739468, 196548846290L, "HP", "VICTUS 15-FA0290ND",
                "Intel Core i7-12700H", "16 GB", "1 TB", "GeForce RTX 3050 Ti",
                "15.6 inch", "39.6 cm", "WIN11", 500);
        this.image1 = new Image(10010, laptop1, "MM V4.6", "Apeldoorn", "9-11-2023",
                Image.Status.IMPOSSIBLE, Image.Release.NEW, Image.Problem.YES, 45, 2023,
                "ImageNaam7", null);

        this.image2 = new Image(10010, laptop1, "MM V4.6", "Apeldoorn", "9-11-2023",
                Image.Status.FINISHED, Image.Release.UPDATE, Image.Problem.YES, 45, 2023,
                "ImageNaam7", null);
    }

    @Test
    public void createNewImage(){

    }

}
