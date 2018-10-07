import java.util.LinkedList;

public class Model {
    private InkMode inkMode;
    private LinkedList<Canvas> canvasList;
    private int currIndex;
    private String statusText;

    public Model() {
        this.inkMode = InkMode.FREE;
        this.canvasList = new LinkedList<>();
        this.statusText = new String("Status Bar");
        this.addCanvas(new Canvas(this)); // good??
    }

    public InkMode getInkMode() {
        return inkMode;
    }
    public void setInkMode(InkMode inkMode) {
        this.inkMode = inkMode;
    }

    public void addCanvas(Canvas canvas) {
        canvasList.add(canvas);
        currIndex = canvasList.size()-1;
    }
    public boolean delCurrCanvas() {
        if (canvasList.size() > 0) {
            canvasList.remove(currIndex);
            if (currIndex > 0) {
                currIndex--;
            }
            return true;
        }
        return false;
    }
    public boolean NextCanvas(){
        if (currIndex < canvasList.size() - 1) {
            currIndex++;
            return true;
        }
        return false;
    }
    public boolean PrevCanvas(){
        if (currIndex > 0) {
            currIndex--;
            return true;
        }
        return false;
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
