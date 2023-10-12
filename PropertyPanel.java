import java.awt.*;
import javax.swing.*;

/**
 * PropertyPanel.
 * Displays the properties of the selected object.
 */
abstract class PropertyPanel extends JPanel implements Panel {
    JProgressBar[] progressBars;
    PropertyContainer propertyContainer;

    /**
     * Draws the progressbars.
     * 
     * @param width  width of the panel in pixels
     * @param height height of the panel in pixels
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
        }
    }
}
