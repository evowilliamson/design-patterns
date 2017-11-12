package jabberpoint.model.drawingdriver;

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
     * Initializes the drawing driver
     *
     * @param title
     *            the title to which the window should be set
     */
    void initialize(final String title);

    /**
     * Initializes the slide
     */
    void initializeSlide();

    /**
     * Draw the current slide number on the screen
     * 
     * @param currentSlideNumber
     *            the current slide number
     */
    void drawCurrentSlideNumber(final int currentSlideNumber);

    /**
     * Draws the title on the screen
     * 
     * @param title
     *            the title
     */
    void drawTitle(final String title);

    /**
     * Contract that defines the drawing of a {@link SlideItem} onto a DrawingDriver implementation
     */
    void drawSlideItem();

}
