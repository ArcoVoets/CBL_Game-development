import java.awt.*;

/**
 * ColorScheme.
 */
class ColorScheme {
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
            if (color.range.contains(percentage))
                return color.color;
        }
        return defaultColor;
    }
}

class ColorRange {
    public Range range;
    public Color color;

    public ColorRange(Range range, Color color) {
        this.range = range;
        this.color = color;
    }

    public ColorRange(int min, int max, Color color) {
        this(new Range(min, max), color);
    }

    public ColorRange(int min, int max, int r, int g, int b, int a) {
        this(new Range(min, max), new Color(r, g, b, a));
    }
}
