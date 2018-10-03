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
        LinkedList<UserContent> userContentList= new LinkedList<>();
        userContentList.add(new UserContent());

        JPanel buttons = new JPanel();



        JButton btnNewPage = new JButton("New Page");
        JButton btnDelPage = new JButton("Delete Page");
        JButton btnPageFwd= new JButton("Page Forward");
        JButton btnPageBack= new JButton("Page Backward");

        JRadioButton btnFreeForm = new JRadioButton("Free-form Ink");
        JRadioButton btnRectangle = new JRadioButton("Rectangle");
        JRadioButton btnOval = new JRadioButton("Oval");
        JRadioButton btnText = new JRadioButton("Text");
        JLabel statusBar = new JLabel("Status Bar");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnFreeForm);
        btnGroup.add(btnRectangle);
        btnGroup.add(btnOval);
        btnGroup.add(btnText);

        btnNewPage.addActionListener(e -> {
            statusBar.setText("New Page added to User Content.");
            userContentList.add(new UserContent());
            // add to right side
            // revalidate right side
            // repaint right side
        });
        btnDelPage.addActionListener(e -> statusBar.setText("Delete Page button clicked."));
        btnPageFwd.addActionListener(e -> statusBar.setText("Page Forward button clicked."));
        btnPageBack.addActionListener(e -> statusBar.setText("Page Backward button clicked."));
        btnFreeForm.addActionListener(e -> statusBar.setText("User Content is now in " + Mode.FREE + " mode."));
        btnRectangle.addActionListener(e -> statusBar.setText("User Content is now in " + Mode.RECT + " mode."));
        btnOval.addActionListener(e -> statusBar.setText("User Content is now in " + Mode.OVAL + " mode."));
        btnText.addActionListener(e -> statusBar.setText("User Content is now in " + Mode.TEXT + " mode."));

        buttons.add(btnNewPage);
        buttons.add(btnDelPage);
        buttons.add(btnPageFwd);
        buttons.add(btnPageBack);
        buttons.add(btnFreeForm);
        buttons.add(btnRectangle);
        buttons.add(btnOval);
        buttons.add(btnText);

        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        rightSide.add(userContentList.getFirst(), BorderLayout.CENTER);
        rightSide.add(buttons, BorderLayout.SOUTH);

        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedFrame,  rightSide);

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