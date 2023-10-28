package panels;

import data.Action;
import interfaces.Creature;
import java.awt.*;
import javax.swing.*;

/**
 * Panel that displays the buttons corresponding to the actions of the creature.
 */
public class ButtonsPanel extends JPanel implements Panel {
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

    /**
     * Updates the buttons.
     */
    public void update() {
        Action[] actions = creature.getActionsContainer().getActions();

        for (int i = 0; i < actions.length; i++) {
            Action action = actions[i];
            buttons[i].setText(String.format("%s (Energy cost: %d)",
                action.name, action.calculateEnergyCost()));
        }
        parentPanel.add(this, BorderLayout.SOUTH);
    }
}
