import java.awt.*;
import javax.swing.*;

public class StatsPanel extends JPanel implements Panel {
    PropertyContainer statsContainer;

    public StatsPanel(PropertyContainer statsContainer) {
        super(new BorderLayout());
        this.statsContainer = statsContainer;
    }

    public void Draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.ORANGE);
    }
}
