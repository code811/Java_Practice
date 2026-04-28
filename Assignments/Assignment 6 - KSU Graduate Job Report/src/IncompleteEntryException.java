public class IncompleteEntryException extends Exception {

    private int lineNumber;
    String info;

    public IncompleteEntryException(String errorMessage, int lineNumber, String info) {
        super(errorMessage);
        this.lineNumber = lineNumber;
        this.info = info;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getInfo() {
        return info;
    }
}
