import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Accepting user input for name, salary, and calculating yearly salary
        System.out.println("[Budgeting System]");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Hello " + name + ". ");
        System.out.print("Please enter your monthly salary: ");
        double salary = Double.parseDouble(sc.nextLine());
        double yearly_salary = salary * 12;
        System.out.println(' ');

        // Accepting user input for the loan to calculate principal
        System.out.print("What is the total amount of your load?: ");
        double loan_principal = Double.parseDouble(sc.nextLine());
        System.out.print("What is the interest rate of your load?: ");
        double loan_interest = Double.parseDouble(sc.nextLine());
        loan_interest /= 100;
        double new_principal = loan_principal * Math.pow((1 + (loan_interest / 12)), 12);
        System.out.println(' ');

        // Displaying salary information and loan's principal
        System.out.println("Your yearly salary is $" + yearly_salary);
        System.out.println("In 12 months, your loan's principal will be $" + new_principal);
        System.out.println(' ');

        // Displaying whether loan will be paid off
        System.out.println("At the end of the year, you will have paid off your debt: " + (yearly_salary >= new_principal));
        System.out.println("At the end of the year, you will still have some debt left: " + (yearly_salary < new_principal));
        System.out.println("At the end of the year, you will have $" + (yearly_salary - new_principal) + " of your salary left");
        System.out.println(' ');

        // Accepting user input to verify potential loan relief and display result corresponding
        System.out.println("The government is offering loan relief for persons 25 and under, and for those 65 and over.");
        System.out.print("What is your age?: ");
        int age = Integer.parseInt(sc.nextLine());
        boolean eligible_for_relief = (age <= 25) || (age >= 65);
        System.out.println("The relief is $10,000. You are eligible for the relief: " + eligible_for_relief);
        boolean loan_paid_off = (yearly_salary >= new_principal) || (eligible_for_relief && ((yearly_salary + 10000) >= new_principal));
        System.out.println("With or without relief, you will be able to pay your loan in full: " + loan_paid_off);
    }
}