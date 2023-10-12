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

    /**
     * Sets up the screen with the frames.
     */
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
        buttonsPanel.draw(screenWidth, buttonsPanelHeight);

        int rightPanelWidth = screenWidth / 5;
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        int environmentPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        EnvironmentPanel environmentPanel = new EnvironmentPanel();
        rightPanel.add(environmentPanel, BorderLayout.NORTH);
        environmentPanel.draw(rightPanelWidth, environmentPanelHeight);

        // used for testing
        Property property = new Property("Hello", 5, 20);
        Property property2 = new Property("Hello2", 10, 20);
        Property property3 = new Property("Hello3", 70, 100);
        Property property4 = new Property("Hello4", 10, 20);
        Property[] properties = { property, property2, property3, property4 };
        PropertyContainer propertyContainer = new PropertyContainer(properties);

        int codesPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        CodesPanel codesPanel = new CodesPanel(propertyContainer);

        rightPanel.add(codesPanel, BorderLayout.CENTER);
        codesPanel.draw(rightPanelWidth, codesPanelHeight);

        screenFrame.add(rightPanel, BorderLayout.EAST);

        WorldPanel worldPanel = new WorldPanel(world);
        screenFrame.add(worldPanel, BorderLayout.CENTER);
        worldPanel.draw(screenWidth - rightPanelWidth, screenHeight - buttonsPanelHeight);

        screenFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main().setupScreen();
    }
}
