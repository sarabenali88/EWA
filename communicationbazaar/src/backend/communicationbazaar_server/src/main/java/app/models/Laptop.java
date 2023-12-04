/**
 * This is a model for a laptop that will be created in the back end.
 *
 * @author Jasper Fernhout
 */
package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Laptop {
    @JsonView(ViewClasses.Summary.class)
    private long articleNumber;
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Laptop_ids")
    @JsonView(ViewClasses.Summary.class)
    private long ean;
    private String brand;
    private String description;
    private String processor;
    private String ram;
    private String storage;
    private String gpu;
    private String sizeInch;
    private String sizeCm;
    private String os;
    private int prize;
    @OneToMany(mappedBy = "laptop")
    @JsonIgnoreProperties(value = {"laptop"}, allowSetters = true)
    private List<Image> images = new ArrayList<>();

    public Laptop(long articleNumber, long ean, String brand, String description, String processor, String ram, String storage, String gpu, String sizeInch, String sizeCm, String os, int prize) {
        this.articleNumber = articleNumber;
        this.ean = ean;
        this.brand = brand;
        this.description = description;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.gpu = gpu;
        this.sizeInch = sizeInch;
        this.sizeCm = sizeCm;
        this.os = os;
        this.prize = prize;
    }

    public Laptop() {

    }

    /**
     * This is a function that will create a sample laptop for test functionalities.
     *
     * @author Jasper Fernhout
     * @return gives back a sample account.
     */
    public static Laptop createSampleLaptop() {
        int randomNumber10000 = (int) Math.floor(Math.random() * 10000);
        int randomNumber1000 = (int) Math.floor(Math.random() * 1000);
        int randomNumber600 = (int) Math.floor(Math.random() * 600);

        return new Laptop(
                randomNumber1000,
                randomNumber10000,
                "brand",
                "description",
                "processor",
                "ram",
                "storage",
                "gpu",
                "sizeInch",
                "sizeCm",
                "os",
                randomNumber600
        );
    }

    public List<Image> getImages() {
        return images;
    }

    public long getArticleNumber() {
        return articleNumber;
    }

    public long getEan() {
        return ean;
    }

    public void setEan(long ean) {
        this.ean = ean;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGpu() {
        return gpu;
    }

    public String getSizeInch() {
        return sizeInch;
    }

    public String getSizeCm() {
        return sizeCm;
    }

    public String getOs() {
        return os;
    }

    public int getPrize() {
        return prize;
    }

    public void setArticleNumber(long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setSizeInch(String sizeInch) {
        this.sizeInch = sizeInch;
    }

    public void setSizeCm(String sizeCm) {
        this.sizeCm = sizeCm;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void addImages(Image image) {
        images.add(image);
    }

    public void removeImages(Image image) {
        images.remove(image);
    }
}

