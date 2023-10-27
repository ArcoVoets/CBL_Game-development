import java.awt.*;
import javax.swing.*;

import data.*;
import panels.*;

/**
 * Main class of CBL game.
 */
class Main {
    data.World world;
    Environment environment = new Environment();

    ProgressBarPanel codesPanel;
    WorldPanel worldPanel;
    ButtonsPanel buttonsPanel;
    ProgressBarPanel environmentPanel;

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

    void doWorldCreaturesActions() {
        for (data.Creature creature : world.getWorldCreatures()) {
            if (creature.isDead()) {
                continue;
            }
            creature.getActionsContainer().selectActionCreature(creature);
            updateScreen();
            creature.runAction();
            updateScreen();
        }
        Actions.actionCreature = null;
        Actions.selectedCreature = null;
        updateScreen();
    }

    /**
     * Creates a player creature.
     * 
     * @return The player creature
     */
    data.Creature createPlayerCreature() {
        return new data.Creature(
            new data.Actions(new data.Action[] {
                new EatAction(() -> {
                    updateScreen();
                    doWorldCreaturesActions();
                }),
                new PairAction(() -> {
                    updateScreen();
                    doWorldCreaturesActions();
                }),
            }), environment);

    }

    /**
     * Creates a world creature.
     * 
     * @return The world creature
     */
    data.Creature createWorldCreature() {
        return new data.Creature(
            new data.Actions(new data.Action[] {
                new EatAction(this::updateScreen),
                new PairAction(this::updateScreen)
            }),
            environment,
            new data.CreatureActionRunner());
    }

    /**
     * Sets up the world with creatures.
     */
    void setupWorld() {
        data.Creature playerCreature = createPlayerCreature();
        data.Creature[] worldCreatures = new data.Creature[] {
            createWorldCreature(), createWorldCreature(), createWorldCreature(),
            createWorldCreature(), createWorldCreature()
        };

        world = new data.World(playerCreature, worldCreatures);
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
        buttonsPanel = new ButtonsPanel(world.getPlayerCreature());
        screenFrame.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.draw(screenWidth, buttonsPanelHeight);

        int rightPanelWidth = screenWidth / 5;
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        int environmentPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        environmentPanel = new ProgressBarPanel(
            environment,
            environmentStatsColorScheme, Color.PINK);
        rightPanel.add(environmentPanel, BorderLayout.NORTH);
        environmentPanel.draw(rightPanelWidth, environmentPanelHeight);

        int codesPanelHeight = screenHeight / 2 - buttonsPanelHeight / 2;
        codesPanel = new ProgressBarPanel(
            world.getPlayerCreature().getCodesContainer(),
            codesColorScheme, Color.GREEN);

        rightPanel.add(codesPanel, BorderLayout.CENTER);
        codesPanel.draw(rightPanelWidth, codesPanelHeight);

        screenFrame.add(rightPanel, BorderLayout.EAST);

        worldPanel = new WorldPanel(world, statsColorScheme,
            this::updateScreen);
        screenFrame.add(worldPanel, BorderLayout.CENTER);
        worldPanel.draw(screenWidth - rightPanelWidth,
            screenHeight - buttonsPanelHeight);

        screenFrame.setVisible(true);
    }

    void updateScreen() {
        codesPanel.update();
        worldPanel.update();
        environmentPanel.update();
        buttonsPanel.update();
    }

    void redrawWorld() {
        Dimension dimension = worldPanel.getPreferredSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        worldPanel.draw(width, height);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setupWorld();
        main.setupScreen();
    }
}
