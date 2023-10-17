import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * EnvironmentPanel.
 * Displays the environment-stats by means of progressbars.
 */
class EnvironmentPanel extends PropertyPanel {

    /**
     * Constructor.
     * 
     */
    public EnvironmentPanel(PropertyContainer propertyContainer,
        ColorScheme colorScheme) {
        this.propertyContainer = propertyContainer;
        this.colorScheme = colorScheme;
    }

    /**
     * Draws the environment.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    @Override
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();
        setBackground(Color.PINK);

        int numStats = propertyContainer.properties.length;
        progressBars = new JProgressBar[numStats];

        JPanel gridPanel = new JPanel(new GridLayout(numStats * 2, 1));
        int rowHeight = getPreferredSize().height / (numStats * 2);
        int columnWidth = getPreferredSize().width;
        gridPanel.setBackground(getBackground());
        gridPanel.setPreferredSize(getPreferredSize());
        gridPanel.setBorder(
            BorderFactory.createEmptyBorder(
                0,
                columnWidth / 10,
                rowHeight / 2,
                columnWidth / 10));

        Font font = new Font("MS Sans Serif", Font.BOLD, 25);

        for (int i = 0; i < numStats; i++) {
            Property property = propertyContainer.properties[i];

            // create and show key
            JLabel label = new JLabel(property.getKey(), SwingConstants.LEFT);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setFont(font);
            label.setForeground(Color.BLUE);
            label.setBackground(getBackground());
            gridPanel.add(label);

            // create and show progressBar
            progressBars[i] = new JProgressBar();
            progressBars[i].setUI(new BasicProgressBarUI());
            progressBars[i].setBackground(Color.WHITE);
            progressBars[i].setForeground(Color.BLUE);
            progressBars[i].setBorder(BorderFactory.createEmptyBorder());
            progressBars[i].setStringPainted(true);
            progressBars[i]
                .setMaximum(property.getMaxValue() - property.getMinValue());
            progressBars[i]
                .setValue(property.getValue() - property.getMinValue());
            progressBars[i].setString(
                String.format("%d%s", property.getValue(), property.getUnit()));
            colorProgressBar(property, progressBars[i], colorScheme);

            gridPanel.add(progressBars[i]);
        }
        this.add(gridPanel);
    }
}
