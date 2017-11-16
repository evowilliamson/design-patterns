package jabberpoint.model.style;

import java.util.Map;

import jabberpoint.model.exception.ConfigurationException;

/**
 * This abstract class is extended by concrete implementations that create the styles
 * 
 * @author Ivo Willemsen
 */
public abstract class BaseStyleFactory {

    protected Map<Integer, Style> styles;

    /**
     * This method returns the style instance that is associated with the style
     * 
     * @param level
     *            the level that is associated with a certain style
     * @return instance of type Style
     */
    protected Style getInstance(final int level) {

        Style style = styles.get(adjustLevel(level));
        if (style == null) {
            throw new ConfigurationException("Styles not correctly configured");
        }

        return style;

    }

    /**
     * This method adjusts the level if not within boundaries
     * 
     * @param level
     *            the level to be adjusted
     * @return
     */
    private int adjustLevel(final int level) {

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
