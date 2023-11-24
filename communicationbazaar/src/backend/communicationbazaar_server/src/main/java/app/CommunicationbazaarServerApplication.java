package app;

import app.models.Account;
import app.repositories.AccountRepositoryJPA;
import app.repositories.Repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CommunicationbazaarServerApplication implements CommandLineRunner {

    @Autowired
    Repository<Account> accountRepository;
    @Autowired
    AccountRepositoryJPA accountRepositoryJPA;

    public static void main(String[] args) {
        SpringApplication.run(CommunicationbazaarServerApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Account> accounts =  accountRepository.findAll();
        if (!accounts.isEmpty()) return;

        accountRepository.save(new Account(2130234,
                "IloveApples4%",
                "Pieter van de Broek",
                "pietervdbroek@gmail.com",
                "ImageMaker",
                "Amsterdam",
                Account.LOGGEDIN.FALSE));
        accountRepository.save(new Account(2165954,
                "MacbookPro3$",
                "Julia Beekman",
                "juliabeekman@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE));
        accountRepository.save(new Account(2121843,
                "GroteBeer9!",
                "Guus de Jong",
                "guusdejong@gmail.com",
                "admin",
                "Apeldoorn",
                Account.LOGGEDIN.FALSE));
        accountRepository.save(new Account(2130901,
                "IloveBazary4%",
                "Cindy Visser",
                "cindyvisser@mm.nl",
                "ImageMaker",
                "Hoorn",
                Account.LOGGEDIN.FALSE));
    }
}
