import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a name: ");
            String name = checkName(sc.nextLine());
            System.out.println("Your name is " + name);
        }
        catch(InvalidNameException e) {
            System.out.println(e.getMessage());
        }


    }

    public static String checkName(String name) throws InvalidNameException {
        if(!name.matches("^[a-zA-Z' -]+$")) {
            throw new InvalidNameException("Invalid Name!");
        }
        return name;
    }
}