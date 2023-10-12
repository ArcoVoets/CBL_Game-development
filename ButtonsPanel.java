import java.awt.*;
import javax.swing.*;

class ButtonsPanel extends JPanel implements Panel {
    public ButtonsPanel() {
    }

    /**
     * Draws the buttons.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.GREEN);
    }
}
