import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Mendec> mendecList = new ArrayList<Mendec>();
        MainComponent mainComponent = new MainComponent(mendecList);
        View view = new View(mainComponent);

        view.start();
    }
}