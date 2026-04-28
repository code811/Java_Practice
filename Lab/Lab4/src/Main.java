import java.util.ArrayList;

// I attempted OOP
public class Main {
    public static void main(String[] args) {
        ParkWallet wallet = new ParkWallet(100);

        AppDisplay.displayHeader();
        do {
            AppDisplay.displayMenu(wallet);
            MenuOption.setOption();
            MenuOption.doOption(wallet);
        } while(MenuOption.getOption() != 5);
    }
}