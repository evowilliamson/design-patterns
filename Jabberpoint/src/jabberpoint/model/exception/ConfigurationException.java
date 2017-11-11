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
    }

    public ConfigurationException(String message) {
        super(message);
    }

}
