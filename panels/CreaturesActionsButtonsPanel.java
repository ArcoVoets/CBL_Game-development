package panels;

import interfaces.*;
import java.awt.*;
import javax.swing.*;

public class CreaturesActionsButtonsPanel extends ButtonsPanel {

    UpdateCallback runNextWorldCreaturesAction;
    UpdateCallback runAllWorldCreaturesActions;

    public CreaturesActionsButtonsPanel(
        UpdateCallback runNextWorldCreaturesAction,
        UpdateCallback runAllWorldCreaturesActions) {
        this.runNextWorldCreaturesAction = runNextWorldCreaturesAction;
        this.runAllWorldCreaturesActions = runAllWorldCreaturesActions;
    }

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

    public void update() {
        return;
    }
}
