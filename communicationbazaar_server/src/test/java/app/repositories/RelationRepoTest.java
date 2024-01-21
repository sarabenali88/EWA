 /**
 * A test class for our relation between Image and Account
 *
 * @author Jonathan
 */

 package app.repositories;

import app.models.Account;
import app.models.Image;
import app.repositories.Dataloader;
import app.repositories.Repository;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;

@Import({Dataloader.class, AccountRepositoryJPA.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = {org.springframework.stereotype.Repository.class, Entity.class}))
public class RelationRepoTest {

    @Autowired
    private AccountRepositoryJPA accountRepository;
    @Autowired
    CommandLineRunner dataLoader;


    @BeforeEach
    public void setup() throws Exception {
        this.dataLoader.run(null);
    }

    @Test
    public void testGetImagesFromAccount() {

        long accountId = 10000;

        List<Image> images = accountRepository.getImagesFromAccount(accountId);

        assertNotNull(images);
        assertFalse(images.isEmpty());

        for (Image image : images) {
            assertEquals(accountId, image.getImageMaker().getPersonalNumber());
        }
    }
}
