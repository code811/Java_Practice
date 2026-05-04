// Handles View/UI
public class AppDisplay {

    public static void displayHeader() {
        System.out.println("[Theme Park Wallet Manager]");
    }

    public static void displayMenu(ParkWallet wallet) {
        System.out.println("1. Add tickets");
        System.out.println("2. Set tickets");
        // Changes whether holiday is true/false
        System.out.println((ParkWallet.getHoliday() ? "3. Buy prize at holiday prices!" : "3. Buy prize"));
        System.out.println("4. Set holiday");
        System.out.println("5. Quit");
        System.out.println("Your wallet has " + wallet.getTickets() + " tickets");
        System.out.print("Enter option: ");
    }

    // Automatically adds and prints available items added to Merchandise list
    public static void displayShop() {
        for(int i = 0; i < Shop.getMerchandise().size(); i++) {
            ItemForSale item = Shop.getMerchandise().get(i);
            int price = item.getPrice();

            System.out.println((i + 1) + ". " + item + " (" + price + " tickets)");
        }
        System.out.print("Buy which prize? ");
    }
}