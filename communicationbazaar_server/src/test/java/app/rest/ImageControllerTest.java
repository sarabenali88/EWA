package app.rest;
import app.models.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    Image image;
    @BeforeEach
    public void setup() {
        image = new Image(0, null, "Version 2.5",
                null, null, null, null,
                null, 1, 2024, null, null);


    }

    @Test
    public void addAndUpdateOneImage() {
        ResponseEntity<Image> response =
                this.restTemplate.postForEntity("/images", image, Image.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Image createdImage = response.getBody();
        createdImage.setName("Testing123");

        ResponseEntity<Image> updateResponse = this.restTemplate.exchange("/images/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(createdImage),
                Image.class,
                createdImage.getId()
        );

        //check to see if it actually is updated
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        ResponseEntity<Image> getResponse = this.restTemplate.getForEntity("/images/" + createdImage.getId(),
                Image.class, createdImage.getId());

        Image getUpdatedImage = getResponse.getBody();
        assertEquals(createdImage.getId(), getUpdatedImage.getId());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());


    }
}
