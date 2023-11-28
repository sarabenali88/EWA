/**
 * This is the model for an Image that will be created in the back end.
 *
 * @author Jasper Fernhout
 */
package app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
@Entity
public class Image {
    @Id
    @SequenceGenerator(name="Image_ids", initialValue=1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Image_ids")
    private int id;
    @ManyToOne
    @JsonManagedReference
    private Laptop laptop;
    @JsonView(ViewClasses.Summary.class)
    private String version;
    private String store;
    @JsonView(ViewClasses.Summary.class)
    private String upDateDate;
    @JsonView(ViewClasses.Summary.class)
    private Status status;
    private Release release;
    private Problem problem;
    private int week;
    private int year;
    private String name;
    private String comment;
    private String imageMaker;

    public Image() {

    }

    public enum Status {
        TODO("Te doen"),
        ONGOING("Mee bezig"),
        FINISHED("Afgerond");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Release {
        NEW("Nieuw"),
        UPDATE("Update");

        private final String value;

        Release(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Problem {
        YES("Ja"),
        NO("Nee"),
        REPORTED("Ja, gemeld");

        private final String value;

        Problem(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Image(int id, Laptop laptop, String version, String store, String upDateDate, Status status, Release release, Problem problem, int week, int year, String name, String comment, String imageMaker) {
        this.id = id;
        this.laptop = laptop;
        this.version = version;
        this.store = store;
        this.upDateDate = upDateDate;
        this.status = status;
        this.release = release;
        this.problem = problem;
        this.week = week;
        this.year = year;
        this.name = name;
        this.comment = comment;
        this.imageMaker = imageMaker;
    }

    /**
     * This is a function that will create a sample image for test functionalities.
     *
     * @return gives back a sample account.
     * @author Jasper Fernhout
     */
    public static Image createSampleImage(int id) {
        int randomNumber3 = (int) Math.floor(Math.random() * 3);
        int randomNumber2 = (int) Math.floor(Math.random() * 2);
        int randomNumber231 = (int) Math.floor(Math.random() * 231);
        int randomNumber52 = (int) Math.floor(Math.random() * 52);
        int randomNumber12 = (int) Math.floor(Math.random() * 12);
        int randomNumber30 = (int) Math.floor(Math.random() * 30);
        int randomNumber1000 = (int) Math.floor(Math.random() * 1000);

        ArrayList<String> store = new ArrayList<>();
        store.add("Hoorn");
        store.add("Apeldoorn");
        store.add("Amsterdam");

        ArrayList<String> imageMaker = new ArrayList<>();
        imageMaker.add("Pieterman");
        imageMaker.add("Lea");
        imageMaker.add("Willem");

        return new Image(
                id,
                Laptop.createSampleLaptop(),
                "MM V" + randomNumber231,
                store.get(randomNumber3),
                randomNumber30 + "-" + randomNumber12 + "-2023",
                Image.Status.values()[randomNumber3],
                Image.Release.values()[randomNumber2],
                Image.Problem.values()[randomNumber3],
                randomNumber52,
                2023,
                "ImageNaam" + randomNumber1000,
                "",
                imageMaker.get(randomNumber3)
        );
    }

    public int getId() {
        return id;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public String getVersion() {
        return version;
    }

    public String getStore() {
        return store;
    }

    public String getUpDateDate() {
        return upDateDate;
    }

    public Status getStatus() {
        return status;
    }

    public Release getRelease() {
        return release;
    }

    public Problem getProblem() {
        return problem;
    }

    public int getWeek() {
        return week;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getImageMaker() {
        return imageMaker;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setUpDateDate(String upDateDate) {
        this.upDateDate = upDateDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setImageMaker(String imageMaker) {
        this.imageMaker = imageMaker;
    }
}
