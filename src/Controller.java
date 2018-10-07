import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.addNewPageListener(new NewPageListener());
    }

    class NewPageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.addCanvas(new Canvas(Mode.FREE));
            model.setStatusText(
                    String.format("New Page added to the Canvas. Current Page: (%d/%d)",
                            model.getCurrIndex()+1, model.getCanvasListSize()));
            view.canvasScroll = new JScrollPane(model.getCurrCanvas()); //does not change b/c line 77
            view.statusBar.setText(model.getStatusText());
            view.RHSide.revalidate();
            view.RHSide.repaint();

            view.btnDelPage.setEnabled(true);
            view.btnPageBack.setEnabled(true);
        }
    }
    class DelPageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.delCurrCanvas();
            model.setStatusText(
                    String.format("Page removed from the Canvas. Current Page: (%d/%d)",
                            model.getCurrIndex()+1, model.getCanvasListSize()));
            view.canvasScroll = new JScrollPane(model.getCurrCanvas());
            view.RHSide.revalidate();
            view.RHSide.repaint();
            if (model.getCurrIndex()>1) {
                view.btnDelPage.setEnabled(true);
                view.btnPageBack.setEnabled(true);
            } else {
                view.btnDelPage.setEnabled(false);
                view.btnPageBack.setEnabled(false);
            }
        }
    }
}
