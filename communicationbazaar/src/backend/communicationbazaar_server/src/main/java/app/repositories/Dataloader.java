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
        this.createInitialAccount();
        this.createInitialImageAndLaptop();
    }

    @Autowired
    Repository<Account> accountRepository;
    private void createInitialAccount() {
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) return;

        accountRepository.save(new Account(0,
                "IloveApples4%",
                "Pieter van de Broek",
                "pietervdbroek@gmail.com",
                "ImageMaker",
                "Amsterdam",
                Account.LOGGEDIN.FALSE));
        accountRepository.save(new Account(0,
                "MacbookPro3$",
                "Julia Beekman",
                "juliabeekman@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE));
        accountRepository.save(new Account(0,
                "GroteBeer9!",
                "Guus de Jong",
                "guusdejong@gmail.com",
                "admin",
                "Apeldoorn",
                Account.LOGGEDIN.FALSE));
        accountRepository.save(new Account(0,
                "IloveBazary4%",
                "Cindy Visser",
                "cindyvisser@mm.nl",
                "ImageMaker",
                "Hoorn",
                Account.LOGGEDIN.FALSE));
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
                4710180623840L,
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
                4711387062937L,
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
                196548844210L,
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

        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);
        laptopRepository.save(laptop3);

        imageRepository.save(new Image(
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
                "",
                ""
        ));
        imageRepository.save(new Image(
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
                "",
                "Pieter van de Broek"
        ));
        imageRepository.save(new Image(
                1003,
                laptop2,
                "MM V4.0",
                "Dordrecht",
                "11-6-2023",
                Image.Status.FINISHED,
                Image.Release.NEW,
                Image.Problem.NO,
                23,
                2023,
                "ImageNaam4",
                "",
                "Pieter van de Broek"
        ));
        imageRepository.save(new Image(
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
                "",
                "Cindy Visser"
        ));
    }
}
