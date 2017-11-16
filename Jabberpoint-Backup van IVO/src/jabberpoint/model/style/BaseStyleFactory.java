package jabberpoint.model.style;

import jabberpoint.model.exception.ConfigurationException;

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

        Style style = styles.get(adjustLevel(level));
        if (style == null) {
            throw new ConfigurationException("Styles not correctly configured");
        }

        return style;

    }

    /**
     * This method adjusts the level if not within boundaries
     * @param level
     * @return
     */
    private int adjustLevel(int level) {

        if (level >= styles.size()) {
            return styles.size();
        }
        else if (level < 0) {
            return 0;
        }
        else {
            return level;
        }

    }


}
