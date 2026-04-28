// Pure UI
import java.util.Scanner;

public class BankUI {

    private final Scanner sc = new Scanner(System.in);

    private Checking checking;

    private Savings savings;

    public BankUI(Checking checking, Savings savings) {
        this.checking = checking;
        this.savings = savings;
    }

    public BankUI(Checking checking) {
        this.checking = checking;
    }

    public BankUI(Savings savings) {
        this.savings = savings;
    }

    public static void printHeader() {
        System.out.println("[Banking System]");
    }

    public int promptMenu() {
        System.out.println();
        System.out.println("1. Withdraw from Checking");
        System.out.println("2. Withdraw from Savings");
        System.out.println("3. Deposit to Checking");
        System.out.println("4. Deposit to Savings");
        System.out.println("5. Balance of Checking");
        System.out.println("6. Balance of Savings");
        System.out.println("7. Award Interest to Savings");
        System.out.println("8. Quit");
        System.out.print("Select option: ");
        int option = Integer.parseInt(sc.nextLine());
        System.out.println();
        return option;
    }

    // Case 1
    public double promptWithdrawChecking() {
        System.out.print("How much would you like to withdraw from Checking? $");
        return Double.parseDouble(sc.nextLine());
    }

    public void printCheckingOverdraft(double minimumBalance, double overdraftFee) {
        System.out.printf("Charging an overdraft fee of $%.2f because account is below $%.2f%n", overdraftFee, minimumBalance);
    }

    // Case 2
    public double promptWithdrawSavings() {
        System.out.print("How much would you like to withdraw from Savings? $");
        return Double.parseDouble(sc.nextLine());
    }

    public void printSavingsOverdraft(double minimumBalance, double overdraftFee) {
        System.out.printf("Charging a fee of $%.2f because you are below $%.2f%n", overdraftFee, minimumBalance);
    }

    // Case 3
    public double promptDepositToChecking() {
        System.out.print("How much would you like to deposit to Checking? $");
        return Double.parseDouble(sc.nextLine());
    }

    // Case 4
    public double promptDepositToSavings() {
        System.out.print("How much would you like to deposit to Savings? $");
        return Double.parseDouble(sc.nextLine());
    }

    public void printNumOfDeposits(int numberOfDeposits) {
        System.out.println("This is deposit " + numberOfDeposits + " to this account");
    }

    public void printExcessTransactions(double excessiveTransactionFee) {
        System.out.printf("Charging a fee of $%.2f%n", excessiveTransactionFee);
    }

    // Case 5
    public void printBalanceChecking() {
        System.out.println(checking);
        System.out.println();
    }

    // Case 6
    public void printBalanceSaving() {
        System.out.println(savings);
        System.out.println();
    }

    // Case 7
    public void printAwardInterest(double interest) {
        System.out.printf("Customer earned $%.2f in interest%n", interest);
        printCurrentBalanceSavings();
    }

    // Case 8
    public void printQuit() {
        System.out.println("Shutting off...");
    }

    // Default
    public void printInvalid() {
        System.out.println("Invalid option.");
    }

    public void printCurrentBalanceSavings() {
        System.out.printf("Current balance of Savings is $%.2f%n", savings.getAccountBalance());
    }

    public void printCurrentBalanceChecking() {
        System.out.printf("Current balance of Checking is $%.2f%n", checking.getAccountBalance());
    }
}
