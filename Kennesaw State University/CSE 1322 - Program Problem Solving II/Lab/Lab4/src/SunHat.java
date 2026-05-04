public class SunHat extends ItemForSale {

    private static final int price = 350;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ("Sun hat");
    }
}
