package jabberpoint.model.action;

/**
 * This factory creates actions. Every invocation of a createXXX() method creates
 * a new Action, as this is not a configurable setting, but is done on a per-user request basis
 */
public class ActionFactory {

    /**
     * This method creates a new {@link AbsoluteNavigationAction}.
     * @param position indicates the type of the {@link AbsoluteNavigationAction}, which can be
     *                 FIRST, LAST or INDEX
     * @return the created action
     */
    public static Action createAbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition position) {
        return new AbsoluteNavigationAction(position);
    }

}
