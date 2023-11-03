import { Account } from '@/models/Account'

export class AccountsAdaptor {
    resourcesUrl;

    constructor (resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    async fetchJson (url, options = null) {
        const response = await fetch(url, options);
        if (response.ok) {
            return await response.json();
        } else {
            // TODO hier moet nog een; response error die in de json body zit
            return null;
        }
    }

    async asyncFindAll () {
        const accounts = await this.fetchJson(this.resourcesUrl);
        return accounts?.map(account => Account.copyConstructor(account));
    }

    async asyncFindById (personalNumber) {
        const accountData = await this.fetchJson(this.resourcesUrl + '/' + personalNumber);
        return Account.copyConstructor(accountData);
    }

    async asyncSave (account) {
        return this.fetchJson(this.resourcesUrl + '/' + account.personalNumber,
            {
                method: 'POST'
            });
    }

    async asyncDeleteById (personalNumber) {
        return this.fetchJson(this.resourcesUrl + '/' + personalNumber,
            {
                method: 'DELETE'
            });
    }
}
