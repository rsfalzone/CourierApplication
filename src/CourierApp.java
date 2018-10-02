import java.awt.*;
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
        JComponent digitalInk = new UserContent();

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

        btnNewPage.addActionListener(e -> {statusBar.setText("New Page button clicked.");});
        btnDelPage.addActionListener(e -> {statusBar.setText("Delete Page button clicked.");});
        btnPageFwd.addActionListener(e -> {statusBar.setText("Page Forward button clicked.");});
        btnPageBack.addActionListener(e -> {statusBar.setText("Page Backward button clicked.");});
        btnFreeForm.addActionListener(e -> {statusBar.setText("Free-form Ink radiobutton clicked.");});
        btnRectangle.addActionListener(e -> {statusBar.setText("Rectangle radiobutton clicked.");});
        btnOval.addActionListener(e -> {statusBar.setText("Oval radiobutton clicked.");});
        btnText.addActionListener(e -> {statusBar.setText("Text radiobutton clicked.");});

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
        rightSide.add(digitalInk, BorderLayout.CENTER);
        rightSide.add(buttons, BorderLayout.SOUTH);

        // Split pane
        JSplitPane splitPane = new JSplitPane(1, tabbedFrame,  rightSide);

        // mainFrame
        BorderLayout layout = new BorderLayout();
        JFrame mainFrame = new JFrame("Courier App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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