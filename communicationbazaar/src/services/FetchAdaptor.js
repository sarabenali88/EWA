// see https://www.npmjs.com/package/fetch-intercept
import fetchIntercept from 'fetch-intercept'
import navBarComponent from "@/components/NavBarComponent";

export class FetchAdaptor {
    static theInstance; // the singleton instance that has been registered
    sessionService; // the sessionService which tracks the authorisation
    unregister; // callback function to unregister this instance
    router;

    constructor (sessionService, router) {
      FetchAdaptor.theInstance = this
      this.router = router
      this.sessionService = sessionService
      // fetchIntercept does not register the object closure, only the methods as functions
      this.unregister = fetchIntercept.register(this)
      console.log('FetchAdaptor has been registered.')
    }

    request (url, options) {
      const token = FetchAdaptor.theInstance.sessionService.getCurrentToken()

      if (token == null) {
        return [url, options]
      } else if (options == null) {
        return [url, { headers: { Authorization: token } }]
      } else {
        const newOptions = { ...options }
        // TODO combine existing headers with new Authorization header
        newOptions.headers = { Authorization: token }
        // console.log("FetchAdaptor request: ", url, newOptions);
        return [url, newOptions]
      }
    }

    response (response) {
    // console.log("FetchInterceptor response: ", response);
      FetchAdaptor.theInstance.tryRecoverNewJWToken(response)
      if (response.status >= 400 && response.status < 600) {
        FetchAdaptor.theInstance.handleErrorInResponse(response)
      }
      return response
    }

    requestError (error) {
    // Called when an error occured during another 'request' interceptor call
      console.log('FetchInterceptor requestError: ', error)
      this.router.push(navBarComponent.data().signOutRoute)
      return Promise.reject(error)
    }

    responseError (error) {
    // Handle a fetch error
      console.log('FetchInterceptor responseError: ', error)
      this.router.push(navBarComponent.data().signInRoute)
      return Promise.reject(error)
    }

    async handleErrorInResponse (response) {
      if (response.status === 401) {
        this.router.push(navBarComponent.data().signOutRoute)
      } else if (response.status !== 406) {
      // 406='Not Acceptable' error is used for logon failure
      // TODO handle any other error
      }
    }

    // eslint-disable-next-line no-unused-vars
    tryRecoverNewJWToken (response) {
    // TODO check the response on availability of a JWT
    //  and request the session service to save that
    }
}
