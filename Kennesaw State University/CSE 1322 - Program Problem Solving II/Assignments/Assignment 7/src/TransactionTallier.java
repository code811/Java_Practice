import java.util.ArrayList;
import java.util.HashMap;

public class TransactionTallier {

    private ArrayList<String> transactions;
    private HashMap<String, Integer> categories;
    private int runningTotal;

    public TransactionTallier(ArrayList<String> transactions) {
        this.transactions = transactions;
        runningTotal = 0;
        categories = new HashMap<>();
    }

    public synchronized void addToTotal(int amount) {
        runningTotal += amount;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public int getTransactionListSize() {
        return transactions.size();
    }

    public synchronized String getNextTransaction() {
        if(transactions.isEmpty()) return null;
        return transactions.removeFirst();
    }

    public synchronized void updateCategories(String productName, int quantity) {
        categories.put(productName,
                categories.getOrDefault(productName, 0) + quantity);
    }

    public HashMap<String, Integer> getCategories() {
        return categories;
    }
}
