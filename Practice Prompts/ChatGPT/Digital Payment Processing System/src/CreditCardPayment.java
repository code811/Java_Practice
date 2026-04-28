public class CreditCardPayment extends AbstractPayment {


    private static final String PAYMENT_TYPE = "Credit Card";
    public static final double TRANSACTION_FEE = 0.02;

    private final String cardNumber;


    public CreditCardPayment(String cardNumber) {
        super(TRANSACTION_FEE);
        this.cardNumber = cardNumber;
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
        System.out.printf("Refunding $%.2f to card ending in %s%n", amount, cardNumber.substring(cardNumber.length() - 4));
    }


    @Override
    public String getPaymentDetails() {
        return ("Payment Type: " + PAYMENT_TYPE + '\n' + "Card Ending In: " + cardNumber.substring(cardNumber.length() - 4));
    }
}
