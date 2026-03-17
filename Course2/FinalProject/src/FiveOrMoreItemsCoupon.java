import java.util.List;

public class FiveOrMoreItemsCoupon extends Coupon {

    @Override
    public boolean applyCoupon(List<GroceryItem> totalCart) {
        return (totalCart.size() >= 5);
    }

    @Override
    public String toString() {
        return (super.toString() + "Five or More Items in Cart");
    }
}
