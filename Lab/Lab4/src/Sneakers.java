public class Sneakers extends ItemForSale {

    private static final int price = 600;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ("Sneakers");
    }
}
