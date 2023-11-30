/**
 * This is a class that will fetch the data for the accounts from the back end in the front end.
 *
 * @author Jasper Fernhout
 */
import {Account} from '@/models/Account'

export class AccountsAdaptor {
    resourcesUrl;

    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    async fetchJson(url, options = null) {
        try {
            const response = await fetch(url, options);
            if (response.ok) {
                return await response.json();
            } else {
                // TODO hier moet nog een; response error die in de json body zit
                return []
            }

        } catch (error) {
            return []
        }
    }

    async asyncFindAll() {
        const accounts = await this.fetchJson(this.resourcesUrl);
        return accounts?.map(account => Account.copyConstructor(account));
    }

    async asyncFindById(personalNumber) {
        const accountData = await this.fetchJson(this.resourcesUrl + '/' + personalNumber);
        return Account.copyConstructor(accountData);
    }

    async asyncSave(account) {
        if (account.personalNumber === 0) {
            return this.fetchJson(this.resourcesUrl,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        personalNumber: account.personalNumber,
                        password: account.password,
                        name: account.name,
                        email: account.email,
                        role: account.role,
                        location: account.location,
                        imagesOnGoing: account.imagesOnGoing,
                        imagesDone: account.imagesDone,
                        loggedIn: account.loggedIn,
                    })
                });
        } else {
            return this.fetchJson(this.resourcesUrl + '/' + account.personalNumber,
                {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(
                        account
                    )
                });
        }
    }

    async asyncDeleteById(personalNumber) {
        return this.fetchJson(this.resourcesUrl + '/' + personalNumber,
            {
                method: 'DELETE'
            });
    }

    async verifyPassword(personalNumber, password) {
        return await this.fetchJson(this.resourcesUrl + '/verifyPassword/' + personalNumber + "/" + password);
    }
}
