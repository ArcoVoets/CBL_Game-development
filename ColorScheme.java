import java.awt.*;

/**
 * ColorScheme.
 */
public class ColorScheme {
    Range[] ranges;
    Color[] colors;

    /**
     * Constructor.
     * 
     * @param ranges The ranges to use
     * @param colors The colors to use, the last color is used for values outside
     *            the ranges
     */
    public ColorScheme(Range[] ranges, Color[] colors) {
        if (colors.length != ranges.length + 1) {
            throw new IllegalArgumentException(
                "The number of colors must be one more than the number of ranges");
        }
        this.colors = colors;
        this.ranges = ranges;
    }

    /**
     * Gets the color for the percentage.
     * 
     * @param percentage The percentage to get the color for
     * @return The color for the percentage
     */
    Color getColor(int percentage) {
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i].contains(percentage)) {
                return colors[i];
            }
        }
        return colors[colors.length];
    }
}
