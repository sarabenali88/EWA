import {Laptop} from "@/models/laptop.js";

export class LaptopImage{
    laptop;
    version;
    store;
    upDateDate;
    status;
    release;
    problem;
    week;
    year;
    name;
    comment;

    static Status = {
        TODO: "Te doen",
        ONGOING: "Mee bezig",
        FINISHED: "Afgerond"
    }

    static Release = {
        NEW: "Nieuw",
        UPDATE: "Update",
    }


    constructor(laptop, version, store, upDateDate, status, release, problem, week, year, name, comment) {
        this.laptop = new Laptop();
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
    }
}