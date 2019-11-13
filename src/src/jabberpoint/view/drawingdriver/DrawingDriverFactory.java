package jabberpoint.view.drawingdriver;

import jabberpoint.model.Configuration;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.ConfigurationException;
import jabberpoint.model.exception.ExceptionFactory;

/**
 * Factory for the creation of implementations of {@link DrawingDriver}
 */
public class DrawingDriverFactory {

    private static final DrawingDriver SWING_DRAWING_DRIVER = new SwingDrawingDriver();
    private static final DrawingDriver JAVAFX_DRAWING_DRIVER = new JavaFXDrawingDriver();

    /**
     * Returns an instance of a {@link DrawingDriver}
     * 
     * @return the {@link DrawingDriver} instance
     */
    public static DrawingDriver getInstance() {

        if (Configuration.getDrawingDriverConfig().equals(Configuration.DrawingDriverConfig.SWING)) {
            return SWING_DRAWING_DRIVER;
        } else if (Configuration.getDrawingDriverConfig().equals(Configuration.DrawingDriverConfig.JAVAFX)) {
            return JAVAFX_DRAWING_DRIVER;
        } else {
            throw ExceptionFactory.createConfigurationException("Incorrect DrawingDriver configuration");
        }

    }

}
