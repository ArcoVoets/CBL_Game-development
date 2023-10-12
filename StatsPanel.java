import java.awt.*;
import javax.swing.*;

/**
 * Statspanel.
 */
public class StatsPanel extends JPanel implements Panel {
    PropertyContainer statsContainer;

    /**
     * Constructor.
     * 
     * @param statsContainer
     *            The stats container.
     */
    public StatsPanel(PropertyContainer statsContainer) {
        super(new BorderLayout());
        this.statsContainer = statsContainer;
    }

    /**
     * Draws the panel.
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.ORANGE);
    }
}
