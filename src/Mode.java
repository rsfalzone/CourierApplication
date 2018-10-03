public enum Mode {
    FREE ("Free-form Ink"), RECT ("Rectangle"), OVAL ("Oval"), TEXT ("Text");

    private String mode;

    Mode(String s) {
        this.mode = s;
    }

    @Override
    public String toString() {
        return mode;
    }
}
