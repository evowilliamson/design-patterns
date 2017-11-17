package jabberpoint.model;

/**
 * Class that represents the configuration of the system. In a real application, configuration will probably
 * be performed with property files or database configuration. But this attempt here shows how configuration can
 * be extracted
 * 
 * @author Ivo Willemsen
 */
public class Configuration {

    private static AccessorConfig accessorConfiguration = AccessorConfig.XML;

    /**
     * Returns the configured {@link jabberpoint.model.drawingdriver.DrawingDriver}.
     * 
     * @return
     */
    public static DrawingDriverConfig getDrawingDriverConfig() {
        // Make the configuration!
        return DrawingDriverConfig.SWING;
    }

    public static AccessorConfig getAccessorConfig() {
        return accessorConfiguration;
    }

    public static void setAccessorConfig(AccessorConfig accessorConfig) {
        accessorConfiguration = accessorConfig;
    }

    public enum DrawingDriverConfig {
        SWING, JAVAFX
    }

    public enum AccessorConfig {
        XML, DEMO
    }

}
