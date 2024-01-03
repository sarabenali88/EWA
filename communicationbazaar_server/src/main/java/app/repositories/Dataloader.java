/**
 * This is a class that will generate test data for us to use
 * for the website
 *
 * @author Seyma Kaya
 */
package app.repositories;

import app.models.Account;
import app.models.Image;
import app.models.Laptop;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    AccountRepositoryJPA accountRepositoryJPA;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        this.createInitialAccount();
        this.createInitialImageAndLaptop();
    }

    @Autowired
    Repository<Account> accountRepository;

    private void createInitialAccount() {
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) return;

        Account account1 = new Account(0,
                "IloveApples4%",
                "Pieter van de Broek",
                "pietervdbroek@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE);
        Account account2 = new Account(0,
                "MacbookPro3",
                "Julia Beekman",
                "juliabeekman@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE);
        Account account3 = new Account(0,
                "GroteBeer9",
                "Guus de Jong",
                "guusdejong@gmail.com",
                "admin",
                "Apeldoorn",
                Account.LOGGEDIN.FALSE);
        Account account4 = new Account(0,
                "IloveBazary4%",
                "Cindy Visser",
                "cindyvisser@mm.nl",
                "ImageMaker",
                "Hoorn",
                Account.LOGGEDIN.FALSE);


        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);

        accountRepository.findById(10000).associateImage(imageRepository.findById(1001));
        accountRepository.findById(10000).associateImage(imageRepository.findById(1002));
        accountRepository.findById(10003).associateImage(imageRepository.findById(1003));
    }

    @Autowired
    Repository<Laptop> laptopRepository;
    @Autowired
    Repository<Image> imageRepository;

    private void createInitialImageAndLaptop() {
        List<Laptop> laptops = laptopRepository.findAll();
        List<Image> images = imageRepository.findAll();

        if (!images.isEmpty() && !laptops.isEmpty()) {
            return;
        }

        Laptop laptop1 = new Laptop(
                1637763,
                1961881940030L,
                "ACER",
                "SWIFT 5 SF515-51T-500N",
                "Intel Core i5-8265U",
                "8 GB",
                "512 GB + 512 GB",
                "UHD Graphics 620",
                "15.6 inch",
                "39.6 cm",
                "WIN11",
                500
        );
        Laptop laptop2 = new Laptop(
                1770000,
                2000016316124L,
                "ASUS",
                "TUF GAMING F15 FX507ZC4-HN002W",
                "Intel Core i7-12700H",
                "16 GB",
                "512 GB",
                "GeForce RTX 3050",
                "15.6 inch",
                "39.6 cm",
                "WIN11",
                500
        );
        Laptop laptop3 = new Laptop(
                1739480,
                4710180626902L,
                "HP",
                "VICTUS 16-E1430ND",
                "AMD Ryzen 7 5800H",
                "16 GB",
                "1 TB",
                "GeForce RTX 3050 Ti",
                "16.1 inch",
                "40.9 cm",
                "WIN11",
                500
        );
        Laptop laptop4 = new Laptop(
                1697952,
                195890201719L,
                "LENOVO",
                "YOGA SLIM7 13- RYZEN5 16GB 512GB",
                "AMD Ryzen 5 5600U",
                "16 GB",
                "512 GB SSD",
                "Radeon Graphics",
                "13.3 inch",
                "33.8 cm",
                "WIN11",
                500
        );
        Laptop laptop5 = new Laptop(
                1635568,
                5415247217718L,
                "APPLE",
                "Z0X20007Q/CTO/MBA 13 SG/1.6/16/512",
                "Intel Core i5-8400",
                "16 GB",
                "512 GB SSD",
                "UHD Graphics 617",
                "13.3 inch",
                "33.8 cm",
                null,
                500
        );

        Laptop laptop6 = new Laptop(
                1696810,
                195890851341L,
                "LENOVO",
                "GAMING 3 15-I5-11300H8GB 512GB RTX3050",
                "Intel Core i5-11300H",
                " 8 GB",
                "512 GB",
                "GeForce RTX 3050",
                "15.6 inch",
                "39.6 cm",
                "WIN11",
                500

        );
        Laptop laptop7 = new Laptop(
                1739468,
                196548846290L,
                "HP",
                "VICTUS 15-FA0290ND",
                "Intel Core i7-12700H",
                "16 GB",
                "1 TB",
                "GeForce RTX 3050 Ti",
                "15.6 inch",
                "39.6 cm",
                "WIN11",
                500
        );

        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);
        laptopRepository.save(laptop3);
        laptopRepository.save(laptop4);
        laptopRepository.save(laptop5);
        laptopRepository.save(laptop6);
        laptopRepository.save(laptop7);

        Image image1 = new Image(
                1001,
                laptop2,
                "MM V4.0.3",
                "Ede",
                "27-7-2023",
                Image.Status.TODO,
                Image.Release.UPDATE,
                Image.Problem.NO,
                30,
                2023,
                "ImageNaam2",
                null
        );
        Image image2 = new Image(
                1002,
                laptop3,
                "MM V4.0.4",
                "Tilburg",
                "2-9-2023",
                Image.Status.ONGOING,
                Image.Release.NEW,
                Image.Problem.NO,
                35,
                2023,
                "ImageNaam3",
                null
        );
        Image image3 = new Image(
                1003,
                laptop2,
                "MM V4.0",
                "Tilburg",
                "11-6-2023",
                Image.Status.FINISHED,
                Image.Release.NEW,
                Image.Problem.NO,
                23,
                2023,
                "ImageNaam4",
                null
        );
        Image image4 = new Image(
                1004,
                laptop1,
                "MM V4.0.1",
                "Hoorn",
                "10-5-2023",
                Image.Status.FINISHED,
                Image.Release.NEW,
                Image.Problem.NO,
                18,
                2023,
                "ImageNaam5",
                null
        );
        Image image5 = new Image(
                1005,
                laptop6,
                "MM V4.0.5",
                "Rotterdam",
                "5-12-2023",
                Image.Status.IMPOSSIBLE,
                Image.Release.NEW,
                Image.Problem.YES,
                49,
                2023,
                "ImageNaam6",
                null
        );

        Image image6 = new Image(
                1006,
                laptop7,
                "MM V4.6",
                "Apeldoorn",
                "9-11-2023",
                Image.Status.IMPOSSIBLE,
                Image.Release.NEW,
                Image.Problem.YES,
                45,
                2023,
                "ImageNaam7",
                null
        );

        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) return;

        Account account1 = new Account(0,
                "IloveApples4%",
                "Pieter van de Broek",
                "pietervdbroek@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE);
        Account account2 = new Account(0,
                "MacbookPro3",
                "Julia Beekman",
                "juliabeekman@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE);
        Account account3 = new Account(0,
                "GroteBeer9",
                "Guus de Jong",
                "guusdejong@gmail.com",
                "admin",
                "Apeldoorn",
                Account.LOGGEDIN.FALSE);
        Account account4 = new Account(0,
                "IloveBazary4%",
                "Cindy Visser",
                "cindyvisser@mm.nl",
                "ImageMaker",
                "Hoorn",
                Account.LOGGEDIN.FALSE);


        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);




//        imageRepository.findById(1001).associateAccount(accountRepository.findById(10000));
//        imageRepository.findById(1002).associateAccount(accountRepository.findById(10000));
//        imageRepository.findById(1003).associateAccount(accountRepository.findById(10003));


        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);
        imageRepository.save(image5);
        imageRepository.save(image6);

        image2.associateAccount(account1);
        image3.associateAccount(account1);
        image4.associateAccount(account4);

        account1.associateImage(image2);
        account1.associateImage(image3);
        account4.associateImage(image4);

        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);
        imageRepository.save(image5);
        imageRepository.save(image6);

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);



    }
}
