/**
 * Test for Image repository
 * @author Sara Benali
 */
package app.repositories;

import app.models.Image;
import app.models.Laptop;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(Dataloader.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = {org.springframework.stereotype.Repository.class, Entity.class}))
public class ImagesRepositoryTest {
    @Autowired
    private Repository<Image> imageRepo;
    @Autowired
    CommandLineRunner dataLoader;
    private List<Image> someImages;

    @BeforeEach
    public void setup() throws Exception {
        this.dataLoader.run(null);
        this.someImages = this.imageRepo.findAll();
    }

    /**
     * This test tests that an image can be created and can be deleted
     * @author Sara Benali
     */
    @Test
    public void savesAndDeletesImage() {
        Laptop newLaptop = new Laptop(1770000, 2000016316124L, "ASUS",
                "TUF GAMING F15 FX507ZC4-HN002W", "Intel Core i7-12700H", "16 GB", "512 GB",
                "GeForce RTX 3050", "15.6 inch", "39.6 cm", "WIN11", 500
        );
        Image newImage = new Image(
                0, newLaptop, "MM V4.0.1", "Ede", "20-12-2023", Image.Status.FINISHED,
                Image.Release.NEW, Image.Problem.NO, 51, 2023, "testing123", null
        );

        Image savedImage = imageRepo.save(newImage);

        Image findSavedImage = imageRepo.findById(savedImage.getId());
        assertNotNull(findSavedImage);
        assertEquals(newImage.getId(), savedImage.getId());

        Image deleteSavedImage = imageRepo.deleteById(savedImage.getId());
        assertNotNull(deleteSavedImage);

        Image deletedImage = imageRepo.findById(savedImage.getId());
        assertNull(deletedImage);

    }
}

