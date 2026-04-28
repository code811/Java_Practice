import java.util.List;

public class Group6 implements CharacterGroupTable {

    private final List<Character> characters = List.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

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
