public class CryptoPayment implements PaymentMethod {

    private static final String PAYMENT_TYPE = "Crypto";
    private static final double NETWORK_FEE = 0.01;

    private String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    private double calculateFinalAmount(double amount) {
        return amount + (amount * NETWORK_FEE);
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing crypto payment of $" + amount);

        double finalAmount = calculateFinalAmount(amount);

        System.out.println("Network fee: " + (NETWORK_FEE * 100) + '%');
        System.out.printf("Final amount deducted: $%.2f%n", finalAmount);
        System.out.println("Transaction confirmed on blockchain.");
    }

    @Override
    public void refund(double amount) {
        System.out.printf("Initiating crypto refund of $%.2f to wallet %s%n", amount, walletAddress.substring(0, 6) + "...");

    }

    @Override
    public String getPaymentDetails() {
        return ("Payment Type: " + PAYMENT_TYPE + '\n' + "Wallet Address: " + walletAddress.substring(0,6) + "...");
    }
}
