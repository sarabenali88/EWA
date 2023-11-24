/**
 * This is a controller for the accounts that will catch the calls from the front end.
 *
 * @author Jasper Fernhout
 */
package app.rest;

import app.exceptions.PreConditionFailedException;
import app.exceptions.ResourceNotFoundException;
import app.models.Account;
import app.models.ViewClasses;
import app.repositories.AccountRepositoryJPA;
import app.repositories.Repository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    Repository<Account> accountList;

    @Autowired
    AccountRepositoryJPA accountRepositoryJPA;

    @GetMapping(path = "", produces = "application/json")
    public List<Account> getAllAccounts() {
        return this.accountList.findAll();
    }

    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "/summary", produces = "application/json")
    public List<Account> getSummaryAccounts() {
        return this.accountList.findAll();
    }

    @GetMapping(path = "{personalNumber}", produces = "application/json")
    public ResponseEntity<Account> getOneAccount(@PathVariable() int personalNumber) throws ResourceNotFoundException {
        Account account = this.accountList.findById(personalNumber);

        if (account == null) {
            throw new ResourceNotFoundException("Cannot provide account with personalNumber= " + personalNumber);
        }

        return ResponseEntity.ok().body(account);
    }

    @DeleteMapping(path = "{personalNumber}", produces = "application/json")
    public Account deleteOneAccount(@PathVariable() int personalNumber) throws ResourceNotFoundException {
        Account account = this.accountList.deleteById(personalNumber);

        if (account == null) {
            throw new ResourceNotFoundException("Cannot delete a cabin with id= " + personalNumber);
        }

        return account;
    }

    @PostMapping(path = "", produces = "application/json")
    public Account addOneAccount(@RequestBody Account account) throws Exception {

        if (this.accountList.findById(account.getPersonalNumber()) != null) {
            throw new Exception("Account already exist with personalNumber= " + account.getPersonalNumber());
        }

        account = this.accountList.save(account);

        return account;
    }


    @PutMapping(path = "{personalNumber}", produces = "application/json")
    public Account updateOneAccount(@PathVariable() int personalNumber, @RequestBody Account targetAccount) throws PreConditionFailedException {

        if (personalNumber != targetAccount.getPersonalNumber()) {
            throw new PreConditionFailedException("Account personalNumber=" + targetAccount.getPersonalNumber() + " does not match path parameter=" + personalNumber);
        } else {
            targetAccount = this.accountList.save(targetAccount);
        }

        return targetAccount;
    }

    @GetMapping(path = "/verifyPassword/{personalNumber}/{password}", produces = "application/json")
    public boolean verifyPassword(@PathVariable() int personalNumber, @PathVariable() String password) {
        Account account = this.accountList.findById(personalNumber);

        if (account == null) {
            return false;
        } else {
            return account.verifyPassword(password);
        }
    }
}