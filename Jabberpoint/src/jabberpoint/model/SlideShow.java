package jabberpoint.model;

import java.util.List;

/**
 * SlideShow is the main class of the Model part of the MVC. It implements the
 * Singleton Pattern. It will make sure that only one instance is ever present.
 * This class delegates the drawing of the slide to the Slide class.
 */
public class SlideShow {
    private static SlideShow instance = new SlideShow();

    private List<Slide> slides;

    private int currentSlideNumber;

    public static SlideShow getInstance() {
        return instance;
    }

    /**
     * To prevent instantiation from outside the class, make the constructor private
     */
    private SlideShow() {
    }

    /**
     * This method draws indicated slide number
     * @param slideNumber the slide number to which the slide show should advance
     */
    public void drawSlide(int slideNumber) {
        this.getSlides().get(slideNumber).draw();
    }

    /**
     * Return the number of slides in the slide show
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

}
