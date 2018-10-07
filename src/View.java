import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    Model model;

    JTabbedPane LHSide;
    JPanel RHSide;

    JScrollPane canvasScroll;
    JLabel statusBar;

    JButton btnNewPage;
    JButton btnDelPage;
    JButton btnPageFwd;
    JButton btnPageBack;
    JRadioButton btnFreeForm;
    JRadioButton btnRectangle;
    JRadioButton btnOval;
    JRadioButton btnText;


    public View(Model model) {
        this.model = model;

    // LH Side
        JPanel webBrowser = new WebBrowser();
        JPanel addressBook = new AddressBook();

        LHSide = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
//        LHSide.setPreferredSize(new Dimension(600, 500)); //TODO: list as constants ^^
        LHSide.addTab("Web Browser", webBrowser);
        LHSide.addTab("Address Book", addressBook);

    //RH Side
        // Canvas
        canvasScroll = new JScrollPane(model.getCurrCanvas());
//        JComponent canvasScroll = new Canvas(Mode.FREE);
//        canvasScroll.add(new Canvas(Mode.FREE), BorderLayout.CENTER);
        // TODO: Scroll bar configuration

        // Canvas Page Navigation buttons
        btnNewPage = new JButton("New Page");
        btnDelPage = new JButton("Delete Page");
        btnPageFwd = new JButton("Page Forward");
        btnPageBack = new JButton("Page Backward");
        btnDelPage.setEnabled(false);
        btnPageFwd.setEnabled(false);
        btnPageBack.setEnabled(false);

        // Ink Mode Buttons
        btnFreeForm = new JRadioButton("Free-form Ink");
        btnRectangle = new JRadioButton("Rectangle");
        btnOval = new JRadioButton("Oval");
        btnText = new JRadioButton("Text");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnFreeForm);
        btnGroup.add(btnRectangle);
        btnGroup.add(btnOval);
        btnGroup.add(btnText);
        btnFreeForm.setSelected(true);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNewPage);
        buttonPanel.add(btnDelPage);
        buttonPanel.add(btnPageFwd);
        buttonPanel.add(btnPageBack);
        buttonPanel.add(btnFreeForm);
        buttonPanel.add(btnRectangle);
        buttonPanel.add(btnOval);
        buttonPanel.add(btnText);

        RHSide = new JPanel(new BorderLayout());
        RHSide.add(canvasScroll, BorderLayout.CENTER);
        RHSide.add(buttonPanel, BorderLayout.SOUTH);

        // Main Frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LHSide,  RHSide);
        statusBar = new JLabel(model.getStatusText());

        JFrame mainFrame = new JFrame("Courier App");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout()); //TODO: Cuts off buttons?
        mainFrame.add(splitPane, BorderLayout.CENTER);
        mainFrame.add(statusBar,BorderLayout.SOUTH);
//        mainFrame.setPreferredSize(new Dimension(1200,500)); //TODO: list as constants ^^
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void addNewPageListener(ActionListener l) {
        btnNewPage.addActionListener(l);
    }
    public void addDelPageListener(ActionListener l) {
        btnDelPage.addActionListener(l);
    }
    public void addPageFwdListener(ActionListener l) {
        btnPageFwd.addActionListener(l);
    }
    public void addPageBackListener(ActionListener l) {
        btnPageBack.addActionListener(l);
    }

    public void addInkModeListener(ActionListener l) {
        btnFreeForm.addActionListener(l);
        btnRectangle.addActionListener(l);
        btnOval.addActionListener(l);
        btnText.addActionListener(l);
    }
}