import java.util.List;

public abstract class Coupon {

    private boolean applicable = false;

    public boolean isApplicable() {
        return applicable;
    }

    public void setApplicable() {
        applicable = !applicable;
    }

    public abstract boolean applyCoupon(Cart cart);
    public abstract float getDiscount(Cart cart);

    @Override
    public String toString() {
        return "Coupon: ";
    }
}
