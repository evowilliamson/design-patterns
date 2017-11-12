package jabberpoint.model.drawingdriver;

import jabberpoint.model.Slide;
import jabberpoint.model.slideitems.SlideItem;

/**
 * DrawingDriver is the abstraction of different type of drawing interfaces that
 * could be possible present. The purpose is to make the Model entities not dependent
 * on implementation specifics, like Swing components, or JavaFx components.
 * The Model entities will communicate with implementations of DrawingDrivers through
 * this interface
 */
public interface DrawingDriver {

    /**
     * Contract that prepares the drawing of a {@link Slide}
     */
    void prepareSlide();

    /**
     * Contract that defines the drawing of a {@link SlideItem} onto a DrawingDriver implementation
     */
    void drawSlideItem();

    /**
     * Initializes the drawing driver
     *
     * @param title the title to which the window should be set
     */
    void initialize(String title);

    /**
     * Method that draws the current slide number
     * @param slideNumber
     */
    void drawCurrentSlideNumber(int slideNumber);
}
