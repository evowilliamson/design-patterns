package jabberpoint.model.slideitems;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

/**
 * Abstract class to represent a generic slide item. A slide item has
 * a level which leads to a certain way of styling the slide item
 */
public abstract class SlideItem {

    private int level;

    /**
     * Constructor that creates a SlideItem with a certain level
     * @param level the level that is associated with the slide item
     */
    public SlideItem(int level) {
        this.level = level;
    }

    /**
     * This method draws the slide item through the {@link DrawingDriver}
     */
    public abstract void draw();

    // Getters/Setters:
    public int getLevel() {
        return level;
    }
    public void setLevel(final int level) {
        this.level = level;
    }

}
