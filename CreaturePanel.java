import java.awt.*;
import javax.swing.*;

class CreaturePanel extends JPanel implements Panel {
    enum CreatureLayout {
        PLAYER, WORLD
    }

    Creature creature;
    SpritePanel spritePanel;
    ProgressBarPanel statsPanel;
    CreatureLayout layout;
    ColorScheme colorScheme;
    UpdateCallback updateCallback;

    /**
     * Constructor.
     * 
     * @param creature Creature to show
     * @param layout Layout of the panel
     */
    public CreaturePanel(Creature creature, CreatureLayout layout,
        ColorScheme colorScheme, UpdateCallback updateCallback) {
        super(new BorderLayout());
        this.creature = creature;
        this.layout = layout;
        this.colorScheme = colorScheme;
        this.updateCallback = updateCallback;
    }

    /**
     * Draws the sprite and stats.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.WHITE);

        int spriteWidth;
        int spriteHeight;
        int statsWidth;
        int statsHeight;

        spritePanel = new SpritePanel("./robot-idle.gif");
        if (layout == CreatureLayout.PLAYER) {
            add(spritePanel, BorderLayout.EAST);
            spriteWidth = height;
            spriteHeight = height;
            statsWidth = width - spriteWidth;
            statsHeight = height;
        } else {
            add(spritePanel, BorderLayout.NORTH);
            spriteWidth = width;
            spriteHeight = width;
            statsWidth = width;
            statsHeight = height - spriteHeight;
        }
        spritePanel.draw(spriteWidth, spriteHeight);

        statsPanel = new ProgressBarPanel(creature.statsContainer, colorScheme,
            Color.ORANGE);
        add(statsPanel, BorderLayout.CENTER);
        statsPanel.draw(statsWidth, statsHeight);

        if (layout == CreatureLayout.WORLD) {
            addMouseListener(new MouseClickListener(() -> {
                Actions.selectedCreature = creature;
                updateCallback.Callback();
            }));
        }
    }

    public void update() {
        statsPanel.update();
        if (creature == Actions.selectedCreature) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(Color.WHITE);
        }
    }
}
