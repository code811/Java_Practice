public class PaymentProcessor {

    public static void executeTransaction(PaymentMethod method, double amount) {

        System.out.println("========================================");
        System.out.println(method.getPaymentDetails());
        System.out.println();

        method.processPayment(amount);
        System.out.println();
        method.refund(amount / 2.0);

        System.out.println("========================================");
        System.out.println();
    }
}
