package app;

import app.models.Account;
import app.models.Image;
import app.models.Laptop;
import app.repositories.AccountRepositoryJPA;
import app.repositories.ImageRepository;
import app.repositories.Repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CommunicationbazaarServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicationbazaarServerApplication.class, args);
    }
}
