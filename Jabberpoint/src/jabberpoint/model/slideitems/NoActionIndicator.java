package jabberpoint.model.slideitems;

import jabberpoint.model.action.Action;

public class NoActionIndicator extends ActionItemDecorator {

    public NoActionIndicator(final int level, final Action action, final SlideItem slideItem) {
        super(level, action, slideItem);
    }

    @Override
    protected void drawActionIndicator() {

    }

}
