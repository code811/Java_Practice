public class KeyUseCase {

    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String capitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public int getKeyOffset(char letter) {
        if('A' < letter && letter < 'Z') {
            for(int i = 0; i < alphabet.length(); i++) {
                if (letter == capitalAlphabet.charAt(i)) {
                    return capitalAlphabet.indexOf(i);
                }
            }
        }
        for(int i = 0; i < alphabet.length(); i++) {
            if (letter == alphabet.charAt(i)) {
                return alphabet.indexOf(i);
            }
        }
        return -1;
    }

    public int getAlphabetLength() {
        return alphabet.length();
    }
}
