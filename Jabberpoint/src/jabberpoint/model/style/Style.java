package jabberpoint.model.style;

/**
 * @author Ivo Willemsen
 * @version 1.0
 * Abstract class for extending concrete implementations of different of style for
 * different type of drawable objects, like bitmaps, text, etc...
 */
public abstract class Style {

    private int indent;
    private int leading;

    /**
     * Constructor that creates a style. Accessor visibility should be package because only
     * the {@link}
     * @param indent
     * @param leading
     */
    Style(int indent, int leading) {
        this.indent = indent;
        this.leading = leading;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(final int indent) {
        this.indent = indent;
    }

    public int getLeading() {
        return leading;
    }

    public void setLeading(final int leading) {
        this.leading = leading;
    }

}
