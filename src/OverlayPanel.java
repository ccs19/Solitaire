import javax.swing.*;
import java.awt.*;

/**
 * Created by christopher on 11/7/14.
 */
public class OverlayPanel extends JPanel {
    private ImageIcon ace = new ImageIcon("textures/2_of_clubs.png");
    private ImageIcon two = new ImageIcon("cards/2c.gif");
    private ImageIcon three = new ImageIcon("cards/3c.gif");
    //private ImageIcon three = new Card('C', 3).image();
    private int xpos = 10;
    private int ypos = 10;
    private int offset = 20;

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        ace.paintIcon(this, page, xpos, ypos);
        two.paintIcon(this, page, xpos, ypos+offset);
        three.paintIcon(this, page, xpos, ypos+offset*2);
    }

}
