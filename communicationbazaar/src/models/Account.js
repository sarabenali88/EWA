export class Account {
    personalNumber;
    password;
    name;
    email;
    role;
    location;
    imagesOnGoing;
    imagesDone;
    loggedIn;

    constructor(personalNumber, password, name, email, role, location, imagesOnGoing, imagesDone, loggedIn) {
        this.personalNumber = personalNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.location = location;
        this.imagesOnGoing = imagesOnGoing;
        this.imagesDone = imagesDone;
        this.loggedIn = loggedIn;
    }

    static copyConstructor (account, id) {
        if (account == null) return null;

        return Object.assign(new Account(id), account);
    }
}