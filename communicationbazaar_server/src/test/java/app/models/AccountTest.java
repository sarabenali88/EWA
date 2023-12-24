package app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    Image image1;
    Laptop laptop1;
    Account account1;

    @BeforeEach
    public void setup(){
        laptop1 = new Laptop(1770000, 2000016316124L, "ASUS",
                "TUF GAMING F15 FX507ZC4-HN002W", "Intel Core i7-12700H", "16 GB", "512 GB",
                "GeForce RTX 3050", "15.6 inch", "39.6 cm", "WIN11", 500
        );
        image1 = new Image(1008, laptop1, "MM V4.0.1", "Ede", "20-12-2023", Image.Status.TODO,
                Image.Release.NEW, Image.Problem.NO, 51, 2023, "testing123", null);
        account1 = new Account(0, "IloveApples4%", "Nazeera Ibrahim",
                "nazeera_ibrahim@gmail.com", "admin", "Rotterdam", Account.LOGGEDIN.TRUE);
    }

    @Test
    public void associatesAndDisassociatesImage(){
        assertTrue(account1.getImages().isEmpty());
        image1.setStatus(Image.Status.ONGOING);

        boolean associatedImage = account1.associateImage(image1);
        assertTrue(associatedImage);
        assertEquals(account1, image1.getImageMaker());
        assertTrue(account1.getImages().contains(image1));

        image1.setStatus(Image.Status.TODO);
        assertNotEquals(image1.getStatus(), Image.Status.ONGOING);

        account1.dissociateImage(image1);
        assertTrue(account1.getImages().isEmpty());

    }
}
