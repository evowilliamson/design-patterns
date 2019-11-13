package jabberpoint.model;

/**
 * Interface that defines a contract for a composite slideshow component.
 * 
 * @author Ivo Willemsen
 */
public interface CompositeSlideShowComponent {

    /**
     * Specifies the contract for drawing from a specification point of perspective
     */
    void draw();

    /**
     *
     * @param i
     *            integer to identify the component in the list of children
     * @return an object of type {@link CompositeSlideShowComponent}
     */
    CompositeSlideShowComponent getComponent(int i);

    /**
     *
     * @return the number of children
     */
    int getComponentCount();

    /**
     * Adds a component
     * 
     * @param component
     *            the component to be added
     */
    void addComponent(CompositeSlideShowComponent component);

}
