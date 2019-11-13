package jabberpoint.model.slideitems;

import java.util.ArrayList;
import java.util.List;

import jabberpoint.model.CompositeSlideShowComponent;
import jabberpoint.model.action.Action;
import jabberpoint.model.drawingdriver.DrawingDriver;

/**
 * Abstract class to represent a generic slide item. A slide item has
 * a level which leads to a certain way of styling the slide item
 */
public abstract class SlideItem implements CompositeSlideShowComponent {

    public SlideItem() {
    }
    
    private List<Action> actions = new ArrayList<Action>();

    // Implementations for Composite Design Pattern

    /**
     * This method draws the slide item through the {@link DrawingDriver}
     */
    public abstract void draw();

    /**
     * Is a child, so useless empty implementation
     * 
     * @param i
     *            integer to identify the component in the list of children
     * @return
     */
    public CompositeSlideShowComponent getComponent(final int i) {

        return null;

    }

    /**
     * Is a child, so useless empty implementation
     * 
     * @return
     */
    public int getComponentCount() {

        return 0;

    }

    /**
     * Is a child, so useless empty implementation
     * 
     * @param component
     *            the component to be added
     */

    /**
     * Is a child, so useless empty implementation
     * 
     * @param component
     *            the component to be added
     */
    public void addComponent(CompositeSlideShowComponent component) {
    }

    // Getters/Setters:

    public void setActions(final List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }

}
