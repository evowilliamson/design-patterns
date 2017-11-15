package jabberpoint.model.action;

import jabberpoint.model.Slide;
import jabberpoint.model.Slideshow;

/**
 * This class represents the action that has an absolute character, going to the
 * first, last or indexed slide
 */
public class AbsoluteNavigationAction extends NavigationAction {

    private NavigationPosition position;
    private int slideNumber;

    /**
     * Constructor that creates an action to move to the indicated {@link NavigationPosition}
     * @param position the position to move to
     */
    public AbsoluteNavigationAction(NavigationPosition position) {

        this.position = position;

    }

    /**
     * Constructor to create an action to move to the indicated slide number
     * @param position
     * @param slideNumber
     */
    public AbsoluteNavigationAction(NavigationPosition position, int slideNumber) {
        this(position);
        this.slideNumber = slideNumber;
    }

    @Override
    public void execute() {

    	System.out.println("AbsoluteNavigationAction triggered.");
    	Slideshow slideShow = Slideshow.getInstance();
        if (this.position == NavigationPosition.FIRST) {
            slideShow.setCurrentSlideNumber(Slideshow.FIRST_SLIDE);
        }
        else if (this.position == NavigationPosition.LAST) {
            slideShow.setCurrentSlideNumber(slideShow.getComponentCount() - 1);
        }
        else if (this.position == NavigationPosition.INDEX) {
            slideShow.setCurrentSlideNumber(this.slideNumber);
        }

    }

    /**
     * Sets the indicated slide number
     * @param slideNumber the slide number
     */
    public void setSlideNumber(int slideNumber) {

        this.slideNumber = slideNumber;

    }

    public enum NavigationPosition {
        FIRST, LAST, INDEX
    }

}
