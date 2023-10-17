import java.awt.*;
import javax.swing.*;

class ButtonsPanel extends JPanel implements Panel {
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
        setLayout(new GridLayout(1, creature.actionsContainer.actions.length));
        removeAll();

        for (Action action : creature.actionsContainer.actions) {
            JButton button = new JButton(action.name);
            button.addActionListener(e -> action.Execute(creature));
            add(button);
        }

        setBackground(Color.GREEN);
    }
}
