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
      return null
    }
  }

  signOut () {
    this.saveTokenIntoBrowserStorage(null, null)
  }

  isAuthenticated () {
    return this._currentAccount != null
  }

  isAuthenticatedAndAdmin () {
    return this._currentAccount != null && this._currentAccount.role === 'admin'
  }

  isAuthenticatedAndImageMaker () {
    return this._currentAccount != null && this._currentAccount.role === 'ImageMaker'
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
    return this._currentToken
  }

  saveTokenIntoBrowserStorage (token, account) {
    this._currentToken = token
    this._currentAccount = account
    if (token == null) {
      this._currentAccount = null
      window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME)
      window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME + '_ACC')
    } else {
      window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token)
      window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME + '_ACC', JSON.stringify(account))
    }
  }

  getCurrentToken () {
    return this._currentToken
  }
}
