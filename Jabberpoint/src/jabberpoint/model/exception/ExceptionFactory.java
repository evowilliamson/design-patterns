package jabberpoint.model.exception;

/**
 * This class encapsulates the creation of Exceptions
 */
public class ExceptionFactory {

    /**
     * Factory method that creates a new {@link NotImplementedException}
     * @param message the message
     * @return the NotImplementedException object
     */
    public static RuntimeException createNotImplementedException(String message) {

        return new NotImplementedException(message);

    }

    /**
     * Method that creates a new {@link ConfigurationException}
     * @param message
     * @return
     */
    public static RuntimeException createConfigurationException(String message) {

        return new ConfigurationException(message);

    }

}
