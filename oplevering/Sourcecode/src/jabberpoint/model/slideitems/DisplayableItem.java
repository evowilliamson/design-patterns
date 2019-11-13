package jabberpoint.model.slideitems;

import jabberpoint.model.style.Style;

/**
 * This class serves as an abstract class for child classes {@link TextItem} and {@link BitmapItem}. It adds the attribute
 * {@link DisplayableItem#level} to the
 * base class {@link SlideItem} as a {@link DisplayableItem} is subject to
 * displaying and thus the level should be taken into account
 * 
 * @author Ivo Willemsen
 */
public abstract class DisplayableItem extends SlideItem {

    private int level;
    private Style style;

    /**
     * Constructor that creates a SlideItem with a certain level
     *
     * @param level
     *            the level that is associated with the slide item
     */
    protected DisplayableItem(final int level) {

        super();
        this.level = level;

    }

    // Getters/Setters:

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(final Style style) {
        this.style = style;
    }

}
