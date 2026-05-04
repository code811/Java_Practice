import java.util.List;

public class Main {
    public static void main(String[] args) {
        FibView view = new FibView(FibonacciSequence.getFibonacciSequence());

        view.start();
    }
}