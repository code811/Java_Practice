// Required class by Lab4 with required fields, methods, and getters & setters
public class ParkWallet {

    private static boolean holiday = false;

    private int tickets;

    public ParkWallet() {
        tickets = 0;
    }

    public ParkWallet(int tickets) {
        this.tickets = tickets;
    }

    public void addTickets(int tickets) {
        this.tickets += Math.max(tickets, 0);
    }

    public boolean removeTickets(int tickets) {
        if(tickets < 0 || tickets > this.tickets) {
            return false;
        }

        this.tickets -= tickets;
        return true;
    }

    public static boolean getHoliday() {
        return holiday;
    }

    public static void setHoliday() {
        holiday = !holiday;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = (tickets < 0) ? this.tickets : tickets;
    }
}
