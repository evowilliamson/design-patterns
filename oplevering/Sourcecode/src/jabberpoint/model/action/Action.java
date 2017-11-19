package jabberpoint.model.action;

import jabberpoint.model.slideitems.SlideItem;

/**
 * Interface that defines the contract for actions within the Jabberpoint application.
 * Actions triggered in three ways:
 * 1. The user can use the keyboard to move through the slides
 * 2. The user can click from a menu a certain action
 * 3. On clicking a certain {@link SlideItem} on the slide
 * @author Ivo Willemsen
 * *  */
public interface Action {

    /**
     * Perform the action
     */
    void execute();

}
