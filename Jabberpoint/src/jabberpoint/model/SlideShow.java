package jabberpoint.model;

import java.util.List;

/**
 * SlideShow is the main class of the Model part of the MVC. It implements the
 * Singleton Pattern. It will make sure that only one instance is ever present
 */
public class SlideShow {
    private static SlideShow instance = new SlideShow();

    private List<SlideOld> slides;

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
     * This method moves the slide show to the indicated slide number
     * @param slideNumber the slide number to which the slide show should advance
     */
    public void goToSlide(int slideNumber) {

    }

    /**
     * Return the number of slides in the slide show
     * @return the number of slides in the slide show
     */
    public int getNumberOfSlides() {
        return this.getSlides().size();
    }

    public List<SlideOld> getSlides() {
        return slides;
    }

    public void setSlides(final List<SlideOld> slides) {
        this.slides = slides;
    }

    public int getCurrentSlideNumber() {
        return currentSlideNumber;
    }

    public void setCurrentSlideNumber(final int currentSlideNumber) {
        this.currentSlideNumber = currentSlideNumber;
    }

}
