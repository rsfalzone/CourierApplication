import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    Model model;
    View view;

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

    class InkModeListener implements ActionListener {
        InkMode inkMode;

        InkModeListener(InkMode m) {
            this.inkMode = m;
        }

        public void actionPerformed(ActionEvent e) {
            model.setInkMode(inkMode);
            model.setStatusText(
                    String.format("User Content is now in %s mode.",
                            model.getInkMode()));
        }
    }

    class NavigationListener implements ActionListener {
        NavAction action;

        NavigationListener(NavAction a){
            this.action = a;
        }

        public void actionPerformed(ActionEvent e) {
            // Update View (pt. 1)
            view.RHSide.remove(view.canvasScroll);

            // Update Model
            switch (action) {
                case ADD:
                    model.addCanvas(new Canvas(model));
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
                    model.NextCanvas();
                    model.setStatusText(
                            String.format("Page forward in Canvas. Current Page: (%d/%d)",
                                    model.getCurrIndex()+1, model.getCanvasListSize()));
                    break;
                case BCK:
                    model.PrevCanvas();
                    model.setStatusText(
                            String.format("Page backward in Canvas. Current Page: (%d/%d)",
                                    model.getCurrIndex()+1, model.getCanvasListSize()));
                    break;
            }

            // Update View
            view.canvasScroll = new JScrollPane(model.getCurrCanvas());
            view.statusBar.setText(model.getStatusText());
            view.RHSide.add(view.canvasScroll, BorderLayout.CENTER);
            view.RHSide.revalidate();
            view.RHSide.repaint();

            // Button Control (pt. 2)
            if (model.getCanvasListSize() == 1) {
                view.btnDelPage.setEnabled(false);
                view.btnPageFwd.setEnabled(false);
                view.btnPageBack.setEnabled(false);
            } else {
                view.btnDelPage.setEnabled(true);
                if (model.getCurrIndex() == 0) {
                    view.btnPageBack.setEnabled(false);
                } else {
                    view.btnPageBack.setEnabled(true);
                }
                if (model.getCurrIndex() == model.getCanvasListSize() - 1) {
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
}
