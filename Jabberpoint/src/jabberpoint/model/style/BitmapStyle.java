package jabberpoint.model.style;

/**
 * @author Ivo Willemsen
 * @version 1.0
 * Class that represents the style for a bitmap formatted object.
 *
 * No extra behavior for the moment
 */
public class BitmapStyle extends Style {

    /**
     * Constructor that creates a bitmap style. Accessor visibility should be package because only
     * the {@link StyleFactory} should create Styles.
     * @param indent number of units that should be reserved as space X-wise.
     * @param leading number of units that should be reserved as space Y-wise.
     */
    BitmapStyle(final int indent, final int leading) {
        super(indent, leading);
    }
}
