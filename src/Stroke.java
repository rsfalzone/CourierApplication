import java.util.ArrayList;

public class Stroke extends Drawn {
    private ArrayList<Integer> points = new ArrayList<>();
    public Stroke(String type, int x, int y) {
        super(type);
        points.add(new Integer(x));
        points.add(new Integer(y));
    }

    public void add(int x, int y) {
        points.add(new Integer(x));
        points.add(new Integer(y));
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }
}
