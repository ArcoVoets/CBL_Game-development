import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

class CreaturePanel extends JPanel implements Panel {
    enum CreatureLayout {
        HORIZONTAL, VERTICAL
    }

    Creature creature;
    SpritePanel spritePanel;
    StatsPanel statsPanel;
    CreatureLayout layout;
    ColorScheme colorScheme;

    /**
     * Constructor.
     * 
     * @param creature Creature to show
     * @param layout Layout of the panel
     */
    public CreaturePanel(Creature creature, CreatureLayout layout,
        ColorScheme colorScheme) {
        super(new BorderLayout());
        this.creature = creature;
        this.layout = layout;
        this.colorScheme = colorScheme;
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
        if (layout == CreatureLayout.HORIZONTAL) {
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

        statsPanel = new StatsPanel(creature.statsContainer, colorScheme);
        add(statsPanel, BorderLayout.CENTER);
        statsPanel.draw(statsWidth, statsHeight);

        addMouseListener(new MouseClickListener(() -> {
            Actions.selectedCreature = creature;
        }));
    }

    public void update() {
        statsPanel.update();
    }
}
