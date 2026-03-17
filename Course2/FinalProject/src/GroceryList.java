import java.util.List;

public class GroceryList {

    public List<GroceryItem> getGroceryList() throws InvalidNameException, InvalidPriceException {
        return List.of(
                new GroceryItem("Apple", 1.29f),
                new GroceryItem("Banana", 0.87f),
                new GroceryItem("Cherry", 0.05f),
                new GroceryItem("Blueberry", 1.54f),
                new GroceryItem("Mango", 2.56f),
                new GroceryItem("Pineapple", 1.23f),
                new GroceryItem("Kiwi", 3.47f),
                new GroceryItem("Starfruit", 3.88f),
                new GroceryItem("Dragonfruit", 1.34f),
                new GroceryItem("Grapes", 0.99f),
                new GroceryItem("Lychee", 1.25f),
                new GroceryItem("Orange", 2.27f),
                new GroceryItem("Guava", 0.98f)
        );
    }
}
