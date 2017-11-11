package jabberpoint.model.style;

/**
 * Factory that, on initialization, constructs the available {@link BitmapStyle} objects
 */
public class TextStyleFactory extends StyleFactory {

    TextStyleFactory() {
        styles.put(1, new BitmapStyle(1, 1));
    }

}
