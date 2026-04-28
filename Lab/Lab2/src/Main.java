import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option = 0;

        // repeats menu option till user inputs 4
        do {
            System.out.println("1. Count from a number to another");
            System.out.println("2. Determine largest number");
            System.out.println("3. Type in word");
            System.out.println("4. Quit");
            System.out.print("Enter option: ");
            option = Integer.parseInt(sc.nextLine());
            System.out.println();

            // navigates menu option through user input
            switch(option) {
                case 1: { // counts from a number to another
                    System.out.print("Enter the start point: ");
                    int startPoint = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter the end point: ");
                    int endPoint = Integer.parseInt(sc.nextLine());
                    System.out.println("Counting from " + startPoint + " to " + endPoint + "...");

                    if(startPoint == endPoint) { // in cases which user inputs same number
                        System.out.println("Start and end are the same!");
                    }
                    else if(startPoint > endPoint) { // in cases user inputs a starting number greater than end point, then it will decrement
                        for(int i = startPoint; i >= endPoint; i--) {
                            System.out.println(i);
                        }
                    }
                    else {
                        for(int i = startPoint; i <= endPoint; i++) { // default case, which increments from starting to end
                            System.out.println(i);
                        }
                    }

                    System.out.println("Done counting.");
                    System.out.println();
                    break;
                }
                case 2: { // determines largest number
                    System.out.println("This option will display the largest number entered. Enter 0 when done.");

                    int largestNum = 0; // resets the largest number each time case is called
                    int number;
                    do { // repeats till user enters '0'
                        System.out.print("Enter a number (current largest is " + largestNum + "): ");
                        number = Integer.parseInt(sc.nextLine());

                        if(number > largestNum) { // compares the current largest number to whatever user inputs
                            largestNum = number; // stores in cases the user inputs a larger number
                        }

                    } while(number != 0);

                    System.out.println("The largest number entered was " + largestNum);
                    System.out.println();
                    break;
                }
                case 3: { // type in word
                    System.out.print("Type in the word 'Computer': ");
                    String userWord = sc.nextLine();

                    String word = "Computer"; // .equals()
                    while(!(userWord.equals(word))) { // checks in case user does not properly input 'Computer' and repeats prompt till then
                        System.out.print("Incorrect. You must type 'Computer': ");
                        userWord = sc.nextLine();
                    }

                    System.out.println("Correct!");
                    System.out.println();
                    break;
                }

            }
        } while(option != 4);

        System.out.println("Shutting off...");
        sc.close();
    }
}