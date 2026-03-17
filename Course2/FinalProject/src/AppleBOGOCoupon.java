import java.util.List;

public class AppleBOGOCoupon extends Coupon {

    @Override
    public boolean applyCoupon(List<GroceryItem> totalCart) {
        int i = 0;
        for(; i < totalCart.size(); i++) {
            if(totalCart.get(i).getName().equalsIgnoreCase("Apple")) break;
            if(i == totalCart.size()) return false;
        }
        if(totalCart.get(i).getQuantity() < 1) return false;

        setApplicable();
        return isApplicable();
    }

    @Override
    public String toString() {
        return (super.toString() + "Apple Buy-One Get-One Free (BOGO)");
    }
}
