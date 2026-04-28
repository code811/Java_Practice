public class Runner extends Member implements Trackable{

    protected String membershipType;
    protected double weeklyCaloriesBurned;


    public Runner(String name, int age, double weight, String membershipType) {
        super(name, age, weight);
        this.membershipType = membershipType;
    }

    @Override
    public String getMembershipType() {
        return this.membershipType;
    }

    @Override
    public double calculateWeeklyCaloriesBurned() {
        this.weeklyCaloriesBurned = 90 + (14 * this.weight) - (6 * this.age);
        return weeklyCaloriesBurned;
    }

    @Override
    public void displayActivityInfo() {
        calculateWeeklyCaloriesBurned();
        System.out.println("As a runner, " + name +" loses: " + this.weeklyCaloriesBurned + " calories!");
    }
}
