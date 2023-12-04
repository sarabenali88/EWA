/**
 * This is an error message for when a resource is not found in the back end.
 *
 * @author Jasper Fernhout
 */
package app.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * This will send an error message.
     *
     * @author Jasper Fernhout
     * @param message the message that will be displayed.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

