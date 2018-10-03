import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class CourierApp {

    private static void initGUI() {
        JPanel webBrowser = new WebBrowser();
        JPanel addressBook = new AddressBook();

        // TabbedPane
        JTabbedPane tabbedFrame = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT); // left side
        tabbedFrame.setPreferredSize(new Dimension(600,500));
        tabbedFrame.addTab("Web Browser", webBrowser);
        tabbedFrame.addTab("Address Book", addressBook);

        // Right side
        final Mode[] mode = new Mode[1];
        mode[0] = Mode.FREE;
        final LinkedList<UserContent> userContentList = new LinkedList<>();
        final int[] curr = new int[1];
        curr[0] = 0;

        userContentList.add(new UserContent(Mode.FREE));

        JPanel buttons = new JPanel();

        JButton btnNewPage = new JButton("New Page");
        JButton btnDelPage = new JButton("Delete Page");
        JButton btnPageFwd= new JButton("Page Forward");
        JButton btnPageBack= new JButton("Page Backward");
        btnDelPage.setEnabled(false);
        btnPageFwd.setEnabled(false);
        btnPageBack.setEnabled(false);

        JRadioButton btnFreeForm = new JRadioButton("Free-form Ink");
        JRadioButton btnRectangle = new JRadioButton("Rectangle");
        JRadioButton btnOval = new JRadioButton("Oval");
        JRadioButton btnText = new JRadioButton("Text");
        JLabel statusBar = new JLabel("Status Bar");

        buttons.add(btnNewPage);
        buttons.add(btnDelPage);
        buttons.add(btnPageFwd);
        buttons.add(btnPageBack);
        buttons.add(btnFreeForm);
        buttons.add(btnRectangle);
        buttons.add(btnOval);
        buttons.add(btnText);

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnFreeForm);
        btnGroup.add(btnRectangle);
        btnGroup.add(btnOval);
        btnGroup.add(btnText);
        btnFreeForm.setSelected(true);

        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        rightSide.add(userContentList.getFirst(), BorderLayout.CENTER);
        rightSide.add(buttons, BorderLayout.SOUTH);

        btnNewPage.addActionListener(e -> {
            rightSide.remove(userContentList.get(curr[0]));
            userContentList.add(new UserContent(mode[0]));
            curr[0] = userContentList.size()-1;
            statusBar.setText(String.format("New Page added to User Content. Current Page: (%d/%d)",
                    curr[0]+1, userContentList.size()));

            rightSide.add(userContentList.get(curr[0]), BorderLayout.CENTER);
            rightSide.revalidate();
            rightSide.repaint();

            btnDelPage.setEnabled(true);
            btnPageBack.setEnabled(true);
        });

        btnDelPage.addActionListener(e -> {
            rightSide.remove(userContentList.get(curr[0]));
            userContentList.remove(curr[0]);
            if (curr[0] >  0) {
                curr[0]--;
            }

            statusBar.setText(String.format("Delete Page button clicked. Current Page: (%d/%d)",
                    curr[0]+1, userContentList.size()));

            if (userContentList.size() == 1) {
                btnDelPage.setEnabled(false);
                btnPageFwd.setEnabled(false);
                btnPageBack.setEnabled(false);
            }

            rightSide.add(userContentList.get(curr[0]), BorderLayout.CENTER);
            rightSide.revalidate();
            rightSide.repaint();
        });

        btnPageFwd.addActionListener(e -> {
            if (curr[0] < userContentList.size() - 1) {
                rightSide.remove(userContentList.get(curr[0]));
                curr[0]++;

                statusBar.setText(String.format("Page Forward button clicked. Current Page: (%d/%d)",
                        curr[0]+1, userContentList.size()));

                rightSide.add(userContentList.get(curr[0]), BorderLayout.CENTER);
                rightSide.revalidate();
                rightSide.repaint();
                btnPageBack.setEnabled(true);
            }
            if (curr[0] == userContentList.size() - 1) {
                btnPageFwd.setEnabled(false);
            }
        });
        btnPageBack.addActionListener(e -> {
            if (curr[0] > 0) {
                rightSide.remove(userContentList.get(curr[0]));
                curr[0] = curr[0]-1;

                statusBar.setText(String.format("Page Backward button clicked. Current Page: (%d/%d)",
                        curr[0]+1, userContentList.size()));

                rightSide.add(userContentList.get(curr[0]), BorderLayout.CENTER);
                rightSide.revalidate();
                rightSide.repaint();
                btnPageFwd.setEnabled(true);
            }
            if (curr[0] == 0) {
                btnPageBack.setEnabled(false);
            }
        });
        btnFreeForm.addActionListener(e -> {
            mode[0] = Mode.FREE;
            statusBar.setText("User Content is now in " + mode[0] + " mode.");
            for (UserContent c :userContentList) {
                c.setMode(mode[0]);
            }
        });
        btnRectangle.addActionListener(e -> {
            mode[0] = Mode.RECT;
            statusBar.setText("User Content is now in " + mode[0] + " mode.");
            for (UserContent c :userContentList) {
                c.setMode(mode[0]);
            }
        });
        btnOval.addActionListener(e -> {
            mode[0] = Mode.OVAL;
            statusBar.setText("User Content is now in " + mode[0] + " mode.");
            for (UserContent c :userContentList) {
                c.setMode(mode[0]);
            }
        });
        btnText.addActionListener(e -> {
            mode[0] = Mode.TEXT;
            statusBar.setText("User Content is now in " + mode[0] + " mode.");
            for (UserContent c :userContentList) {
                c.setMode(mode[0]);
            }
        });



        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedFrame,  rightSide);
        //cutting off buttons?

        // mainFrame
        BorderLayout layout = new BorderLayout();
        JFrame mainFrame = new JFrame("Courier App");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(layout);
        mainFrame.add(splitPane, BorderLayout.CENTER);
        mainFrame.add(statusBar,BorderLayout.SOUTH);
//        mainFrame.setPreferredSize(new Dimension(1200,500));
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGUI();
            }
        });
    }
}