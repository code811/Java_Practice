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
    private float totalDiscount = 0;

    public Cart(GroceryList groceryList) {
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

    public boolean selectCoupon(int couponChoice) throws EmptyCartException {
        if(totalCart.isEmpty()) throw new EmptyCartException("Please add an item to your cart first!");

        return coupons.get(couponChoice - 1).applyCoupon(this);
    }

    public List<GroceryItem> getTotalCart() {
        return totalCart;
    }

    public float getTotalCost() {
        float total = 0;
        for(GroceryItem grocery : totalCart) {
            total += grocery.getCost();
        }

        return (total - totalDiscount);
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public float getTotalDiscount() {
        totalDiscount = 0;
        for(Coupon coupon : coupons) {
            if(coupon.isApplicable()) {
                totalDiscount += coupon.getDiscount(this);
            }
        }
        return totalDiscount;
    }
}
