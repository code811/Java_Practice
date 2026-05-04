import java.util.ArrayList;
import java.util.Scanner;

public class Lab3 {
    static ArrayList<String[]> phonebook = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("[Phonebook]");
        int option = 0;
        do {
            displayMenu();
            System.out.print("Enter option: ");
            option = Integer.parseInt(sc.nextLine());
            doOption(option);
        } while (option != 5);

        sc.close();
    }

    static void displayMenu() {
        System.out.println("1. Add Contact");
        System.out.println("2. Remove Contact");
        System.out.println("3. List Contacts");
        System.out.println("4. Search Contacts");
        System.out.println("5. Quit");
    }

    static void doOption(int option) {
        switch(option) {
            case 1 -> addContact();
            case 2 -> removeContact();
            case 3 -> listContacts();
            case 4 -> searchContacts();
            case 5 -> System.out.println("Shutting off...");
            default -> System.out.println("Please enter an appropriate menu option!");
        }
    }

   static void addContact() {
       String[] contact = new String[2];
       System.out.print("Enter the contact's name: ");
       contact[0] = sc.nextLine();
       // verifies phone number
       do {
           System.out.print("Enter the contact's phone number: ");
           contact[1] = sc.nextLine();
       } while(!verifyNumber(contact[1]));

       phonebook.add(contact);
       System.out.println("Contact added.");
       System.out.println();
   }

   static boolean verifyNumber(String contactNumber) {
        if(     contactNumber.length() != 12 ||
                contactNumber.charAt(3) != '-' ||
                contactNumber.charAt(7) != '-') {
            System.out.println("The number should be: ***-***-****");
            return false;
        }
       return true;
   }

   static void removeContact() {
        System.out.print("Enter contact to remove: ");
        int removeContact = searchToMatch(sc.nextLine());

        if(removeContact == -1) {
            System.out.println("No contact with that name.");
            System.out.println();
            return;
        }

        phonebook.remove(removeContact);
        System.out.println("Contact deleted.");
        System.out.println();
   }

   static int searchToMatch(String contact) {
        for(int i = 0; i < phonebook.size(); i++) {
            String contactName = phonebook.get(i)[0];
            if(contactName.equals(contact)) {
                return i;
            }
        }
        return -1;
   }

   static void listContacts() {
       if(phonebook.isEmpty()) {
           System.out.println("The phonebook is empty.");
           System.out.println();
           return;
       }

       System.out.println("Listing all contacts...");
       for(String[] contact : phonebook) {
           System.out.println("Name: " + contact[0] + " | Phone: " + contact[1]);
       }

       System.out.println("Done listing contacts.");
       System.out.println();
   }

   static void searchContacts() {
       System.out.print("Enter keyword to search: ");
       String keyword = sc.nextLine();

       System.out.println("Searching all contacts for keyword...");
       ArrayList<Integer> containsKeyword = searchToContain(keyword);

       if(containsKeyword.isEmpty()) {
           System.out.println("No contacts contained the keyword.");
           System.out.println();
           return;
       }

       displayContacts(containsKeyword);
       System.out.println("Done searching keyword.");
       System.out.println();
   }

   static ArrayList<Integer> searchToContain(String keyword) {
        ArrayList<Integer> containsKeyword = new ArrayList<Integer>();

        for(int i = 0; i < phonebook.size(); i++) {
            String contactName = phonebook.get(i)[0];
            if(contactName.contains(keyword)) {
                containsKeyword.add(i);
            }
        }

        return containsKeyword;
   }

   static void displayContacts(ArrayList<Integer> containsKeyword) {
        for(int i = 0; i < containsKeyword.size(); i++) {
            String[] contact = phonebook.get(containsKeyword.get(i));
            System.out.println("Name: " + contact[0] + " | Phone: " + contact[1]);
        }
   }
}