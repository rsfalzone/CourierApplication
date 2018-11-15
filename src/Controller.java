import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Controller {
    Model model;
    View view;
    int i = 0;
    Timer timer;
    int distance;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.addNewPageListener(new NavigationListener(NavAction.ADD));
        view.addDelPageListener(new NavigationListener(NavAction.DEL));
        view.addPageFwdListener(new NavigationListener(NavAction.FWD));
        view.addPageBackListener(new NavigationListener(NavAction.BCK));
        view.addInkModeListeners(
                new InkModeListener(InkMode.FREE),
                new InkModeListener(InkMode.RECT),
                new InkModeListener(InkMode.OVAL),
                new InkModeListener(InkMode.TEXT));
    }

    // TODO: MVC Questions
    //      How to update the View based on changes to Model?
    //          - Through Controller manipulation?
    //          - Broadcasting change in Model, Listening in View
    class InkModeListener implements ActionListener {
        InkMode inkMode;

        InkModeListener(InkMode m) {
            this.inkMode = m;
        }

        public void actionPerformed(ActionEvent e) {
            model.setInkMode(inkMode);
            model.setStatusText(
                    String.format("The Canvas is now in %s mode.",
                            model.getInkMode()));
            // TODO: Update View
        }
    }

    class NavigationListener implements ActionListener {
        NavAction action;

        NavigationListener(NavAction a){
            this.action = a;
        }

        public void actionPerformed(ActionEvent e) {
            BufferedImage curr;
            BufferedImage next;
            Boolean pageTurned;

            // Update View (pt. 1)
            view.RHSide.remove(view.canvasScroll);

            // Update Model
            switch (action) {
                case ADD:
                    model.addCanvas(new Canvas(model, view));
                    model.setStatusText(
                            String.format("New Page added to the Canvas. Current Page: (%d/%d)",
                                    model.getCurrIndex()+1, model.getCanvasListSize()));
                    break;
                case DEL:
                    model.delCurrCanvas();
                    model.setStatusText(
                            String.format("Page removed from the Canvas. Current Page: (%d/%d)",
                                    model.getCurrIndex()+1, model.getCanvasListSize()));
                    break;
                case FWD:
//                    pageTurned = turnPageAnimation();
//                    if (pageTurned) {
                    model.NextCanvas();
                    model.setStatusText(
                            String.format("Page forward in Canvas. Current Page: (%d/%d)",
                                    model.getCurrIndex() + 1, model.getCanvasListSize()));
//                    }
                    break;
                case BCK:
//                    curr = makeOffscreenImage(model.getCurrCanvas());
//                    next = makeOffscreenImage(model.getPrevCanvas());
//                    pageTurned = turnPageAnimation(curr, next);
//                    if (pageTurned) {
//                    pageTurned = turnPageAnimation();
                    model.PrevCanvas();
                    model.setStatusText(
                            String.format("Page backward in Canvas. Current Page: (%d/%d)",
                                    model.getCurrIndex() + 1, model.getCanvasListSize()));
//                    }
                    break;
            }

            // Update View (pt. 2)
            view.canvasScroll = new JScrollPane(model.getCurrCanvas());
            view.statusBar.setText(model.getStatusText());
            view.RHSide.add(view.canvasScroll, BorderLayout.CENTER);
            view.RHSide.revalidate();
            view.RHSide.repaint();

            // Button Control
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

    enum NavAction {
        ADD, DEL, FWD, BCK
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
    public boolean turnPageAnimation() {

        model.setPageTurning(true);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage curr = makeOffscreenImage(model.getCurrCanvas());
                BufferedImage next = makeOffscreenImage(model.getNextCanvas());
                distance = 0;
                if (i < 4) {
                    distance += curr.getWidth()/5;
                    i++;
                } else {
                    i = 0;
                    timer.stop();
                }
                System.out.println(i);
            }
        });
        timer.start();
        model.setPageTurning(false);
        return true;
    }
}
