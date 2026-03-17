import java.util.List;

public class CostMoreThanTenCoupon extends Coupon{

    @Override
    public boolean applyCoupon(List<GroceryItem> totalCart) {
        float totalCost = 0;
        for(GroceryItem grocery : totalCart) {
            totalCost += (grocery.getCost());
        }
        if(totalCost < 10) return false;

        setApplicable();
        return isApplicable();
    }

    @Override
    public String toString() {
        return (super.toString() + "Costs More Than $10.00");
    }
}
