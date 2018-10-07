import javax.swing.SwingUtilities;

public class CourierApp {
    private static void initGUI() {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourierApp::initGUI);
    }
}