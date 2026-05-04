import java.util.List;

public class Group3 implements CharacterGroupTable {

    private final List<Character> characters = List.of(
            ':', ';', '<', '=', '>', '?', '@');

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
