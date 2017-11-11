package jabberpoint.model;

/**
 * Class that represents the configuration of the system. In a real application, configuration will probably
 * be performed with property files or database configuration. But this attempt here shows how configuration can
 * be extraced
 */
public class Configuration {

    /**
     * Returns the configured {@link jabberpoint.model.drawingDriver.DrawingDriver}.
     * @return
     */
    public static DrawingDriverConfig getDrawingDriver() {
        return DrawingDriverConfig.SWING;
    }

    public enum DrawingDriverConfig {
        SWING, JAVAFX
    }

}
