package jabberpoint.model.style;

import java.util.Map;

/**
 * @author Ivo Willemsen
 * @version 1.0
 * Class that contains static methods to retrieve styles for text items and bitmap items
 * This abstract class encapsulates the other two concrete factories which are maintained in two
 * static attributes.
 */
public abstract class StyleFactory {

    protected Map<Integer, Style> styles;
    private static BitmapStyleFactory bitmapStyleFactory = new BitmapStyleFactory();
    private static TextStyleFactory textStyleFactory = new TextStyleFactory();

    protected Style getInstance(int level) {
        return styles.get(level);
    }

    /**
     * Retrieves the TextStyle associated with the level
     * @param level
     * @return The BitmapStyle associated with the level
     */
    public static Style getBitmapStyle(int level) {
        return bitmapStyleFactory.getInstance(level);
    }

    /**
     * Retrieves the TextStyle associated with the level
     * @param level
     * @return The TextStyle associated with the level
     */
    public static Style getTextStyle(int level) {
        return textStyleFactory.getInstance(level);
    }

}
