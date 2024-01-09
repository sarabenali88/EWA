/**
 * This is the class for the error message when a status precondition failed in the back end.
 *
 * @author Jasper Fernhout
 */
package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PreConditionFailedException extends RuntimeException {

    /**
     * This will send an error message.
     *
     * @param message the message that will be displayed.
     * @author Jasper Fernhout
     */
    public PreConditionFailedException(String message) {
        super(message);
    }
}

