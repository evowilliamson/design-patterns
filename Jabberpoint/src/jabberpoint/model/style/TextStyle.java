package jabberpoint.model.style;

import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * Class that represents the style for a text formatted object.
 *
 * Setter are not implemented for this class, as after creating the styles, there won't
 * be a need to change them.
 */
public class TextStyle extends Style {

    private Color color;
    private int fontSize;
    private @NotNull Font font;

    private static final String FONTNAME = "Helvetica";

    /**
     * Constructor that creates a style. Accessor visibility should be package because only
     * the implementations of {@link StyleFactory} should create Styles.
     * @param color the color of the text, of type {@link Color}.
     * @param fontSize the size of the font used to display the text.
     * @param indent number of units that should be reserved as space X-wise.
     * @param leading number of units that should be reserved as space Y-wise.
     */
    TextStyle(
            @NotNull final Color color,
            final int fontSize,
            int indent,
            int leading) {
        super(indent, leading);
        this.color = color;
        this.fontSize = fontSize;
        this.font = new Font(FONTNAME, Font.BOLD, fontSize);
    }

    /**
     * Return the effective font
     * @param scale the scale that is used
     * @return the effective font
     */
    public Font getFont(float scale) {
        return font.deriveFont(fontSize * scale);
    }

    @Override
    public String toString() {
        return "TextStyle{" + super.toString() + "color=" + color + ", fontSize=" + fontSize + '}';
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(final int fontSize) {
        this.fontSize = fontSize;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(final Font font) {
        this.font = font;
    }

}
