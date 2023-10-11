import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Main class of CBL game.
 */
class Main {
    World world = new World();

    /**
     * Sets up the screen with the frames.
     */
    void setupScreen() {
        JFrame screenFrame = new JFrame("Game");
        screenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenFrame.setMinimumSize(new Dimension(800, 600));
        screenFrame.setUndecorated(true);

        JPanel buttonsPanel = new ButtonsPanel();
        buttonsPanel.setPreferredSize(new Dimension(800, 50));
        screenFrame.add(buttonsPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        JPanel environmentPanel = new EnvironmentPanel();
        environmentPanel.setPreferredSize(new Dimension(150, 300));
        rightPanel.add(environmentPanel, BorderLayout.NORTH);

        // used for testing
        Property property = new Property("Hello", 5, 20);
        Property property2 = new Property("Hello2", 10, 20);
        Property[] properties = { property, property2 };
        PropertyContainer propertyContainer = new PropertyContainer(properties);

        JPanel codesPanel = new CodesPanel(propertyContainer);
        codesPanel.setPreferredSize(new Dimension(300, 300));
        rightPanel.add(codesPanel, BorderLayout.CENTER);

        screenFrame.add(rightPanel, BorderLayout.EAST);

        JPanel worldPanel = new WorldPanel();
        worldPanel.setPreferredSize(new Dimension(500, 300));
        screenFrame.add(worldPanel, BorderLayout.CENTER);

        screenFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main().setupScreen();
    }
}
