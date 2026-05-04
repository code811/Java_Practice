import java.util.Scanner;

// Acts as Controller
public class MenuOption {
    private static final Scanner sc = new Scanner(System.in);
    private static int option = 0;

    public static void doOption(ParkWallet wallet) {
        System.out.println();
        switch(option) {
            case 1 -> addTickets(wallet);
            case 2 -> setTickets(wallet);
            case 3 -> buyPrize(wallet);
            case 4 -> setHoliday();
            case 5 -> System.out.println("Shutting off...");
            default -> System.out.println("Enter a valid response!");
        }
    }

    // Menu Private Helpers
    private static void addTickets(ParkWallet wallet) {
        System.out.print("Add how many tickets? ");
        wallet.addTickets(Integer.parseInt(sc.nextLine()));
        System.out.println();
    }

    private static void setTickets(ParkWallet wallet) {
        System.out.print("Set ticket balance to: ");
        wallet.setTickets(Integer.parseInt(sc.nextLine()));
        System.out.println();
    }

    private static void buyPrize(ParkWallet wallet) {
        AppDisplay.displayShop();
        int prize = Integer.parseInt(sc.nextLine());

        // Validates balance of tickets
        ItemForSale item = Shop.getMerchandise().get(prize - 1);
        int cost = item.getPrice();
        if(wallet.removeTickets(cost))  {
            System.out.println("Bought a " + item + " for " + cost + " tickets");
        }
        else {
            System.out.println("Not enough tickets to buy a " + item);
        }
        System.out.println();
    }

    private static void setHoliday() {
        ParkWallet.setHoliday();
        System.out.println((ParkWallet.getHoliday()) ? "It is now a holiday." : "It is no longer a holiday");
        System.out.println();
    }

    public static void setOption() {
        option = Integer.parseInt(sc.nextLine());
    }

    public static int getOption() {
        return option;
    }
}
