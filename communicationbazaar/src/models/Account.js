export class Account {
    personalNumber;
    password;
    name;
    email;
    role;
    imagesOnGoing;
    imagesDone;
    loggedIn;

    static LOGGEDIN = {
        TRUE: true,
        FALSE: false
    }

    constructor(personalNumber, password, name, email, role, imagesOnGoing, imagesDone, loggedIn) {
        this.personalNumber = personalNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.imagesOnGoing = imagesOnGoing;
        this.imagesDone = imagesDone;
        this.loggedIn = loggedIn;
    }

    static copyConstructor (account, id) {
        if (account == null) return null;

        return Object.assign(new Account(id), account);
    }
}