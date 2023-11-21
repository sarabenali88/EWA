/**
 * This is the model for an account that will be created in the back end.
 *
 * @author Jasper Fernhout
 */
package app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;

public class Account {
    @JsonView(ViewClasses.Summary.class)
    private int personalNumber;
    private String password;
    @JsonView(ViewClasses.Summary.class)
    private String name;
    @JsonView(ViewClasses.Summary.class)
    private String email;
    private String role;
    private String location;
    private ArrayList<Image> imagesOnGoing;
    private ArrayList<Image> imagesDone;
    private boolean loggedIn;

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

    public Account(int personalNumber, String password, String name, String email, String role, String location, ArrayList<Image> imagesOnGoing, ArrayList<Image> imagesDone, LOGGEDIN loggedIn) {
        this.personalNumber = personalNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.location = location;
        this.imagesOnGoing = imagesOnGoing;
        this.imagesDone = imagesDone;
        this.loggedIn = loggedIn.getValue();
    }

    /**
     * This is a function that will create a sample account for test functionalities.
     *
     * @return gives back a sample account.
     * @author Jasper Fernhout
     */
    public static Account createSampleAccount() {
        ArrayList<Image> onGoingImages = new ArrayList<>();
        ArrayList<Image> doneImages = new ArrayList<>();

        // Create and add some Image objects to the onGoingImages list
        onGoingImages.add(Image.createSampleImage());
        onGoingImages.add(Image.createSampleImage());
        // Add more images as needed.

        // Create and add some Image objects to the doneImages list
        doneImages.add(Image.createSampleImage());
        doneImages.add(Image.createSampleImage());
        // Add more images as needed.

        return new Account(
                123142,
                "Wachtwoord",
                "name",
                "email",
                "role",
                "Amsterdam",
                onGoingImages,
                doneImages,
                LOGGEDIN.values()[0]
        );
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

    public ArrayList<Image> getImagesOnGoing() {
        return imagesOnGoing;
    }

    public ArrayList<Image> getImagesDone() {
        return imagesDone;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}

