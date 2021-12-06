/**
 * Class that Creates a Custom Exception for when a
 * Username being entered already exists
 * @author ryanwake
 */
public class UsernameAlreadyExistsException extends IllegalArgumentException {

    public UsernameAlreadyExistsException(String message) {
        super (message);
    }
}
