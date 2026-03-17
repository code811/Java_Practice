import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<GroceryItem> groceryList;
    private List<GroceryItem> totalCart = new ArrayList<>();

    public Cart(GroceryList groceryList) throws InvalidNameException, InvalidPriceException {
        this.groceryList = groceryList.getGroceryList();
    }

    public void add(int index, int quantity) throws InvalidNameException {
        GroceryItem grocery = groceryList.get(index);
        grocery.purchaseQuantity(quantity);

        int inCartIndex = checkCart(grocery);
        if(inCartIndex == -1) {
            totalCart.add(new GroceryItem(grocery.getName(), quantity));
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

    public List<GroceryItem> getTotalCart() {
        return totalCart;
    }

    public float getTotalCost() {
        float total = 0;
        for(GroceryItem grocery : totalCart) {
            total += grocery.getCost();
        }
        return total;
    }

    public boolean applyCoupon(int couponChoice) {
        switch(couponChoice) {
            case 1 -> { // Apples on BOGO
                for(GroceryItem grocery : totalCart) {
                    boolean valid = Coupon.applyAppleBOGO(grocery); // instance method
                    if(valid) {
                        return true;
                    }
                }
                return false;
            }
            case 2 -> {

            }
        }
    }
}
