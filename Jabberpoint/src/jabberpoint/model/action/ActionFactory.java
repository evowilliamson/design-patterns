package jabberpoint.model.action;

/**
 * This factory creates actions. Every invocation of a createXXX() method creates
 * a new Action, as this is not a configurable setting, but is done on a per-user request basis
 */
public class ActionFactory {

    /**
     * This method creates a new {@link AbsoluteNavigationAction}.
     * @param position indicates the type of the {@link AbsoluteNavigationAction}, which can be
     *                 FIRST or LAST
     * @return the created action
     */
    public static Action createAbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition position) {
        return new AbsoluteNavigationAction(position);
    }

    /**
     * This method creates a new {@link AbsoluteNavigationAction} and specifies the slide number to move to
     * @param slideNumber the number of the slide to navigate to
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
     * @return the created action
     */
    public static Action createOpenDemoSlideshowAction() {
        return new OpenDemoSlideshowAction();
    }

}
