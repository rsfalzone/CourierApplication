public class Shape extends Drawn {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Shape(String type, int x, int y, int width, int height) {
        super(type);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Shape)) {
            return false;
        }
        Shape other = (Shape) obj;
        return (this.type.equals(other.getType()) &&
                this.x == other.getX() &&
                this.y == other.getY() &&
                this.width == other.getWidth() &&
                this.height == other.getHeight());
    }
}

