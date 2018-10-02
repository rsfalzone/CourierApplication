import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserContent extends JComponent {
    ArrayList<Drawn> displayList;

    public UserContent() {
        displayList = new ArrayList();
        displayList.add(new Shape("rectangle", 90, 80, 300, 300 ));
        displayList.add(new Shape("oval", 90, 80, 300, 300 ));
        displayList.add(new Shape("rectangle", 190, 180, 300, 300 ));
        displayList.add(new Shape("oval", 190, 180, 300, 300 ));
        //listeners
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLUE);
        for (int i = 80; i < getHeight(); i+=20) {
            g2.drawLine(0, i, getWidth(), i);
        }
        g2.setColor(Color.RED);
        g2.drawLine(80, 0, 80, getHeight());
        for (Drawn e: displayList) {
            if (e instanceof Shape) {
                Shape shape = (Shape) e;
                if (shape.getType().equals("rectangle")) {
                    g2.drawRect(shape.getX(), shape.getY(),shape.getWidth(), shape.getHeight());
                } else if (shape.getType().equals("oval")) {
                    g2.drawOval(shape.getX(), shape.getY(),shape.getWidth(), shape.getHeight());
                }
            }
        }
    }
}
