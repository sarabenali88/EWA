/**
 * This is the model for an account that will be created in the back end.
 *
 * @author Jasper Fernhout
 */
package app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    @SequenceGenerator(name="Account_ids", initialValue=10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Account_ids")
    @JsonView(ViewClasses.Summary.class)
    private long personalNumber;
    private String password;
    private String hashedPassword;
    @JsonView(ViewClasses.Summary.class)
    private String name;
    @JsonView(ViewClasses.Summary.class)
    private String email;
    private String role;
    private String location;
    private boolean loggedIn;

    public Account() {

    }

    public enum LOGGEDIN {
        TRUE(true),
        FALSE(false);

        private final Boolean value;

        LOGGEDIN(Boolean value) {
            this.value = value;
        }

        @JsonCreator
        public static LOGGEDIN fromBoolean(Boolean value) {
            return value ? TRUE : FALSE;
        }

        @JsonValue
        public boolean getValue() {
            return value;
        }
    }

    public Account(long personalNumber, String password, String name, String email, String role, String location, LOGGEDIN loggedIn) {
        this.personalNumber = personalNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.location = location;
        this.loggedIn = loggedIn.getValue();
    }

    /**
     * Hash the given password in combination with the account identification (id)
     * and some extra characters for extra security.
     * Different accounts with the same password will deliver different hashes
     * @param password
     */
    public String hashPassword(String password) {
        return SecureHasher.secureHash("Id-" + this.getPersonalNumber() + ":" + password);
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
        this.setHashedPassword(this.hashPassword(newPassword));
    }

    /**
     * Verify whether the hash of the given password
     * matches the correct hash of the account's true password
     * (without actually knowing the correct password: only its hash has been kept in store)
     * @param password
     * @return
     */
    public boolean verifyPassword(String password) {
        return this.hashPassword(password).equals(this.hashPassword(this.getPassword()));
    }

    /**
     * This is a function that will create a sample account for test functionalities.
     *
     * @return gives back a sample account.
     * @author Jasper Fernhout
     */
    public static Account createSampleAccount() {

        return new Account(
                123142,
                "Wachtwoord",
                "name",
                "email",
                "role",
                "Amsterdam",
                LOGGEDIN.values()[0]
        );
    }

    public long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getLocation() {
        return location;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}

