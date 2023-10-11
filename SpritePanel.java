import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SpritePanel extends JPanel implements Panel {
    BufferedImage loadedSprite;

    public SpritePanel(String spritePath) {
        super(new BorderLayout());
        try {
            loadedSprite = ImageIO.read(new File(spritePath));
        } catch (IOException ex) {
            System.out.println("Couldn't load sprite at " + spritePath + ": " + ex);
            return;
        }
    }

    public void Draw(int width, int height) {
        setSize(width, height);
        removeAll();

        setBackground(Color.YELLOW);

        if (loadedSprite == null) {
            return;
        }

        Image picture = loadedSprite.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(picture));
        add(picLabel, BorderLayout.CENTER);
    }
}
