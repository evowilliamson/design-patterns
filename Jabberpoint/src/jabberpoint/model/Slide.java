package jabberpoint.model;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

import java.util.List;

/**
 * This class represents a Slide in a SlideShow. A Slide maintains and
 * manages a list of SlideItems
 */
public class Slide {

    private List<SlideItem> slideItems;

    private String title;

    public Slide(String title) {
        this.title = title;
    }

    /**
     * Draws the slide via de {@link DrawingDriver}
     */
    public void draw() {

        DrawingDriver drawingDriver = DrawingDriverFactory.getInstance();
        drawingDriver.drawSlide();
        for (SlideItem slideItem : this.slideItems) {
            slideItem.draw();
        }

    }

    /**
     * This method adds a slide item to the slide
     * @param slideItem the item to be added
     */
    public void addSlideItem(SlideItem slideItem) {
        slideItems.add(slideItem);
    }

    public List<SlideItem> getSlideItems() {
        return slideItems;
    }

    public void setSlideItems(final List<SlideItem> slideItems) {
        this.slideItems = slideItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
