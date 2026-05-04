import java.util.List;

public class Group2 implements CharacterGroupTable {

    private final List<Character> characters = List.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

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
