import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * StatsPanel.
 */
class StatsPanel extends PropertyPanel {

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
        progressBars = new JProgressBar[numProperties];

        JPanel pane = new JPanel(new GridLayout(numProperties * 2, 1));
        int rowHeight = getPreferredSize().height / (numProperties * 2);
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

            pane.add(progressBars[i]);
        }
        this.add(pane);
    }

    /**
     * Changes the filter.
     * 
     * @param newFilter The new filter
     */
    void changeFilter(String[] newFilter) {
        for (int i = 0; i < progressBars.length; i++) {
            if (arrayContains(newFilter, propertyContainer.properties[i].key)) {
                progressBars[i].setVisible(true);
            } else {
                progressBars[i].setVisible(false);
            }
        }
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
}
