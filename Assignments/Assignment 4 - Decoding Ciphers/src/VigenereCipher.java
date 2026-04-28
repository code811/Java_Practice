public class VigenereCipher extends Mendec {

    private String key;
    private KeyUseCase keyUseCase = new KeyUseCase();

    public VigenereCipher(String name, String key) {
        String description = String.format("A Vigenere Cipher with key '%s'.", key);
        super(name, description);
        this.key = key;
    }

    @Override
    public String decrypt(String sentence) {
        String base = "";
        for(int i = 0; i < sentence.length(); i++) {
            int keyOffset = -(keyUseCase.getKeyOffset(key.charAt(i % key.length())));

            if(keyOffset == -1) {
                base += (sentence.charAt(i));
            }

            char offsetChar;
            if('A' < sentence.charAt(i) && sentence.charAt(i) > 'Z') {
                 offsetChar = (char)((sentence.charAt(i) + keyOffset) % 'Z');
            }
            else {
                offsetChar = (char)((sentence.charAt(i) + keyOffset) % 'z');
            }

            base += offsetChar;
        }

        return base;
    }

    @Override
    public String encrypt(String sentence) {
        String base = "";
        for(int i = 0; i < sentence.length(); i++) {
            int keyOffset = keyUseCase.getKeyOffset(key.charAt(i % key.length()));

            if(keyOffset == -1) {
                base += (sentence.charAt(i));
            }

            char offsetChar;
            if('A' < sentence.charAt(i) && sentence.charAt(i) > 'Z') {
                offsetChar = (char)((sentence.charAt(i) + keyOffset) % 'Z');
            }
            else {
                offsetChar = (char)((sentence.charAt(i) + keyOffset) % 'z');
            }

            base += offsetChar;
        }

        return base;
    }



    @Override
    public String toString() {
        return super.toString() + "Vigenere Cipher";
    }
}
