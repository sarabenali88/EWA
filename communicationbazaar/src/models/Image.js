import {Laptop} from "@/models/laptop.js";

export class Image{
    laptop;
    version;
    store;
    upDateDate;
    status;
    release;
    problem;
    createdWeek;
    createdYear;
    name;
    comment;
    imageMaker;

    static Status = {
        TODO: "Te doen",
        ONGOING: "Mee bezig",
        FINISHED: "Afgerond"
    }

    static Release = {
        NEW: "Nieuw",
        UPDATE: "Update"
    }

    static Problem = {
        YES: "Ja",
        NO: "Nee",
        REPORTED: "Ja, gemeld"
    }

    constructor(laptop, version, store, upDateDate, status, release, problem, week, year, name, comment, imageMaker) {
        this.laptop = new Laptop();
        this.laptop.ean = laptop;
        this.version = version;
        this.store = store;
        this.upDateDate = upDateDate;
        this.status = status;
        this.release = release;
        this.problem = problem;
        this.createdWeek = week;
        this.createdYear = year;
        this.name = name;
        this.comment = comment;
        this.imageMaker = imageMaker;
    }

    static copyConstructor (image, id) {
        if (image == null) return null;

        return Object.assign(new Image(id), image);
    }
}