package jabberpoint.model.exception;

/**
 * Representing a configuration exception situation
 * 
 * @author Ivo Willemsen
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Constructor that takes a messages
     *
     * @param message
     *            the message that should be included in the stacktrace
     */
    public ConfigurationException(String message) {
        super(message);
        System.exit(1);
    }

}
