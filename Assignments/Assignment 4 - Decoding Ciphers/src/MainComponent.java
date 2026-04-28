import java.util.List;

public class MainComponent {

    private List<Mendec> mendecList;

    public MainComponent(List<Mendec> mendecList) {
        this.mendecList = mendecList;
    }

    public void addMendecList(Mendec mendec) {
        mendecList.add(mendec);
    }

    public Mendec searchList(int id) {
        for(Mendec mendec : mendecList) {
            if(mendec.getId() == id) {
                return mendec;
            }
        }
        return null;
    }

    public List<Mendec> getList() {
        return mendecList;
    }

    public String encryptMessage(String sentence, int id) {
        return searchList(id).encrypt(sentence);
    }

    public String decryptMessage(String sentence, int id) {
        return searchList(id).decrypt(sentence);
    }

    public void removeMendec(int id) {
        mendecList.remove(searchList(id));
    }
}
