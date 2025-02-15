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

import java.util.ArrayList;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = "Account_find_by_personalNumber_and_password",
                query = "SELECT a FROM Account a WHERE a.personalNumber=?1 AND a.password=?2")
})

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    @SequenceGenerator(name = "Account_ids", initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Account_ids")
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
    @OneToMany(mappedBy = "imageMaker")
    @JsonIgnoreProperties(value = {"imageMaker"}, allowSetters = true)
    private List<Image> images = new ArrayList<>();

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
     *
     * @param password - The password that needs to be hashed.
     * @author Jasper Fernhout
     */
    public String hashPassword(String password) {
        return SecureHasher.secureHash(password);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
        this.setHashedPassword(this.hashPassword(newPassword));
    }

    /**
     * Verify whether the hash of the given password
     * matches the correct hash of the account's true password
     * (without actually knowing the correct password: only its hash has been kept in store)
     *
     * @param password - The password that needs to be checked.
     * @author Jasper Fernhout
     */
    public boolean verifyPassword(String password) {
        return this.hashPassword(password).equals(this.getHashedPassword());
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

    /**
     * Associates the given image with this account, if not yet associated
     *
     * @param image
     * @return whether a new association has been added
     */
    public boolean associateImage(Image image) {
        if (image == null || this.images.contains(image)) {
            //no change required
            return false;
        }

        //update both sides of the association
        this.images.add(image);
        image.associateAccount(this);
        return true;
    }

    /**
     * Dissociates the given image from this account, if associated
     *
     * @param image
     * @return whether an existing association has been removed
     */
    public boolean dissociateImage(Image image) {
        if (image == null || !this.images.contains(image)) {
            //no change required
            return false;
        }

        //update both sides of the association
        this.images.remove(image);
        image.setImageMaker(null);
        return true;
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

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Image> getImages() {
        return images;
    }
}

