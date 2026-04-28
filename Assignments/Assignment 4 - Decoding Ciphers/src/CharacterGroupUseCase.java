import java.util.List;

public class CharacterGroupUseCase {

    private final List<CharacterGroupTable> charGroups = List.of(
            new Group1(),
            new Group2(),
            new Group3(),
            new Group4(),
            new Group5(),
            new Group6(),
            new Group7()
    );

    public CipherCharacter getGroupData(char character) {
        for(int i = 0; i < charGroups.size(); i++) {
            CharacterGroupTable group = charGroups.get(i);
            for(int j = 0; j < group.getSize(); j++) {
                if(character == group.getChar(j)) {
                    return new CipherCharacter(i, j);
                }
            }
        }
        return null;
    }

    public char getCharOffset(CipherCharacter charData, int offset) {
        CharacterGroupTable group = charGroups.get(charData.getGroupNum());

        offset = (offset + charData.getIndex()) % group.getSize();
        if(offset < 0) {
            offset += group.getSize();
        }
        return group.getCharOffset(offset);
    }
}
