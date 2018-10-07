import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Canvas extends JComponent implements MouseMotionListener, MouseListener, KeyListener {
    private ArrayList<Drawn> displayList;
    private Model model;

    public Canvas(Model model) {
        this.model = model;
//        System.out.println(mode);
        displayList = new ArrayList<>();
//        System.out.println(isFocusable());
//        displayList.add(new Shape("rectangle", 90, 80, 300, 300 ));
//        displayList.add(new Shape("oval", 90, 80, 300, 300 ));
//        displayList.add(new Shape("rectangle", 190, 180, 300, 300 ));
//        displayList.add(new Shape("oval", 190, 180, 300, 300 ));
//        setPreferredSize();
        // Listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
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
        // TODO: Switch Statement
        for (Drawn e: displayList) {
            g2.setColor(Color.BLACK);
            if (e instanceof Shape) {
                Shape shape = (Shape) e;
                if (shape.getType().equals("rectangle")) {
                    g2.drawRect(shape.getX(), shape.getY(),shape.getWidth(), shape.getHeight());
                } else if (shape.getType().equals("oval")) {
                    g2.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                } else if (shape.getType().equals("text")) {
                    TextBox textbox = (TextBox) shape;
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(textbox.getX(), textbox.getY(), textbox.getWidth(), textbox.getHeight());
                    g2.setColor(Color.BLACK);
                    g2.drawRect(textbox.getX(), textbox.getY(), textbox.getWidth(), textbox.getHeight()); //adds border
                    g2.drawString(textbox.getText(), textbox.getX() + 5, textbox.getY()+ 20);
                    // TODO: Split Text into lines
                }
            } else {
                ArrayList<Integer> points  = ((Stroke) e).getPoints();
                for (int i = 2; i < points.size(); i = i+2){
                    g2.drawLine(points.get(i-2), points.get(i-1), points.get(i), points.get(i+1));
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
        InkMode mode = model.getInkMode();
        if (mode == InkMode.FREE) {
            displayList.add(new Stroke("stroke", x, y));
//            System.out.println("mouse pressed");
        } else if (mode == InkMode.RECT) {
            displayList.add(new Shape("rectangle", x, y, 0, 0));
        } else if (mode == InkMode.OVAL) {
            displayList.add(new Shape("oval", x, y, 0, 0));
        } else if (mode == InkMode.TEXT){
            displayList.add(new TextBox("text", x, y, 0, 0));
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Invoked when a mouse button has been released on a component.
        // Could hold a curr variable that is changed while mouse is dragged.
        // this curr would then be added to the display list upon release.
        // current implementation relies on current mode matching mode of most recent drawn
        requestFocusInWindow();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Invoked when a mouse button is pressed on a component and then dragged.
        Drawn curr = displayList.get(displayList.size() - 1);
        if (curr instanceof  Shape) {
            ((Shape) curr).setWidth(e.getX()-((Shape) curr).getX());
            ((Shape) curr).setHeight(e.getY()-((Shape) curr).getY());
            if (curr instanceof TextBox) {
                ((TextBox) curr).setText("");
            }
        } else if (curr instanceof Stroke) {
            ((Stroke) curr).add(e.getX(), e.getY());
        }
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
        // No special action
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //current text focus?
//        System.out.println("Here");
        InkMode mode = model.getInkMode();
        if (mode == InkMode.TEXT){
            if (displayList.get(displayList.size() - 1) instanceof TextBox){
                TextBox textBox = (TextBox) displayList.get(displayList.size() - 1);
                textBox.setText(textBox.getText() + e.getKeyChar());
//                System.out.println(textBox.getText() + e.getKeyChar());
                repaint();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("Here1");
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Here2");
    }
}
