import java.util.List;

public class CostMoreThanTenCoupon extends Coupon{

    @Override
    public boolean applyCoupon(Cart cart) {
        if(cart.getTotalCost() < 10) return false;

        setApplicable();
        return isApplicable();
    }

    @Override
    public float getDiscount(Cart cart) {
        if(!isApplicable()) {
            return 0;
        }

        return 2.00f;
    }

    @Override
    public String toString() {
        return (super.toString() + "Costs More Than $10.00");
    }
}
