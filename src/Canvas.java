import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.SwingUtilities;


public class Canvas extends JComponent implements MouseMotionListener, MouseListener, KeyListener {
    private Model model;
    private View view;
    private ArrayList<Drawn> displayList;
    Timer timer;

    public Canvas(Model model, View view){
        this.model = model;
        this.view = view;
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
        if (model.getPageTurning()) {
            g.drawImage(model.portion1, 0, 0, this);
            g.drawImage(model.portion3, model.getCurrCanvas().getWidth()/2, 0, this);
        } else {
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
                        g2.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    } else if (shape.getType().equals("oval")) {
                        g2.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    } else if (shape.getType().equals("text")) {
                        TextBox textbox = (TextBox) shape;
                        g2.setColor(Color.YELLOW);
                        g2.fillRect(textbox.getX(), textbox.getY(), textbox.getWidth(), textbox.getHeight());
                        g2.setColor(Color.BLACK);
                        g2.drawRect(textbox.getX(), textbox.getY(), textbox.getWidth(), textbox.getHeight()); //adds border
                        g2.drawString(textbox.getText(), textbox.getX() + 5, textbox.getY() + 20);
                        // TODO: Split Text into lines
                    }
                } else {
                    ArrayList<Integer> points = ((Stroke) e).getPoints();
                    if (e.getType().equals("gesture")) {
                        g2.setColor(Color.BLUE);
                    }
                    for (int i = 2; i < points.size(); i = i + 2) {
                        g2.drawLine(points.get(i - 2), points.get(i - 1), points.get(i), points.get(i + 1));
                    }
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
        if (SwingUtilities.isLeftMouseButton(e)) {
            InkMode mode = model.getInkMode();
            if (mode == InkMode.FREE) {
                displayList.add(new Stroke("stroke", x, y));
                //            System.out.println("mouse pressed");
            } else if (mode == InkMode.RECT) {
                displayList.add(new Shape("rectangle", x, y, 0, 0));
            } else if (mode == InkMode.OVAL) {
                displayList.add(new Shape("oval", x, y, 0, 0));
            } else if (mode == InkMode.TEXT) {
                displayList.add(new TextBox("text", x, y, 0, 0));
            }
            repaint();
        } else if (SwingUtilities.isRightMouseButton(e)) {
            displayList.add(new Stroke("gesture", x, y));
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Invoked when a mouse button has been released on a component.
        // Could hold a curr variable that is changed while mouse is dragged.
        // this curr would then be added to the display list upon release.
        // current implementation relies on current mode matching mode of most recent drawn
        if (SwingUtilities.isRightMouseButton(e)) {
            Stroke gesture = (Stroke) displayList.remove(displayList.size() - 1);
            // create pattern
            ArrayList<Integer> points = gesture.getPoints();
            String pattern = new String();
            for (int i = 2; i < points.size(); i = i+2){
                int dX = points.get(i) - points.get(i-2);
                int dY = points.get(i+1) - points.get(i-1);
                if (dX > 0) {
                    if (dY > 0) {
                        pattern += "B";
                    } else if (dY < 0) {
                        pattern += "A";
                    } else {
                        pattern += "E";
                    }
                } else if (dX < 0) {
                    if (dY > 0) {
                        pattern += "C";
                    } else if (dY < 0) {
                        pattern += "D";
                    } else {
                        pattern += "W";
                    }
                } else {
                    if (dY > 0) {
                        pattern += "S";
                    } else {
                        pattern += "N";
                    }
                }
            }
            // check for pattern match
            // call appropriate functions
            if(pattern.matches("^.{0,2}+[SEB]+[SWC]+.{0,2}+$")) {
//                System.out.println(">");
                model.setStatusText("'Next Page' gesture recognized.");
                view.btnPageFwd.doClick();
                view.statusBar.setText(model.getStatusText());
            } else if (pattern.matches("^.{0,2}+[SWC]+[SEB]+.{0,2}+$")) {
//                System.out.println("<");
                model.setStatusText("'Back Page' gesture recognized.");
                view.btnPageBack.doClick();
                view.statusBar.setText(model.getStatusText());
            } else if (pattern.matches("^.{0,2}+[DWC]+[SWC]+[SEB]+[NEA]+[NWD]+[DWC]+.{0,2}+$")) {
//                System.out.println("o");
                model.setStatusText("'Select' gesture recognized.");
                view.statusBar.setText(model.getStatusText());
            } else if (pattern.matches("^.{0,2}+[DWC]+[SWC]+[SEB]+[NEA]+[NWD]+[DWC]+[SBC]+.{0,2}+$")){
//                System.out.println("phi");
                model.setStatusText("'Delete' gesture recognized.");
                view.statusBar.setText(model.getStatusText());
            } else {
//                System.out.println("Gesture unrecognized.");
                model.setStatusText("Gesture unrecognized.");
                view.statusBar.setText(model.getStatusText());
            }
        }
        repaint();
        requestFocusInWindow();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Invoked when a mouse button is pressed on a component and then dragged.
        Drawn curr = displayList.get(displayList.size() - 1);
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (curr instanceof Shape) {
                ((Shape) curr).setWidth(e.getX() - ((Shape) curr).getX());
                ((Shape) curr).setHeight(e.getY() - ((Shape) curr).getY());
                if (curr instanceof TextBox) {
                    ((TextBox) curr).setText("");
                }
            } else if (curr instanceof Stroke) {
                ((Stroke) curr).add(e.getX(), e.getY());
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
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
        //if right click in the right side then start animation

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Here2");
        //if right click and past midway point, finish animation.
    }

    public boolean turnPageAnimation() {
        //timer
        //action performed
        //action event listener
//            calculate values for portions
            // make portions using subimage
            // incremet timer ticks
            // check how many there have been
        //start timer.
        model.curr = makeOffscreenImage(model.getCurrCanvas());
        model.next = makeOffscreenImage(model.getNextCanvas());
        model.setPageTurning(true);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.i < 4) {
                    // make portions
//                    System.out.print("image is null: ");
//                    System.out.println(model.curr == null);
//                    System.out.print("Canvas is null: ");
//                    System.out.println(model.getCurrCanvas() == null);
                    model.portion1 = model.curr.getSubimage(0, 0, model.getCurrCanvas().getWidth()/2 - 10, model.getCurrCanvas().getHeight());
//                    model.portion2 = offscreenImage.getSubimage(x, y, width, height);
                    model.portion3 = model.next.getSubimage(model.getCurrCanvas().getWidth()/2, 0, model.getCurrCanvas().getWidth()/2, model.getCurrCanvas().getHeight());
                    // set portions
                    model.i++;
                    model.getCurrCanvas().repaint();
                } else {
                    model.setPageTurning(false);
                    model.i = 0;
                    view.RHSide.revalidate();
                    view.RHSide.repaint();
                    timer.stop();
                }
                System.out.println(model.i);
            }
        });
        timer.start();
        return true;
    }
    public BufferedImage makeOffscreenImage (JComponent source) {
        // Create our BufferedImage and get a Graphics object for it
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage offscreenImage = gfxConfig.createCompatibleImage(source.getWidth(), source.getHeight());
        Graphics2D offscreenGraphics = (Graphics2D) offscreenImage.getGraphics();

        // Tell the component to paint itself onto the image
        source.paint(offscreenGraphics);

        // return the image
        return offscreenImage;
    }

    public void buttonControl(){
        if (model.getCanvasListSize() == 1) {
            // Only one Canvas
            view.btnDelPage.setEnabled(false);
            view.btnPageFwd.setEnabled(false);
            view.btnPageBack.setEnabled(false);
        } else {
            view.btnDelPage.setEnabled(true);
            if (model.getCurrIndex() == 0) {
                // At first Canvas
                view.btnPageBack.setEnabled(false);
            } else {
                view.btnPageBack.setEnabled(true);
            }
            if (model.getCurrIndex() == model.getCanvasListSize() - 1) {
                // At last Canvas
                view.btnPageFwd.setEnabled(false);
            } else {
                view.btnPageFwd.setEnabled(true);
            }
        }
    }
}