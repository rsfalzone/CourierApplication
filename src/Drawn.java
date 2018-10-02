public class Drawn {
    protected String type;

    public Drawn(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Drawn)) {
            return false;
        }
        Drawn other = (Drawn) obj;
        return this.type.equals(other.getType());
    }
}
