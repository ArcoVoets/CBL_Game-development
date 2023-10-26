package panels;

import data.Action;
import interfaces.*;
import java.awt.*;
import javax.swing.*;

public class ButtonsPanel extends JPanel implements Panel {
    Creature creature;

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

        for (Action action : actions) {
            JButton button = new JButton(action.name);
            button.addActionListener(e -> action.execute());
            add(button);
        }

        setBackground(Color.GREEN);
    }

    public void update() {
    }
}
