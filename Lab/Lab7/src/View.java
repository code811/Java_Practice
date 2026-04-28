import java.util.Scanner;

public class View {

    public void start() {
        Scanner sc = new Scanner(System.in);

        int option = 0;
        do {
            try {
                option = promptMenu(sc);
                doMenu(option, sc);
                System.out.println();
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number listed in digit form!");
            }
        } while(option != 6);
    }

    public int promptMenu(Scanner sc) {
        System.out.println("1. Multiply 2 numbers");
        System.out.println("2. Divide 2 numbers");
        System.out.println("3. Mod 2 numbers");
        System.out.println("4. Echo sentence");
        System.out.println("5. Determine if reverse");
        System.out.println("6. Quit");
        System.out.print("Enter option: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void doMenu(int option, Scanner sc) {
        switch(option) {
            case 1 -> printProduct(sc);
            case 2 -> printQuotient(sc);
            case 3 -> printRemainder(sc);
            case 4 -> printEcho(sc);
            case 5 -> printReverse(sc);
            case 6 -> System.out.println("Shutting off...");
            default -> System.out.println("Enter an appropriate response!");
        }
    }

    // Case 1
    public void printProduct(Scanner sc) {
        System.out.print("Enter the first number: ");
        int factor1 = Integer.parseInt(sc.nextLine());
        System.out.print("Enter the second number: ");
        int factor2 = Integer.parseInt(sc.nextLine());

        int product = RecursionOperations.recursiveMultiply(factor1, factor2);
        System.out.println("Your product is " + product);
    }

    // Case 2
    public void printQuotient(Scanner sc) {
        System.out.print("Enter the first number: ");
        int dividend = Integer.parseInt(sc.nextLine());
        System.out.print("Enter the second number: ");
        int divisor = Integer.parseInt(sc.nextLine());

        int quotient = RecursionOperations.recursiveDivision(dividend, divisor);
            System.out.println("Your quotient is " + quotient);
    }

    // Case 3
    public void printRemainder(Scanner sc) {
        System.out.print("Enter the first number: ");
        int dividend = Integer.parseInt(sc.nextLine());
        System.out.print("Enter the second number: ");
        int divisor = Integer.parseInt(sc.nextLine());

        int remainder = RecursionOperations.recursiveRemainder(dividend, divisor);
        System.out.println("Your modulus is " + remainder);
    }

    // Case 4
    public void printEcho(Scanner sc) {
        System.out.print("Enter your sentence: ");
        String sentence = sc.nextLine();
        System.out.print("Repeat how many times? ");
        int numOfReverberations = Integer.parseInt(sc.nextLine());

        String echo = RecursionOperations.recursiveEcho(sentence, numOfReverberations);
        System.out.println("Your sentence repeated " + numOfReverberations + " times is");
        System.out.println(echo);
    }

    // Case 5
    public void printReverse(Scanner sc) {
        System.out.print("Enter a sentence: ");
        String forwards = sc.nextLine();
        System.out.print("Enter another sentence: ");
        String backwards = sc.nextLine();

        boolean isReverse = RecursionOperations.recursiveReverse(forwards, backwards);
        if(isReverse) {
            System.out.println("The sentences are the opposite of each other.");
        }
        else {
            System.out.println("The sentences are NOT the opposite of each other.");
        }
    }
}
