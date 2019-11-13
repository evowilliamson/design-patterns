package jabberpoint.model.style;

/**
 * Class that represents the style for a bitmap formatted object.
 * No extra behavior for the moment
 * 
 * @author Ivo Willemsen
 */
public class BitmapStyle extends Style {

    /**
     * Constructor that creates a bitmap style. Accessor visibility should be package because only
     * the {@link StyleFactory} should create Styles.
     * 
     * @param indent
     *            number of units that should be reserved as space X-wise.
     * @param leading
     *            number of units that should be reserved as space Y-wise.
     */
    protected BitmapStyle(final int indent, final int leading) {

        super(indent, leading);

    }

    @Override
    public String toString() {

        return "BitmapStyle{" + super.toString() + "}";

    }
}
