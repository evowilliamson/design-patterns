package jabberpoint.model.action;

import jabberpoint.model.Slideshow;

/**
 * This class represents the action that has an absolute character, going to the
 * first, last or indexed slide
 */
public class RelativeNavigationAction extends NavigationAction {

    private NavigationDirection direction;

    /**
     * Constructor that creates an action to move according to the indicated {@link NavigationDirection}
     * 
     * @param direction
     *            the position to move to
     */
    public RelativeNavigationAction(NavigationDirection direction) {

        this.direction = direction;

    }

    @Override
    public void execute() {

        Slideshow slideShow = Slideshow.getInstance();
        if (this.direction == NavigationDirection.NEXT && canGoToNext(slideShow)) {
            slideShow.setCurrentSlideNumber(slideShow.getCurrentSlideNumber() + 1);
        }
        else if (this.direction == NavigationDirection.PREVIOUS && canGoToPrevious(slideShow)) {
            slideShow.setCurrentSlideNumber(slideShow.getCurrentSlideNumber() - 1);
        }

    }

    /**
     * Checks to see if the current slide is the last slide. In that case, next slide is
     * not available
     * 
     * @param slideshow
     *            the Singleton {@link Slideshow}.
     * @return true if a move to the next slide is OK, otherwise, false
     */
    private boolean canGoToNext(Slideshow slideshow) {

        return !slideshow.isCurrentSlideLastSlide();

    }

    /**
     * Checks to see if the current slide is the first slide. In that case, previous slide is
     * not available
     * 
     * @param slideshow
     *            the Singleton {@link Slideshow}.
     * @return true if a move to the previous slide is OK, otherwise, false
     */
    private boolean canGoToPrevious(Slideshow slideshow) {

        return !slideshow.isCurrentSlideFirstSlide();

    }

    public enum NavigationDirection {
        NEXT, PREVIOUS
    }

}
