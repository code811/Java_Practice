import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<GroceryItem> groceryList;
    private List<GroceryItem> totalCart = new ArrayList<>();
    private List<Coupon> coupons = List.of(
            new AppleBOGOCoupon(),
            new FiveOrMoreItemsCoupon(),
            new CostMoreThanTenCoupon()
    );

    public Cart(GroceryList groceryList) throws InvalidNameException, InvalidPriceException {
        this.groceryList = groceryList.getGroceryList();
    }

    public void add(int index, int quantity) throws InvalidNameException {
        GroceryItem grocery = groceryList.get(index);
        grocery.purchaseQuantity(quantity);

        int inCartIndex = checkCart(grocery);
        if(inCartIndex == -1) {
            totalCart.add(new GroceryItem(grocery.getName(), grocery.getPrice(), quantity));
            return;
        }

        totalCart.get(inCartIndex).addQuantity(quantity);
    }

    public int checkCart(GroceryItem grocery) {
        for(int i = 0; i < totalCart.size(); i++) {
            if(totalCart.get(i).getName().equals(grocery.getName())) {
                return i;
            }
        }
        return -1;
    }

    public boolean selectCoupon(int couponChoice) {
        return switch(couponChoice - 1) {
            case 0 -> coupons.get(0).applyCoupon(this);
            case 1 -> coupons.get(1).applyCoupon(this);
            case 2 -> coupons.get(2).applyCoupon(this);
            default -> false;
        };
    }

    public List<GroceryItem> getTotalCart() {
        return totalCart;
    }

    public float getTotalCost() {
        float total = 0;
        for(GroceryItem grocery : totalCart) {
            total += grocery.getCost();
        }

        for(Coupon coupon : coupons) {
            if(coupon.isApplicable()) {
                total -= coupon.getDiscount(this);
            }
        }
        return total;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }
}
