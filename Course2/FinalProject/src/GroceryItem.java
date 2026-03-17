import java.util.Random;

public class GroceryItem {

    private Random random = new Random();

    private String name;
    private float price;
    private int quantity = random.nextInt(1, 11);

    public GroceryItem(String name, float price) throws InvalidNameException, InvalidPriceException{
            if(!name.matches("^[a-zA-Z -]+$")) throw new InvalidNameException("This item isn't a valid name!");
            this.name = name;
            if(price < 0) throw new InvalidPriceException("This item is priced too low!");
            this.price = price;
    }

    public GroceryItem(String name, int quantity) throws InvalidNameException {
        if(!name.matches("^[a-zA-Z -]+$")) throw new InvalidNameException("This item isn't a valid name!");
        this.name = name;
        this.quantity = quantity;
    }

    public void purchaseQuantity(int quantity) {
        if(quantity > this.quantity) {
            return;
        }
        this.quantity -= quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void discountPrice(float discount) {
        price -= (price * discount);
    }

    public int getQuantity() {
        return quantity;
    }

    public float getCost() {
        return (quantity * price);
    }

    @Override
    public String toString() {
        return String.format("%s\t\t|%.2f\n", name, price);
    }
}
