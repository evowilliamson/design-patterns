package jabberpoint.model.style;

import javax.xml.soap.Text;
import java.util.Map;

/**
 * Class that contains static methods to retrieve styles for text items and bitmap items
 * This class encapsulates the two concrete factories which are maintained in two
 * static attributes.
 */
public abstract class StyleFactory {

    private static BitmapStyleFactory bitmapStyleFactory = new BitmapStyleFactory();
    private static TextStyleFactory textStyleFactory = new TextStyleFactory();

    /**
     * Retrieves the TextStyle associated with the level
     * @param level
     * @return The BitmapStyle associated with the level
     */
    public static BitmapStyle getBitmapStyle(int level) {
        return (BitmapStyle) bitmapStyleFactory.getInstance(level);
    }

    /**
     * Retrieves the TextStyle associated with the level
     * @param level
     * @return The TextStyle associated with the level
     */
    public static TextStyle getTextStyle(int level) {
        return (TextStyle) textStyleFactory.getInstance(level);
    }

}
