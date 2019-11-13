package jabberpoint.model;

/**
 * Abstract class for implementing Composite Pattern for slide show components
 */
public abstract class CompositeSlideShowComponent {

    /**
     * Specifies the contract for drawing
     */
    public abstract void draw();

    /**
     *
     * @param i integer to identify the component in the list of children
     * @return an object of type {@link CompositeSlideShowComponent}
     */
    public abstract CompositeSlideShowComponent getComponent(int i);

    /**
     *
     * @return the number of children
     */
    public abstract int getComponentCount();

    /**
     * Adds a component
     * @param component the component to be added
     */
    public abstract void addComponent(CompositeSlideShowComponent component);

}
