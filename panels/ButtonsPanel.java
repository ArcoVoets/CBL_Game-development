package panels;

import java.awt.BorderLayout;
import javax.swing.*;

public class ButtonsPanel extends JPanel {
    JButton[] buttons;

    public void hide(JFrame parentPanel) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        parentPanel.remove(this);
    }

    public void show(JFrame parentPanel) {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
        parentPanel.add(this, BorderLayout.SOUTH);
    }
}
