/**
 * This is a model for an account that will function in the front end.
 *
 * @author Jasper Fernhout
 */
export class Account {
    personalNumber;
    password;
    name;
    email;
    role;
    location;
    loggedIn;

    constructor(personalNumber, password, name, email, role, location, loggedIn) {
        this.personalNumber = personalNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.location = location;
        this.loggedIn = loggedIn;
    }

    static copyConstructor (account, id) {
        if (account == null) return null;

        return Object.assign(new Account(id), account);
    }

    static CreateSampleAccount(id) {
        // Generate a sample account
        return new Account(
            id,
            'samplepassword',
            'Sample User',
            'sample@example.com',
            'user',
            'Sample Location',
            false // Assuming initially not logged in
        );
    }

}