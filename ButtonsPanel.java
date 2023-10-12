import java.awt.*;
import javax.swing.*;

class ButtonsPanel extends JPanel implements Panel {
    public ButtonsPanel() {
    }

    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.GREEN);
    }
}
