import java.util.List;

public class FiveOrMoreItemsCoupon extends Coupon {

    @Override
    public boolean applyCoupon(Cart cart) {
        List<GroceryItem> totalCart = cart.getTotalCart();
        int totalQuantity = 0;
        for(GroceryItem grocery : totalCart) {
            totalQuantity += grocery.getQuantity();
        }

        if(totalQuantity < 5) {
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
