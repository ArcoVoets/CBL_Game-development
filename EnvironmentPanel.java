import java.awt.*;
import javax.swing.*;

class EnvironmentPanel extends JPanel implements Panel {
    public EnvironmentPanel() {
    }

    /**
     * Draws the environment.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.PINK);
    }
}
