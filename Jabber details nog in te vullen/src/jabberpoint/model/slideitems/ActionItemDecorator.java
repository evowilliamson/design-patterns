package jabberpoint.model.slideitems;

import jabberpoint.model.action.Action;

import java.util.List;

/**
 * Abstract class that specifies a Decorator for action items. {@link SlideItem} can be decorated
 * with actions, so that when a user clicks on the slide, the action will be triggered.
 */
public class ActionItemDecorator extends SlideItem {

    private SlideItem slideItem;

    /**
     * Constructor that creates a action decorated SlideItem with a certain level
     *
     */
    public ActionItemDecorator(SlideItem slideItem, final List<Action> actions) {
        super(slideItem.getLevel()); // Not too happy about this... no time to refactor :-(
        this.setActions(actions);
        this.slideItem = slideItem;
    }

    @Override
    /**
     * Decorator Pattern. It will first apply the decoration if this a leave decorator
     *
     */
    public void draw() {

        slideItem.draw();

    }

}
