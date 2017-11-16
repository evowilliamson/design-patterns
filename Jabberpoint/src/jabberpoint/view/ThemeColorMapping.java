package jabberpoint.view;

import jabberpoint.model.Theme;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that maps from abstraction to implementations regarding themes
 */
public class ThemeColorMapping {

    private static Map<Theme, Color> mappings = new HashMap<Theme, Color>();
    static {
        mappings.put(Theme.CLASSIC, Color.LIGHT_GRAY);
        mappings.put(Theme.NORMAL, Color.WHITE);
        mappings.put(Theme.POPULAR, Color.CYAN);
        mappings.put(Theme.MODERN, Color.MAGENTA);
    }

    /**
     * Return the color associated with the theme
     * @param theme the theme enum
     * @return the Color
     */
    public static Color getColor(Theme theme) {

        return mappings.get(theme);

    }

}
