import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidPriceException, InvalidNameException {
        GroceryList groceryList = new GroceryList();
        Cart cart = new Cart(groceryList);
        Controller controller = new Controller(groceryList, cart);
        View view = new View();
        view.start(controller);
    }
}