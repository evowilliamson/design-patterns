package jabberpoint.model.style;

import java.awt.*;
import java.util.HashMap;

/**
 * Factory that, on initialization, constructs the available {@link BitmapStyle} objects
 */
public class TextStyleFactory extends BaseStyleFactory {

    TextStyleFactory() {
        styles = new HashMap<Integer, Style>();
        styles.put(1, new TextStyle(Color.black, null,  10, 1, 1));
    }

}
