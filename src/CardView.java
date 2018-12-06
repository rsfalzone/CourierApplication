import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardView extends JComponent {
    ArrayList<Canvas> cards = new ArrayList<>();
    boolean cardmode = false;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cardmode) {
            for (Canvas c: cards) {
                c.paintComponent(g);
            }
        }


    }
}
