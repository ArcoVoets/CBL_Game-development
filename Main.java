import java.awt.*;
import javax.swing.*;

/**
 * Main class of CBL game.
 */
class Main {
    World world;

    CodesPanel codesPanel;
    WorldPanel worldPanel;

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

    Creature createPlayerCreature() {
        return new Creature(
            new PropertyContainer(
                new Property[] {
                    new Property("speed", 5, 20),
                    new Property("damage", 10, 20),
                    new Property("max energy", 70, 100),
                    new Property("heat resistance", 10, 20),
                }),
            new PropertyContainer(
                new Property[] {
                    new Property("energy", 10, 10)
                }),
            new Actions(new Action[] {
                new EatAction(),
                new PairAction()
            }, this::updateScreen));
    }

    Creature createWorldCreature() {
        return new Creature(
            new PropertyContainer(
                new Property[] {
                    new Property("speed", 5, 20),
                    new Property("damage", 10, 20),
                    new Property("max energy", 70, 100),
                    new Property("heat resistance", 10, 20),
                }),
            new PropertyContainer(
                new Property[] {
                    new Property("energy", 10, 10)
                }),
            new Actions(new Action[] {
                new EatAction(),
                new PairAction()
            }, this::updateScreen));
    }

    /**
     * Sets up the world with creatures.
     */
    void setupWorld() {
        Creature playerCreature = createPlayerCreature();
        Creature[] worldCreatures = new Creature[] {
            createWorldCreature(), createWorldCreature(), createWorldCreature(),
            createWorldCreature(), createWorldCreature()
        };

        world = new World(playerCreature, worldCreatures);
    }

    /**
     * Sets up the screen with the frames.
     */
    void setupScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        JFrame screenFrame = new JFrame();
        screenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenFrame.setUndecorated(true);

        int buttonsPanelHeight = screenHeight / 10;
        ButtonsPanel buttonsPanel = new ButtonsPanel(world.playerCreature);
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

        int codesPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        codesPanel = new CodesPanel(world.playerCreature.codesContainer,
            codesColorScheme);

        rightPanel.add(codesPanel, BorderLayout.CENTER);
        codesPanel.draw(rightPanelWidth, codesPanelHeight);

        screenFrame.add(rightPanel, BorderLayout.EAST);

        worldPanel = new WorldPanel(world, statsColorScheme);
        screenFrame.add(worldPanel, BorderLayout.CENTER);
        worldPanel.draw(screenWidth - rightPanelWidth,
            screenHeight - buttonsPanelHeight);

        screenFrame.setVisible(true);
    }

    void updateScreen() {
        codesPanel.update();
        worldPanel.update();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setupWorld();
        main.setupScreen();
    }
}
