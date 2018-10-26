import java.util.LinkedList;

public class Model {
    private InkMode inkMode;
    private LinkedList<Canvas> canvasList;
    private int currIndex;
    private String statusText;

    // TODO: Canvas must access inkMode in model
    //          Current structure requires:
    //             Model have reference to each canvas
    //             Canvas to have reference to Model
    public Model() {
        this.inkMode = InkMode.FREE;
        this.canvasList = new LinkedList<>();
        this.statusText = new String("Status Bar");
//        this.addCanvas(new Canvas(this, )); // good??
    }
    // Ink mode modifiers
    public InkMode getInkMode() {
        return inkMode;
    }
    public void setInkMode(InkMode inkMode) {
        this.inkMode = inkMode;
    }

    // Canvas navigation modifiers
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

    // CanvasList getters
    public int getCurrIndex() {
        return currIndex;
    }
    public int getCanvasListSize(){
        return canvasList.size();
    }
    public Canvas getCurrCanvas() {
        return canvasList.get(currIndex);
    }
    public LinkedList<Canvas> getCanvasList() {
        return canvasList;
    }

    // Status text modifiers
    public String getStatusText() {
        return statusText;
    }
    public void setStatusText(String text) {
        statusText = text;
    }
}
