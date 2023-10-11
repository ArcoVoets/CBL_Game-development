import java.awt.*;
import javax.swing.*;

public class CodesPanel extends JPanel implements Panel {
    public CodesPanel() {
    }

    public void Draw(int width, int height) {
        setSize(width, height);
        removeAll();

        setBackground(Color.BLUE);
    }
}
