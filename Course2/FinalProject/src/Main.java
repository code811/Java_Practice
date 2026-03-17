import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            List<GroceryItem> groceryList = new GroceryList().getGroceryList();
        }
        catch(InvalidNameException | InvalidPriceException e) {
            System.out.println(e.getMessage());
        }

    }
}