package jabberpoint.model;

/**
 * Class that represents the configuration of the system. In a real application, configuration will probably
 * be performed with property files or database configuration. But this attempt here shows how configuration can
 * be extracted
 */
public class Configuration {

    /**
     * Returns the configured {@link jabberpoint.model.drawingdriver.DrawingDriver}.
     * @return
     */
    public static DrawingDriverConfig getDrawingDriver() {
        // Make the configuration!
        return DrawingDriverConfig.SWING;
    }

    public enum DrawingDriverConfig {
        SWING, JAVAFX
    }

}
