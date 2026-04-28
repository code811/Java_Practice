import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Member member1 = new WeightLifter("Cole", 25, 147.9, "Premium");
        Member member2 = new Runner("Ricardo", 31, 139.0, "Standard");
        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);

        checkInMember(members);
    }

    public static void checkInMember(ArrayList<Member> members) {
        for(Member member : members) {
            member.checkIn();
            System.out.println(member.name + " has a " + member.getMembershipType() + " membership!");
            if(member instanceof WeightLifter) {
                ((WeightLifter)member).displayActivityInfo();
            }
            else if(member instanceof Runner) {
                ((Runner)member).displayActivityInfo();
            }
            member.displayActivity
        }
    }
}