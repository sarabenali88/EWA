/**
 * This is the model for an account that will be created in the back end.
 *
 * @author Jasper Fernhout
 */
package app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;


@Entity
public class Account {

    @Id
    @SequenceGenerator(name="Account_ids", initialValue=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Account_ids")
    private int id;
    @JsonView(ViewClasses.Summary.class)
    private int personalNumber;
    private String password;
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

    public Account(int personalNumber, String password, String name, String email, String role, String location, LOGGEDIN loggedIn) {
        this.personalNumber = personalNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.location = location;
        this.loggedIn = loggedIn.getValue();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getPassword() {
        return password;
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

