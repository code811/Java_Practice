public interface PaymentMethod {

    void processPayment(double amount);

    void refund(double amount);

    String getPaymentDetails();
}
