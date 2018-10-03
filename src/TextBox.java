public class TextBox extends Shape {
    protected String text;
    public TextBox(String type, int x, int y, int width, int height) {
        super(type, x, y, width, height);
        text = new String();
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
