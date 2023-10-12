import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * CodesPanel.
 * Displays the codes in the PropertyContainer.
 */
class CodesPanel extends JPanel {
    JProgressBar[] progressBars;

    /**
     * Constructor.
     * 
     * @param propertyContainer
     *            PropertyContainer to show
     */
    public CodesPanel(PropertyContainer propertyContainer) {
        setBackground(Color.BLACK);
        int numProperties = propertyContainer.properties.length;
        progressBars = new JProgressBar[numProperties];
        for (int i = 0; i < numProperties; i++) {
            Property property = propertyContainer.properties[i];

            // create progresbars
            progressBars[i] = new JProgressBar();
            progressBars[i].setUI(new BasicProgressBarUI());
            progressBars[i].setBackground(Color.WHITE);
            progressBars[i].setForeground(Color.BLUE);
            progressBars[i].setBorder(BorderFactory.createEmptyBorder());
            progressBars[i].setString(String.format("%d/%d", property.value, property.maxValue));
            progressBars[i].setStringPainted(true);
        }
    }

    /**
     * Draws the progressbars.
     * 
     * @param propertyContainer
     *            Porpertycontainer to show
     */
    public void draw(int width, int height, PropertyContainer propertyContainer) {
        setPreferredSize(new Dimension(width, height));
        removeAll();
        setBackground(Color.BLACK);

        int numProperties = propertyContainer.properties.length;

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

            // show key
            JLabel label = new JLabel(property.key, SwingConstants.LEFT);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setFont(font);
            label.setForeground(Color.BLUE);
            label.setBackground(getBackground());
            pane.add(label);

            progressBars[i].setMaximum(property.maxValue);
            progressBars[i].setValue(property.value);

            progressBars[i].setString(String.format("%d/%d", property.value, property.maxValue));

            pane.add(progressBars[i]);
        }
        this.add(pane);

    }

    /**
     * Updates the codes in the PropertyContainer.
     * 
     * @param propertyContainer
     *            PropertyContainer to display codes in
     */
    public void updateCodes(PropertyContainer propertyContainer) {
        for (int i = 0; i < propertyContainer.properties.length; i++) {
            Property property = propertyContainer.properties[i];
            progressBars[i].setValue(property.value);
            progressBars[i].setString(String.format("%d/%d", property.value, property.maxValue));
        }
    }
}
