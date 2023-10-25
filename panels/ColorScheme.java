package panels;

import java.awt.*;

/**
 * ColorScheme.
 */
public class ColorScheme {
    ColorRange[] colors;
    Color defaultColor;

    /**
     * Constructor.
     *
     * @param colors The colors to use
     * @param defaultColor The default color
     */
    public ColorScheme(ColorRange[] colors, Color defaultColor) {
        this.colors = colors;
        this.defaultColor = defaultColor;
    }

    /**
     * Constructor.
     * 
     * @param colors The colors to use
     */

    public ColorScheme(ColorRange[] colors) {
        this(colors, Color.BLACK);
    }

    /**
     * Gets the color for the percentage.
     * 
     * @param percentage The percentage to get the color for
     * @return The color for the percentage
     */
    Color getColor(int percentage) {
        for (ColorRange color : colors) {
            if (color.range.contains(percentage)) {
                return color.color;
            }
        }
        return defaultColor;
    }
}
