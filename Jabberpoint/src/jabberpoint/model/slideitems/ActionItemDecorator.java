package jabberpoint.model.slideitems;

import java.util.List;

import jabberpoint.model.action.Action;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

/**
 * Abstract class that specifies a Decorator for action items. {@link SlideItem} can be have 0, 1 or
 * more actions, so that when a user clicks on the slide, the action will be triggered.
 * {@link jabberpoint.model.drawingdriver.DrawingDriver} is the abstraction of the implementations and it defines
 * a contract for invoking the draw() method on a {@link ActionItemDecorator}.
 * a {@link ActionItemDecorator} doesn't provide any added behavior. It "decorates" an underlying {@link SlideItem} in such a way that the
 * {@link jabberpoint.model.drawingdriver.DrawingDriver} implementation can access it to draw it, plus
 * indicate to the user in a graphical manner that 1 or more actions are "under" the slide item.
 * 
 * @author Ivo Willemsen
 *
 */
public class ActionItemDecorator extends SlideItem {

    private SlideItem slideItem;

    /**
     * Constructor that creates an action decorated SlideItem with a certain level
     *
     */
    public ActionItemDecorator(final SlideItem slideItem, final List<Action> actions) {

        super(0);
        this.setActions(actions);
        this.slideItem = slideItem;

    }

    @Override
    /**
     * Decorator Pattern. It will first apply the decoration if this a leave decorator
     *
     */
    public void draw() {

        DrawingDriverFactory.getInstance().drawActionItemDecorator(this);

    }

}
