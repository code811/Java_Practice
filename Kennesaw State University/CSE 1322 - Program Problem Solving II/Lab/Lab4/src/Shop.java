import java.util.ArrayList;
import java.util.List;

// Stores models
public class Shop {
    private static final List<ItemForSale> merchandise = List.of(
            new TShirt(),
            new SunHat(),
            new Sneakers()
    );

    public static List<ItemForSale> getMerchandise() {
        return merchandise;
    }
}
