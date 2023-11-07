package app.models;

import com.fasterxml.jackson.annotation.JsonView;

public class Laptop {
    @JsonView(ViewClasses.Summary.class)
    private int articleNumber;
    @JsonView(ViewClasses.Summary.class)
    private int ean;
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

    public Laptop(int articleNumber, int ean, String brand, String description, String processor, String ram, String storage, String gpu, String sizeInch, String sizeCm, String os, int prize) {
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

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getEan() {
        return ean;
    }

    public void setEan(int ean) {
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
}

