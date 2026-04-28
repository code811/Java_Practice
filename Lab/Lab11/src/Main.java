import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<BoxOfBooks> boxes = new ArrayList<>();

        BoxOfBooks programming = new BoxOfBooks("Programming");
        programming.addBook("Databases made Simple");
        programming.addBook("Macros and Scripts");
        programming.addBook("Programming Basics");
        boxes.add(programming);

        BoxOfBooks chemistry = new BoxOfBooks("Chemistry");
        chemistry.addBook("Organic Chemistry");
        chemistry.addBook("Inorganic Chemistry");
        chemistry.addBook("The Periodic Table of Elements");
        boxes.add(chemistry);

        BoxOfBooks mathematics = new BoxOfBooks("Mathematics");
        mathematics.addBook("Linear Algebra");
        mathematics.addBook("Calculus I and II");
        mathematics.addBook("Pre-Calculus");
        boxes.add(mathematics);

        QueueOfStudents queueOfStudents = new QueueOfStudents();

        System.out.println("[Book Delivery Scheduler]");
        System.out.println();

        int option = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Add student");
            System.out.println("2. Create box of books");
            System.out.println("3. Deliver all boxes");
            System.out.print("Enter option: ");
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter an appropriate response in digit form!");;
            }
            System.out.println();

            switch(option) {
                case 1 -> {
                    System.out.print("Enter the name of student: ");
                    String studentName = sc.nextLine();

                    for(int i = 0; i < boxes.size(); i++) {
                        System.out.println(i + ": " + boxes.get(i).getBoxName());
                    }
                    System.out.print("Give them which box?: ");
                    try {
                        int studentCourse = Integer.parseInt(sc.nextLine());

                        if(studentCourse < 0 || boxes.size() < studentCourse) throw new NumberFormatException();

                        Student student = new Student(studentName, boxes.get(studentCourse).copy());
                        queueOfStudents.registerForPickup(student);
                        System.out.println("Student added to queue.");

                    } catch (NumberFormatException e) {
                        System.out.println("Enter an appropriate response in digit form!");
                    }
                }
                case 2 -> {
                    System.out.print("Enter box name: ");
                    String boxName = sc.nextLine();
                    BoxOfBooks newBox = new BoxOfBooks(boxName);

                    String bookName = "";
                    do {
                        System.out.print("Enter name of book or '-done': ");
                        bookName = sc.nextLine();

                        if(bookName.equalsIgnoreCase("-done")) break;

                        newBox.addBook(bookName);
                        System.out.println("Book added to box.");
                    } while(!bookName.equalsIgnoreCase("-done"));

                    boxes.add(newBox);
                    System.out.println("Box of Books registered for future use.");
                }
                case 3 -> {
                    System.out.println("Here are all the students and the contents of their boxes: ");
                    Student student;
                    while((student = queueOfStudents.deliverBoxOfBooks()) != null) {
                        System.out.println(student);
                    }
                }
            }

            System.out.println();
        } while(option != 3);

        System.out.println("Shutting off...");
    }
}