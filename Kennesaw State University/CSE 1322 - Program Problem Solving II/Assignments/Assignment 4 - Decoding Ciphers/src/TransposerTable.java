public class TransposerTable {

    private char[][] transposedText;

    public TransposerTable(int row, int column) {
        transposedText = new char[row][column];
    }

    public char[][] getTransposedText() {
        return transposedText;
    }
}
