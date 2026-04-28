import java.util.List;
import java.util.Scanner;

public class FibView {

    private final Scanner sc = new Scanner(System.in);
    private final List<FindFib> fibonacciSequence;

    public FibView(List<FindFib> fibonacciSequence) {
        this.fibonacciSequence = fibonacciSequence;
    }

    public void start() {
        int position = promptPosition();

        for(FindFib fib : fibonacciSequence) {
            printFib(fib, position);
            System.out.println();
        }

        System.out.println("Program complete.");
    }

    public int promptPosition() {
        System.out.print("Find which position in the Fibonacci Sequence? ");
        return Integer.parseInt(sc.nextLine());
    }

    public void printFib(FindFib fib, int position) {
        System.out.printf("Fib of 22 using %s is %d", fib, fib.calculateFib(position));
    }
}
