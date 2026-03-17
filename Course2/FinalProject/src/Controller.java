import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<GroceryItem> groceryList;
    private List<GroceryItem> filteredList;
    private Cart cart;

    public Controller(GroceryList groceryList, Cart cart) throws InvalidPriceException, InvalidNameException {
        this.groceryList = groceryList.getGroceryList();
        filteredList = this.groceryList;
        this.cart = cart;
    }

    public boolean addToCart(String userInput, int quantity) throws InvalidNameException{
        int index = searchGroceries(userInput);
        if(index == -1) {
            return false;
        }

        if(!checkQuantity(index, quantity)) {
            return false;
        }

        cart.add(index, quantity);
        return true;
    }

    public int searchGroceries(String userInput) {
        for(int i = 0; i < groceryList.size(); i++) {
            String groceryName = groceryList.get(i).getName();
            if(userInput.equalsIgnoreCase(groceryName)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkQuantity(int index, int quantity) {
        return (quantity <= groceryList.get(index).getQuantity()) && (groceryList.get(index).getQuantity() != 0);
    }

    public void filterGroceryList(float price) {
        filteredList = new ArrayList<>();
        for(GroceryItem grocery : groceryList) {
            if(grocery.getPrice() <= price) {
                filteredList.add(grocery);
            }
        }
    }

    public float calculateAverage() {
        float total = 0;
        for(GroceryItem grocery : groceryList) {
            total += grocery.getPrice();
        }
        return (total / groceryList.size());
    }

    public List<GroceryItem> getGroceryList() {
        return groceryList;
    }

    public List<GroceryItem> getFilteredList() {
        return filteredList;
    }

    public Cart getCart() {
        return cart;
    }
}
