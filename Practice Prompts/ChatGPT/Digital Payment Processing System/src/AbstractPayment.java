public abstract class AbstractPayment implements PaymentMethod {

    protected double transactionFee;

    public AbstractPayment(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public double calculateFinalAmount(double amount) {
        return amount + (amount * transactionFee);
    }

    @Override
    public abstract void processPayment(double amount);
}
