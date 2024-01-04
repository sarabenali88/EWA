export class SessionSbService {
  RESOURCES_URL;
  BROWSER_STORAGE_ITEM_NAME;
  _currentAccount;
  _currentToken;

  constructor (resourcesUrl, browserStorageItemName) {
    this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName
    this.RESOURCES_URL = resourcesUrl
    this.getTokenFromBrowserStorage()
  }

  async asyncSignIn (personalNumber, password) {
    const body = JSON.stringify({ personalNumber: personalNumber, password: password })
    const response = await fetch(this.RESOURCES_URL + '/login',
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: body,
        credentials: 'include'
      })
    if (response.ok) {
      const user = await response.json()
      this.saveTokenIntoBrowserStorage(
        response.headers.get('Authorization'),
        user)
      return user
    } else {
      console.log(response)
      return null
    }
  }

  signOut () {
    this.saveTokenIntoBrowserStorage(null, null)
  }

  isAuthenticated () {
    return this._currentAccount != null
  }

  getTokenFromBrowserStorage () {
    if (this._currentToken != null) return this._currentToken
    this._currentToken = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME)
    const jsonAccount = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME + '_ACC')

    if (this._currentToken == null) {
      // TODO try to find the token+account in local storage and replicate to this session if found
    }
    if (jsonAccount != null) {
      this._currentAccount = JSON.parse(jsonAccount)
    }
    console.log('SessionService recovered token: ', this._currentToken)
    console.log('Current Account:', this._currentAccount)
    return this._currentToken
  }

  saveTokenIntoBrowserStorage (token, account) {
    this._currentToken = token
    this._currentAccount = account
    // allow for different user sessions from the same computer
    // sessionStorage keeps different items per browser tab
    // localStorage keeps a single item per browser vendor
    // both isolate the items per server domain of the page (including port number?)
    if (token == null) {
      this._currentAccount = null
      window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME)
      window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME + '_ACC')
      // TODO remove the token+account from local storage, if localStorage and session storage are equal
    } else {
      console.log('New token for ' + account.name + ': ' + token)
      window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token)
      window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME + '_ACC', JSON.stringify(account))
      // TODO also save the new token+account in localStorage
    }
  }

  getCurrentToken () {
    return this._currentToken
  }
}
