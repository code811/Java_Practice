import java.util.ArrayList;
import java.util.List;

public class DigitalPaymentProcessingApp {

    public static void main(String[] args) {

        List<PaymentMethod> payments = new ArrayList<>();

        payments.add(new CreditCardPayment("1234567812341234"));
        payments.add(new PayPalPayment("user@email.com"));
        payments.add(new CryptoPayment("0xA34F9C89B72D"));

        PaymentProcessor processor = new PaymentProcessor();

        double[] amounts = {100.00, 75.00, 250.00};

        for(int i = 0; i < payments.size(); i++) {
            processor.executeTransaction(payments.get(i), amounts[i]);
        }

    }
}