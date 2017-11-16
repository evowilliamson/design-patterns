package jabberpoint.model.style;

/**
 * Abstract class for extending concrete implementations of different of style for
 * different type of drawable objects, like bitmaps, text, etc...
 *
 * Setter are not implemented for this class, as after creating the styles, there won't
 * be a need to change them.
 */
public abstract class Style {

    private int indent;
    private int leading;

    /**
     * Constructor that creates a style. Accessor visibility should be package because only
     * the {@link BaseStyleFactory} concrete implementations should create Styles.
     * 
     * @param indent
     *            number of units that should be reserved as space X-wise.
     * @param leading
     *            number of units that should be reserved as space Y-wise.
     */
    Style(final int indent, final int leading) {

        this.indent = indent;
        this.leading = leading;

    }

    public int getIndent() {

        return indent;

    }

    public int getLeading() {

        return leading;

    }

}
