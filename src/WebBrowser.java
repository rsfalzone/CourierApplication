import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class WebBrowser extends JPanel {

    public WebBrowser() {
        LinkedList<String> URLList = new LinkedList<>();
        String initialURL = "http://www.google.com/";
        URLList.add(initialURL);
        final JEditorPane ed;

        JButton btnBack = new JButton("Back");
        btnBack.setEnabled(false);
        JLabel lblURL = new JLabel("URL");
        final JTextField txtURL = new JTextField(initialURL, 30);
        JButton btnBrowse = new JButton("Browse");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnBack);
        panel.add(lblURL);
        panel.add(txtURL);
        panel.add(btnBrowse);

        try {
            ed = new JEditorPane(initialURL);
            ed.setEditable(false);

            ed.addHyperlinkListener(new HyperlinkListener() {

                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        JEditorPane pane = (JEditorPane) e.getSource();
                        if (e instanceof HTMLFrameHyperlinkEvent) {
                            HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                            HTMLDocument doc = (HTMLDocument) pane.getDocument();
                            doc.processHTMLFrameHyperlinkEvent(evt);
                        } else {
                            try {
                                pane.setPage(e.getURL());
//                                System.out.println(e.getURL().toString());
                                String newURL = e.getURL().toString();
                                URLList.add(newURL);
                                txtURL.setText(newURL);
                                btnBack.setEnabled(true);
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                    }
                }
            });
            btnBack.addActionListener(
                    new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            if (URLList.size() > 1) {
                                try {
                                    URLList.removeLast();
                                    ed.setPage(URLList.getLast());
                                    txtURL.setText(URLList.getLast());
                                    if (URLList.size() == 1) {
                                        btnBack.setEnabled(false);
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            };
                        }
                    });
            btnBrowse.addActionListener(
                    new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            try {
                                String newURL =  txtURL.getText().trim();
                                ed.setPage(newURL);
                                URLList.add(newURL);
                                btnBack.setEnabled(true);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
            JScrollPane sp = new JScrollPane(ed);
            setLayout(new BorderLayout());
            add(panel, BorderLayout.NORTH);
            add(sp, BorderLayout.CENTER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
