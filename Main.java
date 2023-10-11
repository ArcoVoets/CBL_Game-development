import java.awt.*;
import javax.swing.*;

/**
 * Main class of CBL game.
 */
class Main {
    // World world = new World();
    World world = new World(new Creature(), new Creature[] {
            new Creature(), new Creature(), new Creature(), new Creature(), new Creature()
    });

    void setupScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        JFrame screenFrame = new JFrame("Game");
        screenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenFrame.setUndecorated(true);

        int buttonsPanelHeight = screenHeight / 10;
        ButtonsPanel buttonsPanel = new ButtonsPanel();
        screenFrame.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.Draw(screenWidth, buttonsPanelHeight);

        int rightPanelWidth = screenWidth / 5;
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        int environmentPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        EnvironmentPanel environmentPanel = new EnvironmentPanel();
        rightPanel.add(environmentPanel, BorderLayout.NORTH);
        environmentPanel.Draw(rightPanelWidth, environmentPanelHeight);

        int codesPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        CodesPanel codesPanel = new CodesPanel();
        rightPanel.add(codesPanel, BorderLayout.CENTER);
        codesPanel.Draw(rightPanelWidth, codesPanelHeight);

        screenFrame.add(rightPanel, BorderLayout.EAST);

        WorldPanel worldPanel = new WorldPanel(world);
        screenFrame.add(worldPanel, BorderLayout.CENTER);
        worldPanel.Draw(screenWidth - rightPanelWidth, screenHeight - buttonsPanelHeight);

        screenFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main().setupScreen();
    }
}
