/**
 * This is a class that will fetch the data for the accounts from the back end in the front end.
 *
 * @author Jasper Fernhout
 */
import {Account} from '@/models/Account'

export class AccountsAdaptor {
    resourcesUrl;

    /**
     * Constructs an AccountsAdaptor instance with a specified resources URL.
     *
     * @param {string} resourcesUrl - The URL for accessing account-related resources.
     * @author Jasper Fernhout
     */
    constructor(resourcesUrl) {
        this.resourcesUrl = process.env.VUE_APP_API_URL + resourcesUrl;
    }

    async fetchJson(url, options = null) {
        try {
            const response = await fetch(url, options);
            if (response.ok) {
                return await response.json();
            } else {
                return []
            }
        } catch (error) {
            return []
        }
    }

    /**
     * Retrieves all accounts asynchronously.
     *
     * @returns {Promise<Account[]>} - A promise resolving to an array of Account instances.
     * @author Jasper Fernhout
     */
    async asyncFindAll() {
        const accounts = await this.fetchJson(this.resourcesUrl);
        return accounts?.map(account => Account.copyConstructor(account));
    }

    /**
     * Retrieves an account by its personal number asynchronously.
     *
     * @param {number} personalNumber - The personal number of the account.
     * @returns {Promise<Account>} - A promise resolving to an Account instance.
     * @author Jasper Fernhout
     */
    async asyncFindById(personalNumber) {
        const accountData = await this.fetchJson(this.resourcesUrl + '/' + personalNumber);
        return Account.copyConstructor(accountData);
    }

    /**
     * Saves a newly created account or updates an excisting one.
     *
     * @param account - The account that needs to be added or updated.
     * @returns {Promise<any|[]|undefined>}
     * @author Jasper Fernhout
     */
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

    /**
     * Deletes an account instance based on the personalNumber.
     *
     * @param personalNumber - The personalNumber of the account that needs to be deleted.
     * @returns {Promise<any|[]|undefined>}
     * @author Jasper Fernhout
     */
    async asyncDeleteById(personalNumber) {
        return this.fetchJson(this.resourcesUrl + '/' + personalNumber,
            {
                method: 'DELETE'
            });
    }

    async asyncGetImagesFromAccount(personalNumber) {
        return await this.fetchJson(this.resourcesUrl + '/' + personalNumber + "/images");
    }
}
