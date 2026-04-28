import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("[Alarm System]");

        List<Alarm> alarms = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            try {
                option = promptMenu(sc);
                System.out.println();
                doOption(alarms, option, sc);
                System.out.println();
            }
            catch(NumberFormatException e) {
                System.out.println("Enter a proper response in digit form!");
            }
            catch(Exception e) {
                System.out.println("Something went wrong!");
            }
        } while(option != 3);

        System.out.println("Shutting off...");
    }

    private static int promptMenu(Scanner sc) {
        System.out.println("1. Create new alarm");
        System.out.println("2. View all alarms");
        System.out.println("3. Quit");
        System.out.print("Enter option: ");
        return Integer.parseInt(sc.nextLine());
    }

    private static void doOption(List<Alarm> alarms, int option, Scanner sc) throws NumberFormatException {
        switch(option) {
            case 1 -> createNewAlarm(alarms, sc);
            case 2 -> viewAllAlarms(alarms);
            case 3 -> quit(alarms);
        }
    }

    private static void createNewAlarm(List<Alarm> alarms, Scanner sc) throws NumberFormatException {
        System.out.print("Enter alarm name: ");
        String name = sc.nextLine();
        try {
            System.out.print("Enter alarm timer in seconds: ");
            int timer = Integer.parseInt(sc.nextLine());
            Alarm alarm = new Alarm(name, timer);
            alarms.add(alarm);
            System.out.println(alarm.toString());
            alarm.start();
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid timer: Timer must be a whole number.");
            return;
        }
    }

    private static void viewAllAlarms(List<Alarm> alarms) {
        System.out.println("Here are all the alarms still running: ");

        for(Alarm alarm : alarms) {
            if(alarm.isAlive()) {
                System.out.println(alarm.toString());
            }
        }
    }

    private static void quit(List<Alarm> alarms) {
        System.out.println("Stopping all alarms...");

        for(Alarm alarm : alarms) {
            alarm.interrupt();
        }

        for(Alarm alarm : alarms) {
            try {
                alarm.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All alarms have been stopped.");
    }
}