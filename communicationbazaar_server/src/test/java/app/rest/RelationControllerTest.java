package app.rest;

import app.models.Account;
import app.models.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for our relation between Image and Account
 *
 * @author Jonathan
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RelationControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    /**
     * A test that checks whether all images associated with an account are retrieved
     */
    @Test
    public void getImagesFromAccount() {
        long personalNumber = 10000;

        ResponseEntity<Image[]> response =
                this.testRestTemplate.getForEntity("/accounts/" + personalNumber + "/images", Image[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Image[] images = response.getBody();
        assertNotNull(images);

        assertEquals(2, images.length);
    }

    /**
     * A test that checks whether an image can be associated with an account
     */
    @Test
    public void associateImageWithAccount() {

        long personalNumber = 10002;

        //create image and save it
        Image image = new Image(0, null, "Version 2.3", null, null, null, null, null, 1, 2024, null, null);

        ResponseEntity<Image> response =
                this.testRestTemplate.postForEntity("/images", image, Image.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Image savedImage = response.getBody();


        //get account and associate image
        ResponseEntity<Account> responseAccount =
                this.testRestTemplate.getForEntity("/accounts/" + personalNumber, Account.class);
        assertEquals(HttpStatus.OK, responseAccount.getStatusCode());

        Account account = responseAccount.getBody();

        savedImage.associateAccount(account);

        //Save the updates image
        ResponseEntity<Image> responseAssociatedImage =
                this.testRestTemplate.exchange("/images/" + savedImage.getId(), HttpMethod.PUT, new HttpEntity<>(savedImage), Image.class);
        assertEquals(HttpStatus.OK, responseAssociatedImage.getStatusCode());

        //check if the associated image is there
        ResponseEntity<Image[]> reponseCorrectImage =
                this.testRestTemplate.getForEntity("/accounts/" + personalNumber + "/images", Image[].class);
        assertNotNull(reponseCorrectImage.getBody());
        assertEquals(1, reponseCorrectImage.getBody().length);
        assertEquals(savedImage.getId(), reponseCorrectImage.getBody()[0].getId());
        assertEquals(savedImage.getImageMaker().getPersonalNumber(), reponseCorrectImage.getBody()[0].getImageMaker().getPersonalNumber());

    }



}
