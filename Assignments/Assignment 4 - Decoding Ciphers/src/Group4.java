import java.util.List;

public class Group4 implements CharacterGroupTable {

    private final List<Character> characters = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    @Override
    public int getSize() {
        return characters.size();
    }

    @Override
    public int getChar(int index) {
        return characters.get(index);
    }

    @Override
    public char getCharOffset(int offset) {
        return characters.get(offset);
    }
}
