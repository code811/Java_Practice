public class CaesarCipher extends Mendec {

    private int offset;
    private CharacterGroupUseCase groupUseCase = new CharacterGroupUseCase();

    public CaesarCipher(String name, int offset) {
        String description = String.format("A Caesar Cypher with an offset of %s.", offset);
        super(name, description);
        this.offset = offset;
    }

    @Override
    public String decrypt(String sentence) {
        String base = "";
        for(int i = 0; i < sentence.length(); i++) {
            if(sentence.charAt(i) == ' ') {
                base += " ";
                continue;
            }
            CipherCharacter charData = groupUseCase.getGroupData(sentence.charAt(i));
            char offsetChar = groupUseCase.getCharOffset(charData, -(offset));
            base += offsetChar;
        }
        return base;
    }

    @Override
    public String encrypt(String sentence) {
        String base = "";
        for(int i = 0; i < sentence.length(); i++) {
            if(sentence.charAt(i) == ' ') {
                base += " ";
                continue;
            }
            CipherCharacter charData = groupUseCase.getGroupData(sentence.charAt(i));
            char offsetChar = groupUseCase.getCharOffset(charData, offset);
            base += offsetChar;
        }
        return base;
    }

    @Override
    public String toString() {
        return super.toString() + "Caesar Cipher";
    }
}
