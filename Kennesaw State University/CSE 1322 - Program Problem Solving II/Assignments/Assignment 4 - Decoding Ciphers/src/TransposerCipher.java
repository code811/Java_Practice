public class TransposerCipher extends Mendec {

    private int width;
    private TransposerTable transposerTable;

    public TransposerCipher(String name, int width) {
        String description = String.format("A Transposer Cipher with width %d.", width);
        super(name, description);
        this.width = width;
    }

    @Override
    public String decrypt(String sentence) {
        return encrypt(sentence);
    }

    @Override
    public String encrypt(String sentence) {
        createTransposerTable(sentence);
        String base = "";
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < transposerTable.getTransposedText().length; j++) {
                base += transposerTable.getTransposedText()[i][j];
            }

        }
        return base;
    }

    private void createTransposerTable(String sentence) {
        int row = Math.ceilDiv(sentence.length(), width);

        transposerTable = new TransposerTable(row, width);

        int index = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < width; j++) {
                if(sentence.length() < index) {
                    transposerTable.getTransposedText()[i][j] = '.';
                }
                transposerTable.getTransposedText()[i][j] = sentence.charAt(index++);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Transposer Cipher";
    }
}
