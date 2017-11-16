package jabberpoint.model;

import java.util.ArrayList;
import java.util.List;

import jabberpoint.view.drawingdriver.DrawingDriverFactory;

/**
 * Slideshow is the main class of the Model part of the MVC. It implements the
 * Singleton Pattern. It will make sure that only one instance is ever present.
 * This class delegates the drawing of the slide to the Slide class.
 */
public class Slideshow implements CompositeSlideShowComponent {
    public static final int FIRST_SLIDE = 0;
    private static Slideshow instance;
    private List<CompositeSlideShowComponent> slides;

    private String name;
    private int currentSlideNumber;
    private String title;

    private Theme theme;

    /**
     * To prevent instantiation from outside the class, make the constructor private
     */
    private Slideshow(final Theme theme) {
        this.theme = theme;
        DrawingDriverFactory.getInstance().applyTheme(theme);
        slides = new ArrayList<CompositeSlideShowComponent>();
    }

    // Composite Design Pattern implementations

    /**
     * returns the static instance.
     *
     * @return
     */
    public static Slideshow getInstance() {
        return instance;
    }

    /**
     * This method is used in case a new SlideShow is created. So the static instance
     * should be refreshed.
     *
     * @return
     */
    public static Slideshow createInstance(final Theme theme) {
        instance = new Slideshow(theme);
        return instance;
    }

    @Override
    public void draw() {
        if (this.getComponentCount() != 0) {
            this.getComponent(this.getCurrentSlideNumber()).draw();
        }
    }

    @Override
    public CompositeSlideShowComponent getComponent(final int i) {
        return slides.get(i);
    }

    @Override
    public int getComponentCount() {
        return slides.size();
    }

    @Override
    public void addComponent(final CompositeSlideShowComponent component) {
        this.slides.add(component);
    }

    /**
     * Checks to see if the current slide is the last slide in the {@link Slideshow}.
     * 
     * @return true if it is the last slide, otherwise no
     */
    public boolean isCurrentSlideLastSlide() {

        if (this.getComponentCount() - 1 == this.getCurrentSlideNumber()) {
            return true;
        }
        return false;

    }

    /**
     * Checks to see if the current slide is the first slide in the {@link Slideshow}.
     * 
     * @return true if it is the last slide, otherwise no
     */
    public boolean isCurrentSlideFirstSlide() {

        if (this.getCurrentSlideNumber() == Slideshow.FIRST_SLIDE) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the indicated index slide number can be navigated to
     * not available
     * 
     * @param slideNumber
     *            the slide number
     * @return true if a move to the next slide is OK, otherwise, false
     */
    public boolean isCorrectSlideNumber(int slideNumber) {
        if (slideNumber >= FIRST_SLIDE && slideNumber <= this.getComponentCount() - 1) {
            return true;
        }
        return false;
    }

    // Setters and getters:

    public int getCurrentSlideNumber() {
        return currentSlideNumber;
    }

    public void setCurrentSlideNumber(final int currentSlideNumber) {
        this.currentSlideNumber = currentSlideNumber;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Theme getTheme() {
        return theme;
    }

}
