public class WeightLifter extends Member implements Trackable {

    protected String membershipType;
    protected double weeklyCaloriesBurned;

    public WeightLifter(String name, int age, double weight, String membershipType) {
        super(name, age, weight);
        this.membershipType = membershipType;
    }

    @Override
    public String getMembershipType() {
        return this.membershipType;
    }

    @Override
    public double calculateWeeklyCaloriesBurned() {
        this.weeklyCaloriesBurned = 100 + (14 * this.weight) - (6 * this.age); // random formula
        return this.weeklyCaloriesBurned;
    }

    @Override
    public void displayActivityInfo() {
        calculateWeeklyCaloriesBurned();
        System.out.println("As a weightlifter, " + name +" loses: " + this.weeklyCaloriesBurned + " calories!");
    }
}
