public enum InkMode {
    FREE ("Free-form Ink"), RECT ("Rectangle"), OVAL ("Oval"), TEXT ("Text");

    private String mode;

    InkMode(String s) {
        this.mode = s;
    }

    @Override
    public String toString() {
        return mode;
    }
}
