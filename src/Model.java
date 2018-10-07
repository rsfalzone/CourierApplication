import java.util.LinkedList;

public class Model {
    private Mode inkMode;
    private LinkedList<Canvas> canvasList;
    private int currIndex;
    private String statusText;

    public Model() {
        this.inkMode = Mode.FREE;
        this.canvasList = new LinkedList<>();
        this.statusText = new String("Status Bar");
        this.addCanvas(new Canvas(Mode.FREE));
    }

    public Mode getInkMode() {
        return inkMode;
    }
    public void setInkMode(Mode inkMode) {
        this.inkMode = inkMode;
    }

    public void addCanvas(Canvas canvas) {
        canvasList.add(canvas);
        currIndex = canvasList.size()-1;
    }
    public void delCurrCanvas() {
        if (currIndex > 0) {
            canvasList.remove(currIndex);
            currIndex--;
        }
    }
    public Canvas getCurrCanvas() {
        return canvasList.get(currIndex);
    }

    public int getCurrIndex() {
        return currIndex;
    }
    public int getCanvasListSize(){
        return canvasList.size();
    }
    public LinkedList<Canvas> getCanvasList() {
        return canvasList;
    }

    public String getStatusText() {
        return statusText;
    }
    public void setStatusText(String text) {
        statusText = text;
    }
}
