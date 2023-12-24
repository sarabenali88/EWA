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
public class LaptopRepositoryTest {

    Laptop laptop1;
    Laptop laptop2;

    @Autowired
    CommandLineRunner dataLoader;
    private List<Laptop> someLaptops;
    @Autowired
    Repository<Laptop> laptopRepo;
    @BeforeEach
    public void setUp() throws Exception{
        this.dataLoader.run(null);
        this.someLaptops = this.laptopRepo.findAll();
        laptop1 = new Laptop(1637763, 2961881940030L, "ACER", "SWIFT 5 SF515-51T-500N",
                "Intel Core i5-8265U", "8 GB", "512 GB + 512 GB", "UHD Graphics 620",
                "15.6 inch", "39.6 cm", "WIN11", 500);
        laptop2 = new Laptop(1770000, 2100016316124L, "ASUS", "TUF GAMING F15 FX507ZC4-HN002W",
                "Intel Core i7-12700H", "16 GB", "512 GB", "GeForce RTX 3050",
                "15.6 inch", "39.6 cm", "WIN11", 500);

    }

   @Test
    public void findsAllLaptopsAndChecksImages(){
        laptopRepo.save(laptop1);
        laptopRepo.save(laptop2);

        List<Laptop> allLaptops = laptopRepo.findAll();
        assertNotNull(allLaptops);
        assertEquals(9, allLaptops.size());

        List<Image> checkLaptop = laptop2.getImages();
        assertTrue(checkLaptop.isEmpty());


   }
}
