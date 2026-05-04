public class TShirt extends ItemForSale {

    private static final int price = 150;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ("T-shirt");
    }
}
