package jabberpoint.model;

/**
 * Class that represents the configuration of the system. In a real application, configuration will probably
 * be performed with property files or database configuration. But this attempt here shows how configuration can
 * be extracted
 * 
 * @author Ivo Willemsen
 */
public class Configuration {

    /**
     * Returns the configured {@link jabberpoint.model.drawingdriver.DrawingDriver}.
     * 
     * @return the configured {@link DrawingDriverConfig}
     */
    public static DrawingDriverConfig getDrawingDriverConfig() {

        return DrawingDriverConfig.SWING;

    }

    /**
     * Returns the configured {@link AccessorConfig}
     * @return the configured {@link AccessorConfig}
     */
    public static AccessorConfig getAccessorConfig() {

        return AccessorConfig.XML;

    }

    public enum DrawingDriverConfig {
        SWING, JAVAFX
    }

    public enum AccessorConfig {
        XML, OLDXML
    }

}
