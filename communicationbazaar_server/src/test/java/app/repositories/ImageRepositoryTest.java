/**
 * A test class for the Image Repository
 *
 * @author Seyma Kaya
 */
package app.repositories;

import app.models.Image;

import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Import(Dataloader.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = {org.springframework.stereotype.Repository.class, Entity.class}))
public class ImageRepositoryTest {
    @Autowired
    private Repository<Image> imageRepo;
    @Autowired
    CommandLineRunner dataLoader;
    private List<Image> images;

    @BeforeEach
    public void setup() throws Exception {
        this.dataLoader.run(null);
        this.images = this.imageRepo.findAll();
    }

    /**
     * a test that checks whether images were correctly loaded during the setup
     *
     * @author Seyma Kaya
     */
    @Test
    public void repoFindAllReturnsAll() {
        assertTrue(this.images.size() > 0);
    }

    /**
     * a test that checks whether an image can be saved and updated
     *
     * @author Seyma Kaya
     */
    @Test
    public void repoSavesAndUpdatesAnImage(){
        Image image = new Image();
        Image savedImage = this.imageRepo.save(image);
        Image foundImage = this.imageRepo.findById(savedImage.getId());

        assertTrue(savedImage.getId() > 0);
        assertEquals(foundImage.getId(), savedImage.getId());

        savedImage.setName("New Image Name");
        foundImage = this.imageRepo.save(savedImage);

        assertSame(savedImage.getName(), foundImage.getName());
    }

}
