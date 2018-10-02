//package CourierApp;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
public class CourierApp {

    private static void initGUI() {
        // webBrowser tab
        JPanel webBrowser = new WebBrowser();
//        LinkedList<String> URLList = new LinkedList<>();
//        String initialURL = "http://www.google.com/";
//        URLList.add(initialURL);
//        final JEditorPane ed;
//
//        JButton btnBack = new JButton("Back");
//        btnBack.setEnabled(false);
//        JLabel lblURL = new JLabel("URL");
//        final JTextField txtURL = new JTextField(initialURL, 30);
//        JButton btnBrowse = new JButton("Browse");
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        panel.add(btnBack);
//        panel.add(lblURL);
//        panel.add(txtURL);
//        panel.add(btnBrowse);
//
//        try {
//            ed = new JEditorPane(initialURL);
//            ed.setEditable(false);
//
//            ed.addHyperlinkListener(new HyperlinkListener() {
//
//                public void hyperlinkUpdate(HyperlinkEvent e) {
//                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
//                        JEditorPane pane = (JEditorPane) e.getSource();
//                        if (e instanceof HTMLFrameHyperlinkEvent) {
//                            HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
//                            HTMLDocument doc = (HTMLDocument) pane.getDocument();
//                            doc.processHTMLFrameHyperlinkEvent(evt);
//                        } else {
//                            try {
//                                pane.setPage(e.getURL());
////                                System.out.println(e.getURL().toString());
//                                String newURL = e.getURL().toString();
//                                URLList.add(newURL);
//                                txtURL.setText(newURL);
//                                btnBack.setEnabled(true);
//                            } catch (Throwable t) {
//                                t.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            });
//            btnBack.addActionListener(
//                    new ActionListener() {
//
//                        public void actionPerformed(ActionEvent e) {
//                            if (URLList.size() > 1) {
//                                try {
//                                    URLList.removeLast();
//                                    ed.setPage(URLList.getLast());
//                                    txtURL.setText(URLList.getLast());
//                                    if (URLList.size() == 1) {
//                                        btnBack.setEnabled(false);
//                                    }
//                                } catch (IOException ex) {
//                                    ex.printStackTrace();
//                                }
//                            };
//                        }
//                    });
//            btnBrowse.addActionListener(
//                    new ActionListener() {
//
//                        public void actionPerformed(ActionEvent e) {
//                            try {
//                                String newURL =  txtURL.getText().trim();
//                                ed.setPage(newURL);
//                                URLList.add(newURL);
//                                btnBack.setEnabled(true);
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                    });
//            JScrollPane sp = new JScrollPane(ed);
//            webBrowser.setLayout(new BorderLayout());
//            webBrowser.add(panel, BorderLayout.NORTH);
//            webBrowser.add(sp, BorderLayout.CENTER);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        //table
        String[] columnNames = new String[] {"Last Name", "First Name", "Phone Number", "Email", "City", "State"};
        String[][] rowData = new String[][] {
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"},
                {"Falzone", "Ronnie", "123-234-3456", "ronnie.falzone@email.com", "Atlanta", "GA"},
                {"Davidson", "Paisley", "987-876-7654", "paisley.davidson@email.com", "Savannah", "GA"},
                {"Ehster", "Chris", "102-938-4756", "chris.ehster@email.com", "Tallahassee", "FL"}
        };

        JTable table = new JTable(rowData, columnNames);
        JScrollPane leftTop = new JScrollPane(table);

        //info
        JTextArea info = new JTextArea();
        info.setFont(new Font("Arial Black", Font.BOLD, 24));
        String infoText = "Contact\n\nLast Name: %s\nFirst Name: %s\nPhone Number: %s\nEmail: %s\nCity: %s\nState: %s";
        JScrollPane leftBottom = new JScrollPane(info);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                String first = table.getValueAt(table.getSelectedRow(),0).toString();
                String last = table.getValueAt(table.getSelectedRow(),1).toString();
                String phone = table.getValueAt(table.getSelectedRow(),2).toString();
                String email = table.getValueAt(table.getSelectedRow(),3).toString();
                String city = table.getValueAt(table.getSelectedRow(),4).toString();
                String state = table.getValueAt(table.getSelectedRow(),5).toString();
                info.setText(String.format(infoText, first, last, phone, email, city, state));
            }
        });

        // addressbook tab
        JPanel addressBook = new JPanel();
        addressBook.setLayout(new GridLayout(2, 1));
        addressBook.add(leftTop);
        addressBook.add(leftBottom);

        // TabbedPane
        JTabbedPane tabbedFrame = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT); // left side
        tabbedFrame.setPreferredSize(new Dimension(600,500));
        tabbedFrame.addTab("Web Browser", webBrowser);
        tabbedFrame.addTab("Address Book", addressBook);

        // Right side
        JPanel digitalInk = new JPanel();

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