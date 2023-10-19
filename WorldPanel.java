import java.awt.*;
import javax.swing.*;

class WorldPanel extends JPanel implements Panel {
    World world;
    CreaturePanel playerCreaturePanel;
    CreaturePanel[] worldCreaturePanels;
    ColorScheme statsColorScheme;
    UpdateCallback updateCallback;

    /**
     * Constructor.
     * 
     * @param world World to show
     */
    public WorldPanel(World world, ColorScheme statsColorScheme,
        UpdateCallback updateCallback) {
        super(new BorderLayout());
        this.world = world;
        this.statsColorScheme = statsColorScheme;
        this.updateCallback = updateCallback;
    }

    /**
     * Draws the world.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.GRAY);

        playerCreaturePanel = new CreaturePanel(
            world.playerCreature,
            CreaturePanel.CreatureLayout.PLAYER,
            statsColorScheme,
            updateCallback);

        add(playerCreaturePanel, BorderLayout.SOUTH);
        playerCreaturePanel.draw(width, height / 2);

        JPanel worldCreatureContainerPanel = new JPanel(new GridLayout(
            1,
            world.worldCreatures.length));

        worldCreatureContainerPanel
            .setPreferredSize(new Dimension(width, height / 2));

        add(worldCreatureContainerPanel, BorderLayout.CENTER);

        int worldCreatureWidth = width / world.worldCreatures.length;
        worldCreaturePanels = new CreaturePanel[world.worldCreatures.length];
        for (int i = 0; i < world.worldCreatures.length; i++) {
            worldCreaturePanels[i] = new CreaturePanel(
                world.worldCreatures[i],
                CreaturePanel.CreatureLayout.WORLD,
                statsColorScheme, updateCallback);

            worldCreatureContainerPanel.add(worldCreaturePanels[i]);
            worldCreaturePanels[i].draw(worldCreatureWidth, height / 2);
        }
    }

    public void update() {
        playerCreaturePanel.update();
        for (int i = 0; i < worldCreaturePanels.length; i++) {
            worldCreaturePanels[i].update();
        }
    }
}
