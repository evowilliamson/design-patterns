package jabberpoint.model.style;

import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * @author Ivo Willemsen
 * @version 1.0
 * Class that represents the style for a text formatted object.
 *
 * Setter are not implemented for this class, as after creating the styles, there won't
 * be a need to change them.
 */
public class TextStyle extends Style {

    private Color color;
    private Font font;
    private int fontSize;

    /**
     * Constructor that creates a style. Accessor visibility should be package because only
     * the implementations of {@link StyleFactory} should create Styles.
     * @param color the color of the text, of type {@link Color}.
     * @param font the font that is used to display the text, of type {@link Font}.
     * @param fontSize the size of the font used to display the text.
     * @param indent number of units that should be reserved as space X-wise.
     * @param leading number of units that should be reserved as space Y-wise.
     */
    TextStyle(
            @NotNull final Color color,
            @NotNull final Font font,
            final int fontSize,
            int indent,
            int leading) {
        super(indent, leading);

    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

}
