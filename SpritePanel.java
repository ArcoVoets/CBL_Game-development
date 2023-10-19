import java.awt.*;
import java.net.URL;
import javax.swing.*;

class SpritePanel extends JPanel implements Panel {
    ImageIcon icon;

    /**
     * Constructor.
     * 
     * @param spritePath Path to the sprite
     */
    public SpritePanel(String spritePath) {
        super(new BorderLayout());

        URL resource = getClass().getResource("robot-idle.gif");
        if (resource == null) {
            System.out.println("Image resource not found: " + spritePath);
            return;
        }
        icon = new ImageIcon(resource);
    }

    /**
     * Draws the sprite.
     * 
     * @param width Width of the panel in pixels
     * @param height Height of the panel in pixels
     */
    public void draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setOpaque(false);

        if (icon == null) {
            return;
        }

        icon = scaleIcon(icon, width, height);
        JLabel picLabel = new JLabel(icon);
        add(picLabel, BorderLayout.CENTER);
    }

    /**
     * Scales the icon to the given width and height.
     * 
     * @param icon Icon to scale
     * @param width Width to scale to
     * @param height Height to scale to
     * @return Scaled icon
     */
    private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height,
            Image.SCALE_DEFAULT);
        icon.setImage(newImage);
        return icon;
    }

    public void update() {
    }
}
