package panels;

import interfaces.Creature;
import interfaces.UpdateCallback;
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

        if (creature.isDead()) {
            return;
        }

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

        statsPanel = new ProgressBarPanel(creature.getStatsContainer(),
            colorScheme,
            Color.WHITE);
        add(statsPanel, BorderLayout.CENTER);
        statsPanel.draw(statsWidth, statsHeight);

        if (layout == CreatureLayout.WORLD) {
            addMouseListener(new MouseClickListener(() -> {
                creature.getActionsContainer().selectCreature();
                updateCallback.callback();
            }));
        }
    }

    /**
     * Updates the panel after changes in the game data.
     */
    public void update() {
        if (creature.isDead()) {
            removeAll();
            setBackground(Color.WHITE);
            return;
        }
        statsPanel.update();
        if (creature.getActionsContainer().isSelectedCreature()) {
            setBackground(Color.YELLOW);
        } else if (creature.getActionsContainer().isActionCreature()) {
            setBackground(Color.GREEN);
        } else {
            setBackground(Color.WHITE);
        }
    }
}
