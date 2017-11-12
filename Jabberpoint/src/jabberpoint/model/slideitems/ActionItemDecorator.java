package jabberpoint.model.slideitems;

import jabberpoint.model.action.Action;

/**
 * Abstract class that specifies a Decorator for action items. {@link SlideItem} can be decorated
 * with actions, so that when a user clicks on the slide, the action will be triggered.
 */
public abstract class ActionItemDecorator extends SlideItem {

    private SlideItem slideItem;
    private Action action;

    /**
     * Constructor that creates a action decorated SlideItem with a certain level
     *
     * Example:
     * new ActionItemDecorator(
     *      1,
     *      new AbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.FIRST),
     *      new ActionItemDecorator(
     *          2,
     *          new AuxilaryAction(AuxilaryAction.BEEP),
     *          new TextItem(3, "Go to first slide")));
     *
     * @param level the level that is associated with the slide item
     */
    public ActionItemDecorator(final int level, Action action, SlideItem slideItem) {
        super(level);
        this.action = action;
        this.slideItem = slideItem;
    }

    @Override
    /**
     * Decorator Pattern. It will first apply the decoration if this a leave decorator
     *
     */
    public void draw() {

        if (action == null) {
            /*
             * Leaf level: No more other actions. So slideItem will be a concrete implementation.
             * This means that now the slide item should be decorated.
             */
            drawActionIndicator();
        }
        slideItem.draw();

    }

    /**
     * This method will be implemented by the concrete implementations and it specifies
     * that a certain {@link SlideItem} will be decorated a certain way that will indicate
     * to the user that an {@link Action} is attached to it.
     */
    protected abstract void drawActionIndicator();

}
