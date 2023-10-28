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

    public ButtonsPanel(Creature creature) {
        this.creature = creature;
    }

    /**
     * Draws the buttons.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        Action[] actions = creature.getActionsContainer().getActions();

        setLayout(
            new GridLayout(1,
                actions.length));
        removeAll();

        setBackground(Color.WHITE);

        setBackground(Color.WHITE);

        buttons = new JButton[actions.length];

        for (int i = 0; i < actions.length; i++) {
            Action action = actions[i];
            buttons[i] = new JButton(String.format("%s (Energy cost: %d)",
                action.name, action.calculateEnergyCost()));
            buttons[i].addActionListener(e -> action.execute());
            add(buttons[i]);
        }
    }

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
    }
}
