import java.awt.*;
import javax.swing.*;

public class ButtonsPanel extends JPanel implements Panel {
    public ButtonsPanel() {
    }

    public void Draw(int width, int height) {
        setSize(width, height);
        removeAll();

        setBackground(Color.GREEN);
    }
}
