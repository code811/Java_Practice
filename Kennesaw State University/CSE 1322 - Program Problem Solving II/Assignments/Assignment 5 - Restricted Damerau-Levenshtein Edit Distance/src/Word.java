public class Word {
    public final String originalWord;
    public final String candidateWord;
    public final int editDistance;

    public Word(String originalWord, String candidateWord, int editDistance) {
        this.originalWord = originalWord;
        this.candidateWord = candidateWord;
        this.editDistance = editDistance;
    }
}
