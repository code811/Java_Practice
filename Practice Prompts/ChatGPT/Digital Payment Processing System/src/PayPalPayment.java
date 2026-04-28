public class PayPalPayment extends AbstractPayment {

    private static final String PAYMENT_TYPE = "PayPal";
    private static final double TRANSACTION_FEE = 0.03;
    private final String email;


    public PayPalPayment(String email) {
        super(TRANSACTION_FEE);
        this.email = email;
    }


    @Override
    public void processPayment(double amount) {
        System.out.println("Processing " + PAYMENT_TYPE + " payment of $" + amount);

        double finalAmount = calculateFinalAmount(amount);

        System.out.println("Transaction fee: " + (TRANSACTION_FEE * 100) + "%");
        System.out.printf("Final amount charged: $%.2f%n", finalAmount);
        System.out.println("Payment successful.");
    }

    @Override
    public void refund(double amount) {
        System.out.printf("Refunding $%.2f to PayPal account: %s%n", amount, email);
    }


    @Override
    public String getPaymentDetails() {
        return ("Payment Type: " + PAYMENT_TYPE + '\n' + "Account Email: " + email);
    }
}
