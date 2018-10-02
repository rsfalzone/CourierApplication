import javax.swing.*;
import java.awt.*;

public class UserContent extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.DARK_GRAY);
        for (int i = 80; i < getHeight(); i+=20) {
            g2.drawLine(0, i, getWidth(), i);
        }
        g2.setColor(Color.RED);
        g2.drawLine(80, 0, 80, getHeight());
    }
}
