import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<String[]> contacts = new ArrayList<>();

        String[] contact = new String[2];

        contact[0] = "Alice";
        contact[1] = "404-123-4567";

        contacts.add(contact);
        System.out.println("Name: " + contacts.get(0)[0] + " | Phone: " + contacts.get(0)[1]);

        contacts.remove(0);

    }
}