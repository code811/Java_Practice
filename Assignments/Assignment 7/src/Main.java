import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Transaction Tallier]");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();

        ArrayList<String> transactionsList = TransactionReader.loadTransactions(fileName);
        if(transactionsList == null) {
            return;
        }
        TransactionTallier tt = new TransactionTallier(transactionsList);
        System.out.println("Transactions loaded.");

        System.out.print("Create how many workers? ");
        try {
            int numOfWorkers = Integer.parseInt(sc.nextLine());

            ArrayList<Thread> tallyWorkers = new ArrayList<Thread>();
            for(int i = 0; i < numOfWorkers; i++) {
                tallyWorkers.add(new Thread(new TallyWorker(tt)));
            }
            System.out.println("Workers created. Press 'enter' to start tallying...");
            sc.nextLine();

            System.out.println("Starting workers...");
            int id = 0;
            for(Thread tallyWorker : tallyWorkers) {
                tallyWorker.start();
            }

            for(Thread tallyWorker : tallyWorkers) {
                tallyWorker.join();
            }

            System.out.println();

            System.out.println("All workers are done working.");
            System.out.println("Transaction total is $" + tt.getRunningTotal());
            System.out.println();
            System.out.println("Categories and their quantities: ");
            for(String key : tt.getCategories().keySet()) {
                System.out.println(key + " : " + tt.getCategories().get(key));
            }
            System.out.println();

        } catch (NumberFormatException e) {
            System.out.println("Enter an appropriate response in digit form!");
        } catch (InterruptedException e) {
            System.out.println("A tally worker was interrupted!");;
        }

        System.out.println("Program complete.");
    }
}