import java.util.List;
import java.util.Scanner;

public class View {

    public void start(Controller controller) {
        boolean continueLoop = true;
        do {
            Scanner sc = new Scanner(System.in);
            String userInput = "";
            do {
                userInput = promptMenu(sc);
                if(userInput.equalsIgnoreCase("Complete")) {
                    break;
                }

            } while(!userInput.equalsIgnoreCase("Complete"));


            System.out.print("Would you like to repeat the program? Enter 'Exit' if you wish to end: ");
            if(sc.nextLine().equalsIgnoreCase("Exit")) {
                continueLoop = false;
            }
        } while(continueLoop);
    }

    private String promptMenu(Scanner sc) {
        System.out.println("1. Add to Cart");
        System.out.println("2. Current Cart");
        System.out.println("3. Search for Item");
        System.out.println("4. Average Cost of Groceries");
        System.out.println("5. Filter Items by Price");
        System.out.println("6. Apply coupon");
        System.out.println("7. View Items on Hand");
        System.out.print("Which option would you like? Enter 'Complete to finish: ");
        return sc.nextLine();
    }

    private void doMenu(Controller controller, Scanner sc, String userInput) {
        switch(userInput) {
            case "1" -> promptAddToCart(controller, sc);
            case "2" -> printCurrentCart(controller);
            case "3" -> printSearchForItem(controller, sc);
            case "4" -> printAverageCostOfGroceries(controller);
            case "5" -> promptFilterGroceryList(controller, sc);
            case "6" ->
        }
    }

    // Case 1
    private void promptAddToCart(Controller controller, Scanner sc) {
        try {
            printGroceries(controller.getFilteredList());
            System.out.print("What would you like to add?: ");
            String grocery = sc.nextLine();
            System.out.print("How much would you like to purchase?: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if(!controller.addToCart(grocery, quantity)) {
                System.out.println(grocery + " was unable to be added to the cart.");
            }
            System.out.println("Successfully added to cart!");
        }
        catch(InvalidNameException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printGroceries(List<GroceryItem> groceryList) {
        for(int i = 0; i < groceryList.size(); i++) {
            System.out.println(groceryList.get(i));
        }
    }

    // Case 2
    private void printCurrentCart(Controller controller) {
        List<GroceryItem> totalCart = controller.getCart().getTotalCart();

        System.out.println("Currently, your cart holds: ");
        System.out.println("Amount\t\tGrocery\t\tPrice");
        System.out.println("----------------------------------------");
        for(GroceryItem grocery : totalCart) {
            System.out.println(grocery.getQuantity() + "\t\t" + grocery.getName() + "\t\t" + grocery.getCost());
        }
        System.out.println("For a grand total of: " + controller.getCart().getTotalCost());
    }

    // Case 3
    private void printSearchForItem(Controller controller, Scanner sc) {
        System.out.print("What item are you searching for?: ");
        String groceryToFind = sc.nextLine();

        int groceryIndex = controller.searchGroceries(groceryToFind);
        if(groceryIndex == -1) {
            System.out.println("Unfortunately, we do not have that item.");
            return;
        }

        System.out.println("This product matches the description you gave us: ");
        System.out.println(controller.getGroceryList().get(groceryIndex));
    }

    // Case 4
    private void printAverageCostOfGroceries(Controller controller) {
        System.out.printf("The average cost of groceries is: $%.2f", controller.calculateAverage());
    }

    // Case 5
    private void promptFilterGroceryList(Controller controller, Scanner sc) {
        System.out.println("What price would you like to filter your results by? (Enter '0' to reset filter): ");
        float price = Float.parseFloat(sc.nextLine());

        controller.filterGroceryList(price);
        System.out.println("The groceries at or below $" + price + " is: ");
        for(GroceryItem grocery : controller.getFilteredList()) {
            System.out.println(grocery);
        }
    }

    // Case 6
    private void promptApplyCoupon() {

    }


}
