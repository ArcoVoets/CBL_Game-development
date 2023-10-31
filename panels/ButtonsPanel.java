package panels;

import interfaces.Creature;
import javax.swing.*;

/**
 * Panel that displays the buttons corresponding to the actions of the creature.
 */
public abstract class ButtonsPanel extends JPanel implements Panel {
    Creature creature;
    JButton[] buttons;

    /**
     * Hides the buttons.
     */
    public void hide() {
        for (JButton button : buttons) {
            button.setEnabled(false);
            button.setVisible(false);
        }
    }

    /**
     * Shows the buttons.
     */
    public void show() {
        for (JButton button : buttons) {
            button.setEnabled(true);
            button.setVisible(true);
        }
    }
}
