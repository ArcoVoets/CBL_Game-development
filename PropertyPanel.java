import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * PropertyPanel.
 * Displays the properties of the selected object.
 */
abstract class PropertyPanel extends JPanel implements Panel {
    JProgressBar[] progressBars;
    PropertyContainer propertyContainer;
    ColorScheme colorScheme;

    /**
     * Draws the progressBars.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public abstract void draw(int width, int height);

    /**
     * Updates the codes in the PropertyContainer.
     * 
     */
    public void update() {
        for (int i = 0; i < propertyContainer.properties.length; i++) {
            Property property = propertyContainer.properties[i];
            progressBars[i]
                .setValue((property.getValue() - property.getMinValue()));
            if (property.getMinValue() == 0) {
                progressBars[i].setString(
                    String.format("%d/%d", property.getValue(),
                        property.getMaxValue()));
            } else {
                progressBars[i].setString(
                    String.format("%d%s", property.getValue(),
                        property.getUnit()));
            }
            colorProgressBar(property, progressBars[i], colorScheme);
        }
    }

    /**
     * Colors the progressBar based on the value.
     * 
     * @param property The property to color the progressBar for
     * @param progressBar The progressBar to color
     */
    void colorProgressBar(Property property, JProgressBar progressBar,
        ColorScheme colorScheme) {
        Color color = colorScheme
            .getColor(
                100 * (property.getValue() - property.getMinValue())
                    / (property.getMaxValue() - property.getMinValue()));
        progressBar.setForeground(color);

        progressBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.BLACK;
            }

            protected Color getSelectionForeground() {
                if ((color.getRed() * 3 + color.getGreen() * 2
                    + color.getBlue() * 1)
                    / 6 < 80) {
                    return Color.WHITE;
                } else {
                    return Color.BLACK;
                }
            }
        });
    }
}
