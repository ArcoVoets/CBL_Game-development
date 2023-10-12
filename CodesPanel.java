import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * CodesPanel.
 * Displays the codes in the PropertyContainer.
 */
class CodesPanel extends PropertyPanel {

    /**
     * Constructor.
     * 
     * @param propertyContainer PropertyContainer to show
     */
    public CodesPanel(PropertyContainer propertyContainer, ColorScheme colorScheme) {
        this.propertyContainer = propertyContainer;
        this.colorScheme = colorScheme;
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
        setBackground(Color.BLACK);

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
            colorProgressBar(property, progressBars[i], colorScheme);

            pane.add(progressBars[i]);
        }
        this.add(pane);
    }
}
