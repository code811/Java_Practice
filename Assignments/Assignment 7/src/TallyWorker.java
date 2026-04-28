public class TallyWorker implements Runnable {

    private TransactionTallier tt;
    private int id;
    private static int nextId = 0;

    public TallyWorker(TransactionTallier tt) {
        this.tt = tt;
        id = nextId++;
    }

    @Override
    public void run() {
        System.out.println("Worker " + id + " is about to start...");

        for(;;) {
            String transaction = tt.getNextTransaction();
            if(transaction == null) break;

            String[] parsedTransaction = transactionParser(transaction);
            String transactionType = parsedTransaction[0];
            String productName = parsedTransaction[1];
            int quantity = Integer.parseInt(parsedTransaction[2]);
            int price = Integer.parseInt(parsedTransaction[3]);

            int cost = price * quantity;

            switch(transactionType) {
                case "sell" -> tt.addToTotal(cost);
                case "buy" -> tt.addToTotal(-cost);
            }

            tt.updateCategories(productName, quantity);
        }

        System.out.println("Worker " + id + " is done working.");
    }

    private String[] transactionParser(String transaction) {
        String[] transactionSplit = transaction.split(" ");
        String[] transactionParsed = new String[4];

        // Buy or Sell
        transactionParsed[0] = transactionSplit[0];
        // Product name
        transactionParsed[1] = transactionSplit[1];
        for(int i = 2; i < transactionSplit.length - 3; i++) {
            transactionParsed[1] += " " + transactionSplit[i];
        }
        // Quantity
        transactionParsed[2] = transactionSplit[transactionSplit.length - 3];
        // Price
        transactionParsed[3] = transactionSplit[transactionSplit.length - 1].substring(1);

        return transactionParsed;
    }
}
