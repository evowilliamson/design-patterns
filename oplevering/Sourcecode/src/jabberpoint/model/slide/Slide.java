package jabberpoint.model.slide;

import jabberpoint.model.CompositeSlideShowComponent;
import jabberpoint.model.Slideshow;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Slide in a SlideShow. A Slide maintains and
 * manages a list of SlideItems using the Composite Design Pattern
 * @author Ivo Willemsen
 */
public class Slide implements CompositeSlideShowComponent {

    private List<CompositeSlideShowComponent> slideItems = new ArrayList<CompositeSlideShowComponent>();

    private String title;

    protected Slide(String title) {
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

        Slideshow slideshow = Slideshow.getInstance();
        DrawingDriver drawingDriver = DrawingDriverFactory.getInstance();
        drawingDriver.drawSlideNumberInfo(slideshow.getCurrentSlideNumber(), slideshow.getComponentCount());
        drawingDriver.drawTitle(this.getTitle());
        for (CompositeSlideShowComponent slideItem : this.slideItems) {
            slideItem.draw();
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
