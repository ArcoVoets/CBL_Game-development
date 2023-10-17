import java.awt.*;
import javax.swing.*;

/**
 * Main class of CBL game.
 */
class Main {
    World world = new World(new Creature(), new Creature[] {
        new Creature(), new Creature(), new Creature(), new Creature(),
        new Creature()
    });

    ColorScheme codesColorScheme = new ColorScheme(new ColorRange[] {
        new ColorRange(0, 100, Color.BLUE)
    }, Color.WHITE);
    ColorScheme statsColorScheme = new ColorScheme(new ColorRange[] {
        new ColorRange(0, 19, Color.RED),
        new ColorRange(20, 39, Color.ORANGE),
        new ColorRange(40, 89, Color.YELLOW),
        new ColorRange(90, 100, Color.GREEN)
    }, Color.WHITE);
    ColorScheme environmentStatsColorScheme = new ColorScheme(new ColorRange[] {
        new ColorRange(new Range(0, 19), Color.BLUE),
        new ColorRange(new Range(20, 59), Color.GREEN),
        new ColorRange(new Range(60, 79), Color.YELLOW),
        new ColorRange(new Range(80, 89), Color.ORANGE),
        new ColorRange(new Range(90, 100), Color.RED)
    }, Color.WHITE);

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

        // used for testing
        Property temperature = new Property("Temperature", 20, -20, 100, "°C");
        Property[] environmentProperties = {
            temperature };
        PropertyContainer environmentPropertyContainer = new PropertyContainer(
            environmentProperties);
        // end used for testing

        int environmentPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        EnvironmentPanel environmentPanel = new EnvironmentPanel(
            environmentPropertyContainer,
            environmentStatsColorScheme);
        rightPanel.add(environmentPanel, BorderLayout.NORTH);
        environmentPanel.draw(rightPanelWidth, environmentPanelHeight);

        // used for testing
        Property property = new Property("Hello", 5, 20);
        Property property2 = new Property("Hello2", 10, 20);
        Property property3 = new Property("Hello3", 70, 100);
        Property property4 = new Property("Hello4", 10, 20);
        Property[] properties = {
            property, property2, property3, property4 };
        PropertyContainer propertyContainer = new PropertyContainer(properties);
        // end used for testing

        int codesPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        CodesPanel codesPanel = new CodesPanel(propertyContainer,
            codesColorScheme);

        rightPanel.add(codesPanel, BorderLayout.CENTER);
        codesPanel.draw(rightPanelWidth, codesPanelHeight);

        screenFrame.add(rightPanel, BorderLayout.EAST);

        WorldPanel worldPanel = new WorldPanel(world, statsColorScheme);
        screenFrame.add(worldPanel, BorderLayout.CENTER);
        worldPanel.draw(screenWidth - rightPanelWidth,
            screenHeight - buttonsPanelHeight);

        screenFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main().setupScreen();
    }
}
