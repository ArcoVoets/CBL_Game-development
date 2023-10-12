import java.awt.*;
import javax.swing.*;

class WorldPanel extends JPanel implements Panel {
    World world;
    CreaturePanel playerCreaturePanel;
    CreaturePanel[] worldCreaturePanels;

    public WorldPanel(World world) {
        super(new BorderLayout());
        this.world = world;
    }

    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.GRAY);

        playerCreaturePanel = new CreaturePanel(world.playerCreature, CreaturePanel.CreatureLayout.HORIZONTAL);
        add(playerCreaturePanel, BorderLayout.SOUTH);
        playerCreaturePanel.draw(width, height / 2);

        JPanel worldCreatureContainerPanel = new JPanel(new GridLayout(1, world.worldCreatures.length));
        worldCreatureContainerPanel.setPreferredSize(new Dimension(width, height / 2));
        ;
        add(worldCreatureContainerPanel, BorderLayout.CENTER);

        int worldCreatureWidth = width / world.worldCreatures.length;
        worldCreaturePanels = new CreaturePanel[world.worldCreatures.length];
        for (int i = 0; i < world.worldCreatures.length; i++) {
            worldCreaturePanels[i] = new CreaturePanel(world.worldCreatures[i], CreaturePanel.CreatureLayout.VERTICAL);
            worldCreatureContainerPanel.add(worldCreaturePanels[i]);
            worldCreaturePanels[i].draw(worldCreatureWidth, height / 2);
        }
    }
}
