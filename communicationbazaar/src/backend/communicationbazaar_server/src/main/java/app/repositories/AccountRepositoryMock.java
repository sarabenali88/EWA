/**
 * This is a class that will give the repository functions an action for the accounts.
 *
 * @author Jasper Fernhout
 */
package app.repositories;

import app.models.Account;
import app.models.Image;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("ACCOUNTS.MOCK")
public class AccountRepositoryMock implements Repository<Account> {
    private List<Account> accounts = new ArrayList<>();
    private final int LISTLENGTH = 7;
    private int personalNuberCreate = 10000;

    public AccountRepositoryMock() {
//        for (int i = 0; i < LISTLENGTH; i++) {
//            accounts.add(Account.createSampleAccount());
//        }

        // Add more images as needed.
        accounts.add(new Account(2130234,
                "IloveApples4%",
                "Pieter van de Broek",
                "pietervdbroek@gmail.com",
                "ImageMaker",
                "Amsterdam",
                Account.LOGGEDIN.FALSE));
        accounts.add(new Account(2165954,
                "MacbookPro3$",
                "Julia Beekman",
                "juliabeekman@gmail.com",
                "admin",
                "Amsterdam",
                Account.LOGGEDIN.FALSE));
        accounts.add(new Account(2121843,
                "GroteBeer9!",
                "Guus de Jong",
                "guusdejong@gmail.com",
                "admin",
                "Apeldoorn",
                Account.LOGGEDIN.FALSE));
        accounts.add(new Account(2130901,
                "IloveBazary4%",
                "Cindy Visser",
                "cindyvisser@mm.nl",
                "ImageMaker",
                "Hoorn",
                Account.LOGGEDIN.FALSE));
    }

    @Override
    public List<Account> findAll() {
        return this.accounts;
    }

    @Override
    public Account findById(int id) {
        return this.accounts.stream().filter(account -> account.getPersonalNumber() == id).findFirst().orElse(null);
    }

    @Override
    public Account save(Account model) {
        if (this.findById(model.getPersonalNumber()) != null) {
            int modelIndex = this.accounts.indexOf(this.findById(model.getPersonalNumber()));
            this.accounts.set(modelIndex, model);
        } else if (model.getPersonalNumber() == 0) {
            model.setPersonalNumber(this.personalNuberCreate);
            this.personalNuberCreate++;
            this.accounts.add(model);
        }
        return model;
    }

    @Override
    public Account deleteById(int id) {
        Account deleteAccount = this.findById(id);

        if (deleteAccount != null) {
            this.accounts.remove(deleteAccount);
        }

        return deleteAccount;
    }
}
