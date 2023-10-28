package panels;

import interfaces.UpdateCallback;
import java.awt.*;
import javax.swing.*;

/**
 * Panel that displays the buttons to play world creature's actions.
 */
public class CreaturesActionsButtonsPanel extends ButtonsPanel {
    UpdateCallback runNextWorldCreaturesAction;
    UpdateCallback runAllWorldCreaturesActions;

    /**
     * Constructor.
     * 
     * @param runNextWorldCreaturesAction Callback to run next world creature's
     *            action
     * @param runAllWorldCreaturesActions Callback to run all world creature's
     *            actions
     */
    public CreaturesActionsButtonsPanel(
        UpdateCallback runNextWorldCreaturesAction,
        UpdateCallback runAllWorldCreaturesActions) {
        this.runNextWorldCreaturesAction = runNextWorldCreaturesAction;
        this.runAllWorldCreaturesActions = runAllWorldCreaturesActions;
    }

    /**
     * Draws the buttons.
     * 
     * @param width Width of the panel
     * @param height Height of the panel
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        int numButtons = 2;
        setLayout(new GridLayout(1, numButtons));
        removeAll();

        setBackground(Color.WHITE);

        buttons = new JButton[numButtons];

        buttons[0] = new JButton("Execute next world creature action");
        buttons[0]
            .addActionListener(e -> runNextWorldCreaturesAction.callback());
        add(buttons[0]);
        buttons[1] = new JButton("Execute all world creature actions");
        buttons[1]
            .addActionListener(e -> runAllWorldCreaturesActions.callback());
        add(buttons[1]);
    }

    @Override
    public void update() {
        // Do nothing
    }
}
