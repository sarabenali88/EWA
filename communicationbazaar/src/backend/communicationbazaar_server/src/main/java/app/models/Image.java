package app.models;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;

public class Image {
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

    public Image(Laptop laptop, String version, String store, String upDateDate, Status status, Release release, Problem problem, int week, int year, String name, String comment, String imageMaker) {
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

    public static Image createSampleImage() {
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
}
