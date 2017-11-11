package jabberpoint.model.style;

import java.util.Map;

/**
 * This abstract class is extended by concrete implementations that create the styles
 */
public abstract class BaseStyleFactory {

    protected Map<Integer, Style> styles;

    /**
     * This method returns the style instance that is associated with the style
     * @param level the level that is associated with a certain style
     * @return instance of type Style
     */
    protected Style getInstance(int level) {
        return styles.get(level);
    }

}
