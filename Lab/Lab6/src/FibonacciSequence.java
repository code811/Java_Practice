import java.util.List;

public class FibonacciSequence {

    private static final List<FindFib> fibonacciSequence = List.of(
            new FibIteration(),
            new FibFormula()
    );

    public static List<FindFib> getFibonacciSequence() {
        return fibonacciSequence;
    }
}
