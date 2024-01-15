/**
 * This is the class for the error message when a user is not provided with a token.
 *
 * @author Jasper Fernhout
 */
package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException {

    /**
     * This method will give back the error message.
     *
     * @param message - The error message.
     * @author Jasper Fernhout
     */
    public NotAcceptableException(String message) {
        super(message);
    }
}
