import java.util.List;

public class AppleBOGOCoupon extends Coupon {

    @Override
    public boolean applyCoupon(Cart cart) {
        int i = 0;
        List<GroceryItem> totalCart = cart.getTotalCart();
        for(; i < totalCart.size(); i++) {
            if(totalCart.get(i).getName().equalsIgnoreCase("Apple")) break;
            if(i == totalCart.size()) return false;
        }
        if(totalCart.get(i).getQuantity() < 1) return false;

        setApplicable();
        return isApplicable();
    }

    @Override
    public float getDiscount(Cart cart) {
        if(!isApplicable()) {
            return 0;
        }

        int i = 0;
        for(; i < cart.getTotalCart().size(); i++) {
            GroceryItem grocery = cart.getTotalCart().get(i);
            if(grocery.getName().equalsIgnoreCase("Apple")) {
                break;
            }
        }

        GroceryItem apple = cart.getTotalCart().get(i);
        int quantity = apple.getQuantity();
        float discount = Math.ceilDiv(quantity, 2);
        return (apple.getPrice() - (discount * apple.getPrice()));
    }

    @Override
    public String toString() {
        return (super.toString() + "Apple Buy-One Get-One Free (BOGO)");
    }
}
