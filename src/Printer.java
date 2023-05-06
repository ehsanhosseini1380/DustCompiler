public class Printer {

    public static final int INDENTATION_UNIT = 4;

    public int indentation = 0;

    public void increaseIndentation() {
        indentation += INDENTATION_UNIT;
    }

    public void decreaseIndentation() {
        indentation -= INDENTATION_UNIT;
    }

}
