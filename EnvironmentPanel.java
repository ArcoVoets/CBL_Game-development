import java.awt.*;
import javax.swing.*;

public class EnvironmentPanel extends JPanel implements Panel {
    public EnvironmentPanel() {
    }

    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.PINK);
    }
}
