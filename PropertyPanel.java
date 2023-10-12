import java.awt.*;
import javax.swing.*;

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
            progressBars[i].setValue(property.value);
            progressBars[i].setString(String.format("%d/%d", property.value, property.maxValue));
            colorProgressBar(property, progressBars[i], colorScheme);
        }
    }

    /**
     * Colors the progressBar based on the value.
     * 
     * @param property The property to color the progressBar for
     * @param progressBar The progressBar to color
     */
    void colorProgressBar(Property property, JProgressBar progressBar, ColorScheme colorScheme) {
        Color color = colorScheme.getColor(100 * property.value / property.maxValue);
        progressBar.setForeground(color);
    }
}
