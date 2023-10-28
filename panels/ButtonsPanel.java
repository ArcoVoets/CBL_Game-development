package panels;

import interfaces.Creature;
import java.awt.*;
import javax.swing.*;

/**
 * Panel that displays the buttons corresponding to the actions of the creature.
 */
public abstract class ButtonsPanel extends JPanel implements Panel {
    Creature creature;
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
