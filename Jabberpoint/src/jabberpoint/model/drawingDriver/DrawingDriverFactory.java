package jabberpoint.model.drawingDriver;

import jabberpoint.model.Configuration;
import jabberpoint.model.exception.ConfigurationException;

/**
 * Factory for the creation of implementations of {@link DrawingDriver}
 */
public class DrawingDriverFactory {

    private static final DrawingDriver SWING_DRAWING_DRIVER = new SwingDrawingDriver();
    private static final DrawingDriver JAVAFX_DRAWING_DRIVER = new JavaFXDrawingDriver();

    /**
     * Returns an instance of a {@link DrawingDriver}
     * @return the instance
     */
    public static DrawingDriver getInstance() {

       if (Configuration.getDrawingDriver().equals(Configuration.DrawingDriverConfig.SWING)) {
           return SWING_DRAWING_DRIVER;
       } else if (Configuration.getDrawingDriver().equals(Configuration.DrawingDriverConfig.JAVAFX)) {
           return JAVAFX_DRAWING_DRIVER;
       } else {
           throw new ConfigurationException("Incorrect DrawingDriver configuration");
       }

    }

}
