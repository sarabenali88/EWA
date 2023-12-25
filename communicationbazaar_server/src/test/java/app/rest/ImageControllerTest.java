/**
 * A test class for the Image rest controller
 *
 * @author Seyma Kaya
 */
package app.rest;

import app.models.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ImageControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * A test that checks whether all the images are retrieved
     *
     * @author Seyma Kaya
     */
    @Test
    public void allImagesAreRetrieved(){
        ResponseEntity<Image[]> response =
                this.testRestTemplate.getForEntity("/images", Image[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Image[] images = response.getBody();
        assertThat(images.length, is(greaterThan(0)));

    }

    /**
     * A test that checks whether an Image is correctly posted to the backend
     *
     * @author Seyma Kaya
     */
    @Test
    public void aNewImageCanBePosted(){
        Image image = new Image(0, null, "Version 2.3", null, null, null, null, null, 1, 2024, null, null);

        ResponseEntity<Image> response =
                this.testRestTemplate.postForEntity("/images", image, Image.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Image responseImage = response.getBody();
        assertThat(responseImage.getId(), is(greaterThan(0L)));
        assertEquals(responseImage.getVersion(), image.getVersion());

        ResponseEntity<Image> response2 =
                this.testRestTemplate.getForEntity("/images/" + responseImage.getId(), Image.class);
        assertEquals(HttpStatus.OK, response2.getStatusCode());

        Image responseImage2 = response2.getBody();
        assertEquals(responseImage2.getVersion(), responseImage.getVersion());

    }
}
