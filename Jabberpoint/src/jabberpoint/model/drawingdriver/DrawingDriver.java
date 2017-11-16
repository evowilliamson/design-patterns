package jabberpoint.model.drawingdriver;

import jabberpoint.model.Theme;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.TextItem;

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
     * This method applies the theme to the slideshow implementation
     * 
     * @param theme
     *            the theme that translates in a certain visual representation
     */
    void applyTheme(final Theme theme);

    /**
     * Draw the current slide number on the screen
     * 
     * @param currentSlideNumber
     *            the current slide number
     * @param totalSlides
     *            the total number of slides
     */
    void drawSlideNumberInfo(final int currentSlideNumber, final int totalSlides);

    /**
     * Draws the title on the screen
     * 
     * @param title
     *            the title
     */
    void drawTitle(final String title);

    /**
     * Contract that defines the drawing of a {@link jabberpoint.model.slideitems.TextItem} onto a DrawingDriver implementation
     * 
     * @param textItem
     *            the text item
     */
    void drawTextItem(final TextItem textItem);

    /**
     * Contract that defines the drawing of a {@link jabberpoint.model.slideitems.BitmapItem}
     * 
     * @param bitmapItem
     *            the bitmap item
     */
    void drawBitmapItem(final BitmapItem bitmapItem);

    /**
     * Contract that defines the drawing of a {@link jabberpoint.model.action.Action}
     */
    void drawActionItemDecorator(final ActionItemDecorator actionItemDecorator);

}
