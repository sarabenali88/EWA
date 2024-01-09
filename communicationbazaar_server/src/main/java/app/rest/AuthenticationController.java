/**
 * AuthenticationController class that will control the login token functionalities.
 *
 * @author Jasper Fernhout
 */
package app.rest;

import app.APIConfig;
import app.exceptions.NotAcceptableException;
import app.jwt.JWToken;
import app.models.Account;
import app.repositories.AccountRepositoryJPA;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    APIConfig apiConfig;

    @Autowired
    AccountRepositoryJPA accountRepositoryJPA;

    /**
     * Handles the authentication process.
     *
     * @param temp - JSON object containing user credentials (personalNumber, password).
     * @return ResponseEntity<Account> - Response entity with the authenticated account and a generated token.
     * @throws NotAcceptableException - If authentication fails for the provided credentials.
     * @author Jasper Fernhout
     */
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<Account> authenticate(@RequestBody ObjectNode temp) throws NotAcceptableException {
        if (accountRepositoryJPA.findByQuery("Account_find_by_personalNumber_and_password", temp.get("personalNumber").asInt(), temp.get("password").asText()).isEmpty()) {
            throw new NotAcceptableException("Cannot authenticate user by personalNumber=" + temp.get("personalNumber").asInt());
        }
        List<Account> accounts = accountRepositoryJPA.findByQuery("Account_find_by_personalNumber_and_password", temp.get("personalNumber").asText(), temp.get("password").asText());
        Account account = !accounts.isEmpty() ? accounts.get(0) : null;

        assert account != null;
        account.setLoggedIn(true);
        accountRepositoryJPA.save(account);

        // Issue a token for the account, valid for some time
        JWToken jwToken = new JWToken(account.getName(), account.getPersonalNumber(), account.getRole());
        String tokenString = jwToken.encode(this.apiConfig.getIssuer(),
                this.apiConfig.getPassphrase(),
                this.apiConfig.getTokenDurationOfValidity());


        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(account);
    }

}
