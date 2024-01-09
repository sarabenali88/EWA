/**
 * This is a controller for the accounts that will catch the calls from the front end.
 *
 * @author Jasper Fernhout
 */
package app.rest;

import app.exceptions.PreConditionFailedException;
import app.exceptions.ResourceNotFoundException;
import app.models.Account;
import app.models.Image;
import app.models.ViewClasses;
import app.repositories.AccountRepositoryJPA;
import app.repositories.Repository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * This will search for all available acocunts.
     *
     * @return - Gives back a list of all accounts.
     * @author Jasper Fernhout
     */
    @GetMapping(path = "", produces = "application/json")
    public List<Account> getAllAccounts() {
        return this.accountList.findAll();
    }

    /**
     * Will look for a summary version of all the accounts.
     *
     * @return - Gives back a summary list of those accounts.
     * @author Jasper Fernhout
     */
    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "/summary", produces = "application/json")
    public List<Account> getSummaryAccounts() {
        return this.accountList.findAll();
    }

    /**
     * Will look for an account with the giving personalNumber.
     *
     * @param personalNumber - The personalNumber of the target account.
     * @return - Gives back the target account.
     * @throws ResourceNotFoundException - The error if the account is not available.
     * @author Jasper Fernhout
     */
    @GetMapping(path = "{personalNumber}", produces = "application/json")
    public ResponseEntity<Account> getOneAccount(@PathVariable() long personalNumber) throws ResourceNotFoundException {
        Account account = this.accountList.findById(personalNumber);

        if (account == null) {
            throw new ResourceNotFoundException("Cannot provide account with personalNumber= " + personalNumber);
        }

        return ResponseEntity.ok().body(account);
    }

    /**
     * Will delete an account with the giving personalNumber.
     *
     * @param personalNumber - The personalNumber of the target account.
     * @return - Gives back the target account.
     * @throws ResourceNotFoundException - The error if the account is not available.
     * @author Jasper Fernhout
     */
    @DeleteMapping(path = "{personalNumber}", produces = "application/json")
    public ResponseEntity<Account> deleteOneAccount(@PathVariable() long personalNumber) throws ResourceNotFoundException {
        Account account = this.accountList.deleteById(personalNumber);

        if (account == null) {
            throw new ResourceNotFoundException("Cannot delete a cabin with id= " + personalNumber);
        }

        return ResponseEntity.ok().body(account);
    }

    /**
     * Will add an account to the existing list of accounts.
     *
     * @param account - The account that needs to be added.
     * @return - Gives back the added account.
     * @throws Exception - Will throw this exception if anything goes wrong.
     * @author Jasper Fernhout
     */
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Account> addOneAccount(@RequestBody Account account) throws Exception {

        if (this.accountList.findById(account.getPersonalNumber()) != null) {
            throw new Exception("Account already exist with personalNumber= " + account.getPersonalNumber());
        }

        account = this.accountList.save(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    /**
     * Updates an existing account with new details.
     *
     * @param personalNumber - The personalNumber of the account that needs to be updated.
     * @param targetAccount  - The account with the new details.
     * @return - Gives back the account that has been updated.
     * @throws PreConditionFailedException - Throws this exception if anything goes wrong.
     * @author Jasper Fernhout
     */
    @PutMapping(path = "{personalNumber}", produces = "application/json")
    public ResponseEntity<Account> updateOneAccount(@PathVariable() long personalNumber,
                                                    @RequestBody Account targetAccount) throws PreConditionFailedException {

        if (personalNumber != targetAccount.getPersonalNumber()) {
            throw new PreConditionFailedException("Account personalNumber=" + targetAccount.getPersonalNumber() + " does not match path parameter=" + personalNumber);
        } else {
            targetAccount = this.accountList.save(targetAccount);
        }

        return ResponseEntity.ok().body(targetAccount);
    }

    @GetMapping(path = "/verifyPassword/{personalNumber}/{password}", produces = "application/json")
    public boolean verifyPassword(@PathVariable() long personalNumber, @PathVariable() String password) {
        Account account = this.accountList.findById(personalNumber);

        if (account == null) {
            return false;
        } else {
            return account.verifyPassword(password);
        }
    }

    @GetMapping(path = "/{personalNumber}/images")
    public List<Image> getImagesFromAccount(@PathVariable long personalNumber) {

        return this.accountRepositoryJPA.getImagesFromAccount(personalNumber);

    }
}