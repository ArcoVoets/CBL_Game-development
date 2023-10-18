import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarPanel extends JPanel implements Panel {
    ColorScheme colorScheme;
    Color backgroundColor;
    ProgressBarDataProvider dataProvider;
    JProgressBar[] progressBars;

    /**
     * Constructor.
     * 
     */
    public ProgressBarPanel(ProgressBarDataProvider dataProvider,
        ColorScheme colorScheme, Color backgroundColor) {
        this.dataProvider = dataProvider;
        this.colorScheme = colorScheme;
        this.backgroundColor = backgroundColor;
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
        setBackground(backgroundColor);

        ProgressBarData[] data = dataProvider.getProgressBarData();

        int numStats = data.length;
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
            ProgressBarData property = data[i];

            // create and show key
            JLabel label = new JLabel(property.key, SwingConstants.LEFT);
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
                .setMaximum(property.max - property.min);
            progressBars[i]
                .setValue(property.value - property.min);
            if (property.unit == null) {
                progressBars[i].setString(
                    String.format("%d/%d", property.value,
                        property.max));
            } else {
                progressBars[i].setString(
                    String.format("%d%s", property.value, property.unit));
            }
            colorProgressBar(property, progressBars[i], colorScheme);

            gridPanel.add(progressBars[i]);
        }
        this.add(gridPanel);
    }

    /**
     * Updates the codes in the PropertyContainer.
     * 
     */
    public void update() {
        ProgressBarData[] data = dataProvider.getProgressBarData();

        for (int i = 0; i < data.length; i++) {
            ProgressBarData property = data[i];
            progressBars[i]
                .setValue((property.value - property.min));
            if (property.unit == null) {
                progressBars[i].setString(
                    String.format("%d/%d", property.value,
                        property.max));
            } else {
                progressBars[i].setString(
                    String.format("%d%s", property.value,
                        property.unit));
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
    void colorProgressBar(ProgressBarData property, JProgressBar progressBar,
        ColorScheme colorScheme) {
        Color color = colorScheme
            .getColor(
                100 * (property.value - property.min)
                    / (property.max - property.min));
        progressBar.setForeground(color);
        progressBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.black;
            }

            protected Color getSelectionForeground() {
                if ((color.getRed() * 3 + color.getGreen() * 2
                    + color.getBlue() * 1)
                    / 6 < 80) {
                    return Color.white;
                } else {
                    return Color.black;
                }
            }
        });
    }
}
