import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WorldPanel extends JPanel {
    CreaturePanel playerCreaturePanel;
    CreaturePanel[] worldCreaturePanels;

    public WorldPanel() {
        setBackground(Color.WHITE);
    }
}
