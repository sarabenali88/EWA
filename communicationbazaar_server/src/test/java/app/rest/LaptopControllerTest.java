package app.rest;
import app.models.Laptop;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LaptopControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    Laptop newLaptop;
    @BeforeEach
    public void setup() {
        newLaptop = new Laptop(1770000, 2000016316125L, "ASUS",
                "TUF GAMING F15 FX507ZC4-HN002W", "Intel Core i7-12700H", "16 GB", "512 GB",
                "GeForce RTX 3050", "15.6 inch", "39.6 cm", "WIN11", 500
        );
    }

    @Test
    public void addAndUpdateOneLaptop() {
        ResponseEntity<Laptop> response =
                this.restTemplate.postForEntity("/laptops", newLaptop, Laptop.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Laptop createdLaptop = response.getBody();
        assertEquals(createdLaptop.getEan(), newLaptop.getEan());
        createdLaptop.setPrize(1000);

        ResponseEntity<Laptop> updateResponse = this.restTemplate.exchange("/laptops/{ean}",
                HttpMethod.PUT,
                new HttpEntity<>(createdLaptop),
                Laptop.class,
                createdLaptop.getEan()
        );

        //check to see if it actually is updated
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        ResponseEntity<Laptop> getResponse = this.restTemplate.getForEntity("/laptops/{ean}",
                Laptop.class, createdLaptop.getEan());

        Laptop getUpdatedLaptop = getResponse.getBody();
        assertEquals(createdLaptop.getPrize(), getUpdatedLaptop.getPrize());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());


    }
}
