package jabberpoint.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Slideshow is the main class of the Model part of the MVC. It implements the
 * Singleton Pattern. It will make sure that only one instance is ever present.
 * This class delegates the drawing of the slide to the Slide class.
 */
public class Slideshow {
    private static Slideshow instance = new Slideshow();

    public static final int FIRST_SLIDE = 0;

    private List<Slide> slides;

    private String name;
    private int currentSlideNumber;
    private String title;

    /**
     * To prevent instantiation from outside the class, make the constructor private
     */
    private Slideshow() {
        slides = new ArrayList<Slide>();
    }

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
    public static Slideshow createInstance() {
        instance = new Slideshow();
        return instance;
    }

    /**
     * This method draws indicated slide number
     * 
     * @param slideNumber
     *            the slide number to which the slide show should advance
     */
    public void drawSlide(int slideNumber) {
        this.getSlides().get(slideNumber).draw();
    }

    /**
     * Adds a {@link Slide} to the slideshow
     * 
     * @param slide
     *            the slide that will be added
     */
    public void addSlide(Slide slide) {
        this.slides.add(slide);
    }


    /**
     * Checks to see if the current slide is the last slide in the {@link Slideshow}.
     * @return true if it is the last slide, otherwise no
     */
    public boolean isCurrentSlideLastSlide() {

        if (this.getNumberOfSlides() - 1 == this.getCurrentSlideNumber()) {
            return true;
        }
        return false;

    }

    /**
     * Checks to see if the current slide is the first slide in the {@link Slideshow}.
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
     * @param slideNumber the slide number
     * @return true if a move to the next slide is OK, otherwise, false
     */
    public boolean isCorrectSlideNumber(int slideNumber) {
        if (slideNumber >= FIRST_SLIDE && slideNumber <= this.getNumberOfSlides() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Return the number of slides in the slide show
     * 
     * @return the number of slides in the slide show
     */
    public int getNumberOfSlides() {
        return this.getSlides().size();
    }

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(final List<Slide> slides) {
        this.slides = slides;
    }

    public int getCurrentSlideNumber() {
        return currentSlideNumber;
    }

    public void setCurrentSlideNumber(final int currentSlideNumber) {
        this.currentSlideNumber = currentSlideNumber;
    }

    public String getName() {
        return name;
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

}
