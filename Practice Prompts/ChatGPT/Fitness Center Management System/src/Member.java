public abstract class Member {

    protected String name;
    protected int age;
    protected double weight;

    public Member(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public void checkIn() {
        System.out.println("Member " + this.name + " has checked in.");
    }

    public abstract String getMembershipType();
}
