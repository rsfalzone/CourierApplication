import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class UserContent extends JComponent implements MouseMotionListener, MouseListener {
    ArrayList<Drawn> displayList;
    Mode mode;

    public UserContent() {
        mode = Mode.RECT;
        displayList = new ArrayList<>();
//        displayList.add(new Shape("rectangle", 90, 80, 300, 300 ));
//        displayList.add(new Shape("oval", 90, 80, 300, 300 ));
//        displayList.add(new Shape("rectangle", 190, 180, 300, 300 ));
//        displayList.add(new Shape("oval", 190, 180, 300, 300 ));

        // Listeners
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Background
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLUE);
        for (int i = 80; i < getHeight(); i+=20) {
            g2.drawLine(0, i, getWidth(), i);
        }
        g2.setColor(Color.RED);
        g2.drawLine(80, 0, 80, getHeight());

        // Draw: Loops over display list to paint.
        g2.setColor(Color.BLACK);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // Invoked when the mouse button has been clicked (pressed and released) on a component.
        // No special action
//         System.out.println("mouse clicked at point:" + e.getX() + " " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Invoked when the mouse enters a component.
        // No special action
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        // Invoked when the mouse exits a component.
        // No special action
//        System.out.println("mouse exited through point:" + e.getX() + " " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Invoked when a mouse button has been pressed on a component.
        int x = e.getX();
        int y = e.getY();
        if (mode == Mode.FREE) {
            // TODO: strokes
        } else if (mode == Mode.RECT) {
            displayList.add(new Shape("rectangle", x, y, 0, 0));
            System.out.println(mode);
            mode = Mode.OVAL;   // TESTING ONLY
        } else if (mode == Mode.OVAL) {
            displayList.add(new Shape("oval", x, y, 0, 0));
            System.out.println(mode);
            mode = Mode.RECT;   // TESTING ONLY
        } else {
            // TODO: text
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Invoked when a mouse button has been released on a component.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Invoked when a mouse button is pressed on a component and then dragged.
        Drawn curr = displayList.get(displayList.size() - 1);
        if (curr instanceof  Shape) {
            ((Shape) curr).setWidth(e.getX()-((Shape) curr).getX());
            ((Shape) curr).setHeight(e.getY()-((Shape) curr).getY());
        }
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
        // No special action
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
