package jabberpoint.model;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Slide in a SlideShow. A Slide maintains and
 * manages a list of SlideItems
 */
public class Slide extends CompositeSlideShowComponent {

    private List<CompositeSlideShowComponent> slideItems = new ArrayList<CompositeSlideShowComponent>();

    private String title;

    public Slide(String title) {
        this.title = title;
    }

    // Methods that implement the Composite Pattern for this class

    @Override
    public CompositeSlideShowComponent getComponent(final int i) {
        return slideItems.get(i);
    }

    @Override
    public int getComponentCount() {
        return slideItems.size();
    }

    @Override
    public void addComponent(final CompositeSlideShowComponent component) {
        slideItems.add(component);
    }

    /**
     * Draws the slide via de {@link DrawingDriver}
     */
    @Override public void draw() {

        System.out.println("drawing slide");
        DrawingDriver drawingDriver = DrawingDriverFactory.getInstance();
        drawingDriver.drawCurrentSlideNumber(
                Slideshow.getInstance().getCurrentSlideNumber());
        drawingDriver.drawTitle(this.getTitle());
        for (CompositeSlideShowComponent slideItem : this.slideItems) {
            slideItem.draw();
        }
        System.out.println("drawing finished");

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
