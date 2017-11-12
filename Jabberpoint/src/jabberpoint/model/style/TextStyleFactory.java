package jabberpoint.model.style;

import java.awt.*;
import java.util.HashMap;

/**
 * Factory that, on initialization, constructs the available {@link BitmapStyle} objects
 */
public class TextStyleFactory extends BaseStyleFactory {

    /**
     * Constructor that puts the text styles in the styles attribute
     */
    TextStyleFactory() {
        styles = new HashMap<Integer, Style>();
        styles.put(1, new TextStyle(Color.red, 48, 0, 20));
        styles.put(2, new TextStyle(Color.blue, 40, 20, 10));
        styles.put(3, new TextStyle(Color.black, 36, 50, 10));
        styles.put(4, new TextStyle(Color.black, 30, 70, 10));
        styles.put(5, new TextStyle(Color.black, 24, 90, 10));
    }

}
