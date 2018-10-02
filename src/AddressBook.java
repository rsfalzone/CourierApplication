import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class AddressBook extends JPanel{
    public AddressBook() {
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

        setLayout(new GridLayout(2, 1));
        add(leftTop);
        add(leftBottom);
    }
}
