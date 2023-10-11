import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class SpritePanel extends JPanel implements Panel {
    ImageIcon icon;

    public SpritePanel(String spritePath) {
        super(new BorderLayout());

        URL resource = getClass().getResource("robot-idle.gif");
        if (resource == null) {
            System.out.println("Image resource not found: " + spritePath);
            return;
        }
        icon = new ImageIcon(resource);
    }

    public void Draw(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        removeAll();

        setBackground(Color.YELLOW);

        if (icon == null) {
            return;
        }

        icon = scaleIcon(icon, width, height);
        JLabel picLabel = new JLabel(icon);
        add(picLabel, BorderLayout.CENTER);
    }

    private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image new_image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon.setImage(new_image);
        return icon;
    }
}
