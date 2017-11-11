package jabberpoint.model.exception;

/**
 * Representing a configuration exception situation
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Default constructor
     */
    public ConfigurationException() {

        super();
        System.exit(1);

    }

    /** Constructor that takes a messages
     *
     * @param message the message that should be included in the stacktrace
     */
    public ConfigurationException(String message) {
        super(message);
        System.exit(1);
    }

}
