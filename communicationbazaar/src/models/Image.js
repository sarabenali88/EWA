export class Image{
    id;
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

    constructor(id, laptop, version, store, upDateDate, status, release, problem, createdWeek, createdYear, name, comment, imageMaker) {
        this.id = id;
        this.laptop = laptop;
        this.version = version;
        this.store = store;
        this.upDateDate = upDateDate;
        this.status = status;
        this.release = release;
        this.problem = problem;
        this.createdWeek = createdWeek;
        this.createdYear = createdYear;
        this.name = name;
        this.comment = comment;
        this.imageMaker = imageMaker;
    }

    static createSampleImage(id) {
        return new Image(
            id,
            'Sample Laptop',
            'Sample Version',
            'Sample Store',
            new Date(), // Sample date, you can adjust this as needed
            Image.Status.TODO, // Using the predefined status 'Te doen'
            Image.Release.NEW, // Using the predefined release 'Nieuw'
            Image.Problem.YES, // Using the predefined problem 'Ja'
            1, // Sample createdWeek value
            2023, // Sample createdYear value
            'Sample Name',
            'Sample Comment',
            'Sample Image Maker'
        );
    }

    static copyConstructor (image, id) {
        if (image == null) return null;
        return Object.assign(new Image(id), image);
    }
}