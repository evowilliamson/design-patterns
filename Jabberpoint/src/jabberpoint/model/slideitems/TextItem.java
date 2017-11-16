package jabberpoint.model.slideitems;

import jabberpoint.view.drawingdriver.DrawingDriverFactory;

public class TextItem extends SlideItem {

    private String text;

    /**
     * Constructor that creates a SlideItem with a certain level
     *
     * @param level
     *            the level that is associated with the slide item
     * @param text
     *            the text that is the essential part of the text item
     */
    public TextItem(final int level, String text) {

        super(level);
        this.text = text;

    }

    @Override
    public void draw() {
        DrawingDriverFactory.getInstance().drawTextItem(this);
    }

    // Getters/Setters:
    public String getText() {

        return text;

    }

}
