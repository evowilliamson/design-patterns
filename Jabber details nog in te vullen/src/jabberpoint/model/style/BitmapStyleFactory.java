package jabberpoint.model.style;

import java.util.HashMap;

/**
 * Factory that, on initialization, constructs the available {@link BitmapStyle} objects
 */
public class BitmapStyleFactory extends BaseStyleFactory {

    /**
     * Constructor that puts the bitmap styles in the styles attribute
     */
    BitmapStyleFactory() {
        styles = new HashMap<Integer, Style>();
        styles.put(1, new BitmapStyle(0, 20));
        styles.put(2, new BitmapStyle(20, 10));
        styles.put(3, new BitmapStyle(50, 10));
        styles.put(4, new BitmapStyle(70, 10));
        styles.put(5, new BitmapStyle(90, 10));
    }

}
