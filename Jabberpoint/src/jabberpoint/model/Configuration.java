package jabberpoint.model;

/**
 * Class that represents the configuration of the system. In a real application, configuration will probably
 * be performed with property files or database configuration. But this attempt here shows how configuration can
 * be extracted
 */
public class Configuration {

    private static AccessorConfig accessorConfig;

    /**
     * Returns the configured {@link jabberpoint.model.drawingdriver.DrawingDriver}.
     * @return
     */
    public static DrawingDriverConfig getDrawingDriverConfig() {
        // Make the configuration!
        return DrawingDriverConfig.SWING;
    }

    public enum DrawingDriverConfig {
        SWING, JAVAFX
    }

    public static void setAccessorConfig(AccessorConfig accessorConfig) {
        accessorConfig = accessorConfig;
    }

    public static AccessorConfig getAccessorConfig() {
        return accessorConfig;
    }

    public enum AccessorConfig {
        XML, DEMO
    }

}
