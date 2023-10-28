package panels;

import java.awt.*;

/**
 * A range of colors used for coloring the progress bars.
 */
public class ColorRange {
    public Range range;
    public Color color;

    /**
     * Constructor.
     * 
     * @param range The range (in percentages) for which the color should be
     *            showed
     * @param color The color to show
     */
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
