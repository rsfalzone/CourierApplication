import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class CourierApp {
    // todo: separate acctions into anoth class
    // todo: separate model instance into another class
    // Todo: separate view into a class
    private static void initGUI() {
//        // Computer Generated Content (CGC) modules
//        JPanel webBrowser = new WebBrowser();
//        JPanel addressBook = new AddressBook();
//
//        // LH Side
//        JTabbedPane LHSide = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
//        LHSide.setPreferredSize(new Dimension(600,500));
//        LHSide.addTab("Web Browser", webBrowser);
//        LHSide.addTab("Address Book", addressBook);
//
//        // RH side
//        final Mode[] mode = new Mode[1];
//        mode[0] = Mode.FREE;
//        final LinkedList<Canvas> canvasList = new LinkedList<>();
//        final int[] curr = new int[1];
//        curr[0] = 0;
//        canvasList.add(new Canvas(Mode.FREE));
//
//        // Page Navigation Buttons
//        JPanel buttonPanel = new JPanel();
//        JButton btnNewPage = new JButton("New Page");
//        JButton btnDelPage = new JButton("Delete Page");
//        JButton btnPageFwd= new JButton("Page Forward");
//        JButton btnPageBack= new JButton("Page Backward");
//        btnDelPage.setEnabled(false);
//        btnPageFwd.setEnabled(false);
//        btnPageBack.setEnabled(false);
//
//        // Ink Mode Buttons
//        JRadioButton btnFreeForm = new JRadioButton("Free-form Ink");
//        JRadioButton btnRectangle = new JRadioButton("Rectangle");
//        JRadioButton btnOval = new JRadioButton("Oval");
//        JRadioButton btnText = new JRadioButton("Text");
//
//        ButtonGroup btnGroup = new ButtonGroup();
//        btnGroup.add(btnFreeForm);
//        btnGroup.add(btnRectangle);
//        btnGroup.add(btnOval);
//        btnGroup.add(btnText);
//        btnFreeForm.setSelected(true);
//
//        buttonPanel.add(btnNewPage);
//        buttonPanel.add(btnDelPage);
//        buttonPanel.add(btnPageFwd);
//        buttonPanel.add(btnPageBack);
//        buttonPanel.add(btnFreeForm);
//        buttonPanel.add(btnRectangle);
//        buttonPanel.add(btnOval);
//        buttonPanel.add(btnText);
//
//        JLabel statusBar = new JLabel("Status Bar");
//
//        JPanel rightSide = new JPanel();
//        rightSide.setLayout(new BorderLayout());
//        rightSide.add(canvasList.getFirst(), BorderLayout.CENTER);
//        rightSide.add(buttonPanel, BorderLayout.SOUTH);
//
//        btnNewPage.addActionListener(e -> {
//            rightSide.remove(canvasList.get(curr[0]));
//            canvasList.add(new Canvas(mode[0]));
//            curr[0] = canvasList.size()-1;
//            statusBar.setText(String.format("New Page added to User Content. Current Page: (%d/%d)",
//                    curr[0]+1, canvasList.size()));
//
//            rightSide.add(canvasList.get(curr[0]), BorderLayout.CENTER);
//            rightSide.revalidate();
//            rightSide.repaint();
//
//            btnDelPage.setEnabled(true);
//            btnPageBack.setEnabled(true);
//        });
//
//        btnDelPage.addActionListener(e -> {
//            rightSide.remove(canvasList.get(curr[0]));
//            canvasList.remove(curr[0]);
//            if (curr[0] >  0) {
//                curr[0]--;
//            }
//
//            statusBar.setText(String.format("Delete Page button clicked. Current Page: (%d/%d)",
//                    curr[0]+1, canvasList.size()));
//
//            if (canvasList.size() == 1) {
//                btnDelPage.setEnabled(false);
//                btnPageFwd.setEnabled(false);
//                btnPageBack.setEnabled(false);
//            }
//
//            rightSide.add(canvasList.get(curr[0]), BorderLayout.CENTER);
//            rightSide.revalidate();
//            rightSide.repaint();
//        });
//
//        btnPageFwd.addActionListener(e -> {
//            if (curr[0] < canvasList.size() - 1) {
//                rightSide.remove(canvasList.get(curr[0]));
//                curr[0]++;
//
//                statusBar.setText(String.format("Page Forward button clicked. Current Page: (%d/%d)",
//                        curr[0]+1, canvasList.size()));
//
//                rightSide.add(canvasList.get(curr[0]), BorderLayout.CENTER);
//                rightSide.revalidate();
//                rightSide.repaint();
//                btnPageBack.setEnabled(true);
//            }
//            if (curr[0] == canvasList.size() - 1) {
//                btnPageFwd.setEnabled(false);
//            }
//        });
//        btnPageBack.addActionListener(e -> {
//            if (curr[0] > 0) {
//                rightSide.remove(canvasList.get(curr[0]));
//                curr[0] = curr[0]-1;
//
//                statusBar.setText(String.format("Page Backward button clicked. Current Page: (%d/%d)",
//                        curr[0]+1, canvasList.size()));
//
//                rightSide.add(canvasList.get(curr[0]), BorderLayout.CENTER);
//                rightSide.revalidate();
//                rightSide.repaint();
//                btnPageFwd.setEnabled(true);
//            }
//            if (curr[0] == 0) {
//                btnPageBack.setEnabled(false);
//            }
//        });
//        btnFreeForm.addActionListener(e -> {
//            mode[0] = Mode.FREE;
//            statusBar.setText("User Content is now in " + mode[0] + " mode.");
//            for (Canvas c :canvasList) {
//                c.setMode(mode[0]);
//            }
//        });
//        btnRectangle.addActionListener(e -> {
//            mode[0] = Mode.RECT;
//            statusBar.setText("User Content is now in " + mode[0] + " mode.");
//            for (Canvas c :canvasList) {
//                c.setMode(mode[0]);
//            }
//        });
//        btnOval.addActionListener(e -> {
//            mode[0] = Mode.OVAL;
//            statusBar.setText("User Content is now in " + mode[0] + " mode.");
//            for (Canvas c :canvasList) {
//                c.setMode(mode[0]);
//            }
//        });
//        btnText.addActionListener(e -> {
//            mode[0] = Mode.TEXT;
//            statusBar.setText("User Content is now in " + mode[0] + " mode.");
//            for (Canvas c :canvasList) {
//                c.setMode(mode[0]);
//            }
//        });
//
//
//
//        // Split pane
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LHSide,  rightSide);
//        //cutting off buttonPanel?
//
//        // mainFrame
//        BorderLayout layout = new BorderLayout();
//        JFrame mainFrame = new JFrame("Courier App");
//        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        mainFrame.setLayout(layout);
//        mainFrame.add(splitPane, BorderLayout.CENTER);
//        mainFrame.add(statusBar,BorderLayout.SOUTH);
////        mainFrame.setPreferredSize(new Dimension(1200,500));
//        mainFrame.pack();
//        mainFrame.setVisible(true);
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGUI();
            }
        });
    }
}