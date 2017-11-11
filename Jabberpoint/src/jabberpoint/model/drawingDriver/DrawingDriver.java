package jabberpoint.model.drawingDriver;

import jabberpoint.model.Slide;
import jabberpoint.model.SlideItem;

/**
 * DrawingDriver is the abstraction of different type of drawing interfaces that
 * could be possible present. The purpose is to make the Model entities not dependent
 * on implementation specifics, like Swing components, or JavaFx components.
 * The Model entities will communicate with implementations of DrawingDrivers through
 * this interface
 */
public interface DrawingDriver {

    /**
     * Contract that defines the draswing of a {@link Slide} onto a DrawingDriver implementation
     */
    void drawSlide();

    /**
     * Contract that defines the drawing of a {@link SlideItem} onto a DrawingDriver implementation
     */
    void drawSlideItem();

}
