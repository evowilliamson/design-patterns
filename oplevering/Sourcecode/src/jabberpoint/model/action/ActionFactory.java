package jabberpoint.model.action;

/**
 * This factory creates actions. Every invocation of a createXXX() method creates
 * a new Action, as this is not a configurable setting, but is done on a per-user request basis
 * 
 * @author Ivo Willemsen
 */
public class ActionFactory {

    /**
     * This method creates a new {@link AbsoluteNavigationAction}.
     * 
     * @param position
     *            indicates the type of the {@link AbsoluteNavigationAction}, which can be
     *            FIRST or LAST
     * @return the created action
     */
    public static Action createAbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition position) {
        return new AbsoluteNavigationAction(position);
    }

    /**
     * This method creates a new {@link AbsoluteNavigationAction} and specifies the slide number to move to
     * 
     * @param slideNumber
     *            the number of the slide to navigate to
     * @return the created action
     */
    public static Action createAbsoluteNavigationAction(
            final int slideNumber) {
        AbsoluteNavigationAction action = new AbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.INDEX);
        action.setSlideNumber(slideNumber);
        return action;
    }

    public static Action createRelativeNavigationAction(RelativeNavigationAction.NavigationDirection direction) {
        return new RelativeNavigationAction(direction);
    }

    /**
     * This factory method creates a new {@link OpenDemoSlideshowAction}.
     * 
     * @return the created OpenDemoSlideshowAction
     */
    public static Action createOpenDemoSlideshowAction() {
        return new OpenDemoSlideshowAction();
    }

    /**
     * This factory method creates a new {@link OpenSlideshowAction}
     * 
     * @param fileName
     *            the file name that corresponds with a file on the file system
     * @return the created OpenSlideshowAction
     */
    public static Action createOpenSlideshowAction(String fileName) {

        return new OpenSlideshowAction(fileName);

    }
    /**
     * This factory method creates a new {@link OpenSlideshowAction}
     * 
     * @param fileName
     *            the file name that corresponds with a file on the file system
     * @return the created OpenSlideshowAction
     */
    public static Action createSaveSlideshowAction(String fileName) {

        return new SaveSlideshowAction(fileName);

    }

    public static Action createAuxiliaryAction(AuxiliaryAction.AuxAction auxAction) {
        return new AuxiliaryAction(auxAction);
    }

}
