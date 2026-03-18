import java.util.List;
import java.util.Scanner;

public class View {

    public void start(Controller controller) {
        boolean continueLoop = true;
        do {
            Scanner sc = new Scanner(System.in);
            String userInput;
            do {
                userInput = promptMenu(sc);
                if(userInput.equalsIgnoreCase("Complete")) {
                    break;
                }
                System.out.println();
                doMenu(controller, sc, userInput);
                System.out.println();
            } while(!userInput.equalsIgnoreCase("Complete"));

            System.out.print("Would you like to repeat the program? Enter 'Exit' if you wish to end: ");
            if(sc.nextLine().equalsIgnoreCase("Exit")) {
                continueLoop = false;
            }
        } while(continueLoop);
        System.out.println("Thank you.");
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
        switch(userInput.toLowerCase()) {
            case "1" -> promptAddToCart(controller, sc);
            case "2" -> printCurrentCart(controller);
            case "3" -> printSearchForItem(controller, sc);
            case "4" -> printAverageCostOfGroceries(controller);
            case "5" -> promptFilterGroceryList(controller, sc);
            case "6" -> promptApplyCoupon(controller, sc);
            case "7" -> printItemsOnHand(controller);
            case "complete" -> {}
            default -> System.out.println("Invalid Choice!");
        }
    }

    // Case 1
    private void promptAddToCart(Controller controller, Scanner sc) {
        try {
            printGroceries(controller.getFilteredList());
            System.out.print("What would you like to add?: ");
            String grocery = sc.nextLine();
            if(controller.searchGroceries(grocery) == -1) throw new InvalidNameException(grocery + " is not listed!");

            System.out.print("How much would you like to purchase?: ");
            int quantity = Integer.parseInt(sc.nextLine());
            int groceryIdx = controller.searchGroceries(grocery);
            if(quantity > controller.getGroceryList().get(groceryIdx).getQuantity()) throw new InvalidQuantityException("Inappropriate input!");


            if(!controller.addToCart(grocery, quantity)) {
                System.out.println(grocery + " was unable to be added to the cart.");
                return;
            }
            System.out.println("Successfully added to cart!");
        }
        catch(InvalidNameException | InvalidQuantityException e) {
            System.out.println(e.getMessage());
        }
        catch(NumberFormatException e) {
            System.out.println("Please enter the digit form of the number!");
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
        System.out.println(" Amount     Grocery     $Price");
        System.out.println("-------------------------------");
        for(GroceryItem grocery : totalCart) {
            System.out.printf("%4d%14s%11.2f\n", grocery.getQuantity(), grocery.getName(), grocery.getCost());
        }

        float totalDiscount = controller.getCart().getTotalDiscount();
        if(totalDiscount > 0) {
            System.out.printf("Coupons applied: -$%.2f\n", totalDiscount);
        }
        System.out.printf("For a grand total of: $%.2f\n", controller.getCart().getTotalCost());
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
        System.out.printf("The average cost of groceries is: $%.2f\n", controller.calculateAverage());
    }

    // Case 5
    private void promptFilterGroceryList(Controller controller, Scanner sc) {
        System.out.print("What price would you like to filter your results by? (Enter '-1' to reset filter): ");
        float price = Float.parseFloat(sc.nextLine());

        if(price == -1) {
            controller.filterGroceryList(1000000f);
            System.out.println("Filter reset!");
            return;
        }

        controller.filterGroceryList(price);
        System.out.println("The groceries at or below $" + price + " is: ");
        for(GroceryItem grocery : controller.getFilteredList()) {
            System.out.println(grocery);
        }
    }

    // Case 6
    private void promptApplyCoupon(Controller controller, Scanner sc) {
        System.out.println("Current coupons active: ");
        Cart cart = controller.getCart();
        List<Coupon> coupons = cart.getCoupons();

        for(int i = 0; i < coupons.size(); i++) {
            System.out.printf("%d. %s %-40b\n", (i + 1), coupons.get(i), coupons.get(i).isApplicable());
        }

        System.out.println("------------------------");
        try {
            System.out.print("Which coupon would you like to apply?: ");
            int couponChoice = Integer.parseInt(sc.nextLine());
            if(cart.selectCoupon(couponChoice)) {
                System.out.println("Coupon successfully applied!");
            }
            else {
                System.out.println("The coupon was unsuccessful/removed.");
            }
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid choice!");
        }
        catch(EmptyCartException e) {
            System.out.println(e.getMessage());
        }
    }

    // Case 7
    private void printItemsOnHand(Controller controller) {
        List<GroceryItem> groceryList = controller.getGroceryList();
        for(GroceryItem grocery : groceryList) {
            System.out.println(grocery);
        }
    }

}
