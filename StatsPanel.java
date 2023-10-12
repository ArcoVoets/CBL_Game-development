import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * StatsPanel.
 */
class StatsPanel extends PropertyPanel {
    boolean[] showProperty;

    /**
     * Constructor.
     * 
     * @param propertyContainer The stats container.
     */
    public StatsPanel(PropertyContainer propertyContainer) {
        this.propertyContainer = propertyContainer;
    }

    /**
     * Draws the progressBars.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    @Override
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();
        setBackground(Color.ORANGE);

        int numProperties = propertyContainer.properties.length;

        if (showProperty == null) {
            showProperty = new boolean[numProperties];
            Arrays.fill(showProperty, true);
        }

        int numShowedProperties = elementFrequency(showProperty, true);
        progressBars = new JProgressBar[numProperties];

        JPanel pane = new JPanel(new GridLayout(numShowedProperties * 2, 1));
        int rowHeight = getPreferredSize().height / (numShowedProperties * 2);
        int columnWidth = getPreferredSize().width;
        pane.setBackground(getBackground());
        pane.setPreferredSize(getPreferredSize());
        pane.setBorder(
            BorderFactory.createEmptyBorder(
                0,
                columnWidth / 10,
                rowHeight / 2,
                columnWidth / 10));

        Font font = new Font("MS Sans Serif", Font.BOLD, 25);

        for (int i = 0; i < numProperties; i++) {
            if (!showProperty[i]) {
                continue;
            }
            Property property = propertyContainer.properties[i];

            // create and show key
            JLabel label = new JLabel(property.key, SwingConstants.LEFT);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setFont(font);
            label.setForeground(Color.BLUE);
            label.setBackground(getBackground());
            pane.add(label);

            // create and show progressBar
            progressBars[i] = new JProgressBar();
            progressBars[i].setUI(new BasicProgressBarUI());
            progressBars[i].setBackground(Color.WHITE);
            progressBars[i].setForeground(Color.BLUE);
            progressBars[i].setBorder(BorderFactory.createEmptyBorder());
            progressBars[i].setStringPainted(true);
            progressBars[i].setMaximum(property.maxValue);
            progressBars[i].setValue(property.value);
            progressBars[i].setString(String.format("%d/%d", property.value, property.maxValue));
            colorProgressBar(property, progressBars[i]);

            pane.add(progressBars[i]);
        }
        this.add(pane);
    }

    /**
     * Colors the progressBar based on the value.
     * 
     * @param property The property to color the progressBar for
     * @param progressBar The progressBar to color
     */
    private void colorProgressBar(Property property, JProgressBar progressBar) {
        if (property.value >= 0.9 * property.maxValue) {
            progressBar.setForeground(Color.GREEN);
        } else if (property.value >= 0.3 * property.maxValue) {
            progressBar.setForeground(Color.ORANGE);
        } else {
            progressBar.setForeground(Color.RED);
        }
    }

    /**
     * Changes the filter.
     * 
     * @param newFilter The new filter
     */
    void changeFilter(String[] newFilter) {
        for (int i = 0; i < progressBars.length; i++) {
            if (arrayContains(newFilter, propertyContainer.properties[i].key)) {
                showProperty[i] = true;
            } else {
                showProperty[i] = false;
            }
        }
        draw(this.getPreferredSize().width, this.getPreferredSize().height);
    }

    /**
     * Checks if array contains value.
     * 
     * @param array The array to check
     * @param value The value to check for in the array
     */
    private <T> boolean arrayContains(
        T[] array, T value) {
        for (T element : array) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the frequency of value in array.
     * 
     * @param <T> The type of the array
     * @param showProperty The array to check
     * @param b The value to count the frequency of in the array
     * @return The frequency of value in array
     */
    private int elementFrequency(boolean[] showProperty, boolean b) {
        int frequency = 0;
        for (boolean element : showProperty) {
            if (element == b) {
                frequency++;
            }
        }
        return frequency;
    }
}
