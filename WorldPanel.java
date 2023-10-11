import java.awt.*;
import javax.swing.*;

public class WorldPanel extends JPanel implements Panel {
    World world;
    CreaturePanel playerCreaturePanel;
    CreaturePanel[] worldCreaturePanels;

    public WorldPanel(World world) {
        super(new BorderLayout());
        this.world = world;
    }

    public void Draw(int width, int height) {
        setSize(width, height);
        removeAll();

        setBackground(Color.GRAY);

        playerCreaturePanel = new CreaturePanel(world.playerCreature, CreaturePanel.CreatureLayout.HORIZONTAL);
        playerCreaturePanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));
        add(playerCreaturePanel, BorderLayout.SOUTH);
        playerCreaturePanel.Draw(width, height / 2);

        int worldCreatureWidth = width / world.worldCreatures.length;
        worldCreaturePanels = new CreaturePanel[world.worldCreatures.length];
        for (int i = 0; i < world.worldCreatures.length; i++) {
            worldCreaturePanels[i] = new CreaturePanel(world.worldCreatures[i], CreaturePanel.CreatureLayout.VERTICAL);
            worldCreaturePanels[i].setPreferredSize(new Dimension(100, 100));
            add(worldCreaturePanels[i], BorderLayout.CENTER);
            worldCreaturePanels[i].Draw(worldCreatureWidth, height / 2);
        }
    }
}
