import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class View {

    public View() {
        JPanel webBrowser = new WebBrowser();
        JPanel addressBook = new AddressBook();

        // LH Side
        JTabbedPane LHSide = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        LHSide.setPreferredSize(new Dimension(600,500));
        LHSide.addTab("Web Browser", webBrowser);
        LHSide.addTab("Address Book", addressBook);

        // RH side
        final Mode[] mode = new Mode[1];
        mode[0] = Mode.FREE;
        final LinkedList<Canvas> canvasList = new LinkedList<>();
        final int[] curr = new int[1];
        curr[0] = 0;
        canvasList.add(new Canvas(Mode.FREE));

        // Page Navigation Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnNewPage = new JButton("New Page");
        JButton btnDelPage = new JButton("Delete Page");
        JButton btnPageFwd= new JButton("Page Forward");
        JButton btnPageBack= new JButton("Page Backward");
        btnDelPage.setEnabled(false);
        btnPageFwd.setEnabled(false);
        btnPageBack.setEnabled(false);

        // Ink Mode Buttons
        JRadioButton btnFreeForm = new JRadioButton("Free-form Ink");
        JRadioButton btnRectangle = new JRadioButton("Rectangle");
        JRadioButton btnOval = new JRadioButton("Oval");
        JRadioButton btnText = new JRadioButton("Text");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnFreeForm);
        btnGroup.add(btnRectangle);
        btnGroup.add(btnOval);
        btnGroup.add(btnText);
        btnFreeForm.setSelected(true);

        buttonPanel.add(btnNewPage);
        buttonPanel.add(btnDelPage);
        buttonPanel.add(btnPageFwd);
        buttonPanel.add(btnPageBack);
        buttonPanel.add(btnFreeForm);
        buttonPanel.add(btnRectangle);
        buttonPanel.add(btnOval);
        buttonPanel.add(btnText);

        JLabel statusBar = new JLabel("Status Bar");

        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        rightSide.add(canvasList.getFirst(), BorderLayout.CENTER);
        rightSide.add(buttonPanel, BorderLayout.SOUTH);
    }
}
