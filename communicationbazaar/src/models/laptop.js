export class Laptop {
    articleNumber;
    ean;
    brand;
    description;
    processor;
    ram;
    storage;
    gpu;
    sizeInch;
    sizeCm;


    constructor(articleNumber, ean, brand, description, processor, ram, storage, gpu, sizeInch, sizeCm) {
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
    }
}