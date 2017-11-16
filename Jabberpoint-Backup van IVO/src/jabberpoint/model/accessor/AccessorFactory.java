package jabberpoint.model.accessor;

import jabberpoint.model.Configuration;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.ConfigurationException;
import jabberpoint.view.drawingdriver.JavaFXDrawingDriver;
import jabberpoint.view.drawingdriver.SwingDrawingDriver;

/**
 * Factory for the creation of implementations of {@link DrawingDriver}
 */
public class AccessorFactory {

    private static final DemoAccessor DEMO_ACCESSOR = new DemoAccessor();
    private static final XMLAccessor XML_ACCESSOR = new XMLAccessor();

    /**
     * Returns an instance of a {@link Accessor}
     * 
     * @return the {@link Accessor} instance
     */
    public static Accessor getInstance() {

        if (Configuration.getAccessorConfig().equals(Configuration.AccessorConfig.XML)) {
            return XML_ACCESSOR;
        } else {
            throw new ConfigurationException("Incorrect Accessor configuration");
        }

    }

    public static DemoAccessor getDemoAccessor() {
        return DEMO_ACCESSOR;
    }

}
