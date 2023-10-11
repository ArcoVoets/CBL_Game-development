import java.awt.*;
import javax.swing.*;

public class EnvironmentPanel extends JPanel implements Panel {
    public EnvironmentPanel() {
    }

    public void Draw(int width, int height) {
        setSize(width, height);
        removeAll();

        setBackground(Color.PINK);
    }
}
