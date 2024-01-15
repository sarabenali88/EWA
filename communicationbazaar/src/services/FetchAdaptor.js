/**
 * This class will regulate all the incomming requests and outgoing resonses.
 *
 * @author Jasper Fernhout
 */
import fetchIntercept from 'fetch-intercept'
import navBarComponent from "@/components/NavBarComponent";

export class FetchAdaptor {
    static theInstance;
    sessionService;
    unregister;
    router;

    /**
     * Creates an instance of FetchAdaptor.
     *
     * @param {object} sessionService - The service managing user sessions.
     * @param {object} router - The router instance for navigation.
     * @author Jasper Fernhout
     */
    constructor(sessionService, router) {
        FetchAdaptor.theInstance = this
        this.router = router
        this.sessionService = sessionService
        this.unregister = fetchIntercept.register(this)
    }

    /**
     * Modifies the request by adding an authorization token if available.
     *
     * @param {string} url - The URL for the request.
     * @param {object} options - The options for the request.
     * @returns {[string, object]} - Modified URL and options.
     * @author Jasper Fernhout
     */
    request(url, options) {
        const token = FetchAdaptor.theInstance.sessionService.getCurrentToken()

        if (token == null) {
            return [url, options];
        } else if (options == null) {
            return [url, {headers: {Authorization: token}}];
        } else {
            const newOptions = {...options};
            newOptions.headers = {...newOptions.headers, Authorization: token};
            return [url, newOptions];
        }
    }

    /**
     * Handles the response, checks for errors, and returns the response.
     *
     * @param {Response} response - The response received from the request.
     * @returns {Response} - The response object.
     * @author Jasper Fernhout
     */
    response(response) {
        FetchAdaptor.theInstance.tryRecoverNewJWToken(response)
        if (response.status >= 400 && response.status < 600) {
            FetchAdaptor.theInstance.handleErrorInResponse(response)
        }
        return response
    }

    /**
     * Handles the error response for a request error.
     *
     * @param error - The incomming error.
     * @returns {Promise<never>}
     * @author Jasper Fernhout
     */
    requestError(error) {
        this.router.push(navBarComponent.data().signOutRoute)
        return Promise.reject(error)
    }

    /**
     * Handles the error response for a response error.
     *
     * @param error - The incomming error.
     * @returns {Promise<never>}
     * @author Jasper Fernhout
     */
    responseError(error) {
        this.router.push(navBarComponent.data().signInRoute)
        return Promise.reject(error)
    }

    /**
     * Handles the error in response.
     *
     * @param response - The incomming error.
     * @returns {Promise<never>}
     * @author Jasper Fernhout
     */
    async handleErrorInResponse(response) {
        if (response.status === 401) {
            this.router.push(navBarComponent.data().signOutRoute)
        } else if (response.status !== 406) {
            this.router.push(navBarComponent.data().signOutRoute)
        }
    }

    // eslint-disable-next-line no-unused-vars
    tryRecoverNewJWToken(response) {
        // TODO check the response on availability of a JWT
        //  and request the session service to save that
    }
}
