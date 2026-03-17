import java.util.List;

public class FiveOrMoreItemsCoupon extends Coupon {

    @Override
    public boolean applyCoupon(Cart cart) {
        if(cart.getTotalCart().size() < 5) {
            return false;
        }

        setApplicable();
        return isApplicable();
    }

    @Override
    public float getDiscount(Cart cart) {
        if(!isApplicable()) {
            return 0;
        }

        return (cart.getTotalCost() * 0.20f);
    }

    @Override
    public String toString() {
        return (super.toString() + "Five or More Items in Cart");
    }
}
