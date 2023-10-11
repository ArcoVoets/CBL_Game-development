import java.awt.*;
import javax.swing.*;

/**
 * CodesPanel.
 */
public class CodesPanel extends JPanel {
    JProgressBar[] progressBars;

    /**
     * Constructor.
     * 
     * @param propertyContainer Foo
     */
    public CodesPanel(PropertyContainer propertyContainer) {
        setBackground(Color.BLUE);

        progressBars = new JProgressBar[propertyContainer.properties.length];

        Dimension panelSize = getSize();
        int panelWidth = (int) panelSize.getWidth();
        int progressBarLengthPercentage = 80;

        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;

        for (int i = 0; i < propertyContainer.properties.length; i++) {
            JProgressBar progressBar = progressBars[i];
            Property property = propertyContainer.properties[i];

            // show key
            JLabel label = new JLabel(property.key);
            c.gridy = 2 * i;
            pane.add(label, c);

            progressBar = new JProgressBar();
            progressBar.setSize(
                    new Dimension((panelWidth * progressBarLengthPercentage) / 100, 10));
            progressBar.setMaximum(property.maxValue);
            progressBar.setValue(property.value);

            c.gridy = 2 * i + 1;
            pane.add(progressBar, c);
        }
        this.add(pane);
    }

    /**
     * Displays the codes in the PropertyContainer.
     * 
     * @param propertyContainer PropertyContainer to display codes in
     */
    public void displayCodes(PropertyContainer propertyContainer) {
        for (int i = 0; i < propertyContainer.properties.length; i++) {
            progressBars[i].setValue(propertyContainer.properties[i].value);
        }
    }
}
