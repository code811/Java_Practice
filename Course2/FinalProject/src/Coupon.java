import java.util.List;

public abstract class Coupon {

    private boolean applicable = false;

    public boolean isApplicable() {
        return applicable;
    }

    public void setApplicable() {
        applicable = !applicable;
    }

    public abstract boolean applyCoupon(List<GroceryItem> totalCart);

    @Override
    public String toString() {
        return "Coupon: ";
    }
}
