import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("[Time Calculator]");
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println("1. Calculate difference in seconds");
                System.out.println("2. Exit");
                System.out.print("Enter your option: ");
                option = Integer.parseInt(sc.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        System.out.print("Enter the start timestamp: ");
                        String start = sc.nextLine();
                        System.out.print("Enter the end timestamp: ");
                        String end = sc.nextLine();
                        int difference = differenceInSeconds(start, end);
                        System.out.printf("The difference between %s and %s is %d seconds\n", start, end, difference);
                    }
                    case 2 -> System.out.println("Shutting off...");
                }
            }
            catch(NumberFormatException e){
                System.out.println("You must enter integers for the hours, minutes, and seconds");
            }
            catch(InvalidTimeException e){
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                System.out.println("There was a problem");
            }
            finally {
                System.out.println();
            }
        } while(option != 2);
    }

    public static int differenceInSeconds(String start, String end) throws InvalidTimeException {
//        if(invalidFormat(start) || invalidFormat(end)) {
//            throw new InvalidTimeException("Timestamp must be in format HH:MM:SS");
//        }
        String[] startTimestamp = start.split(":");
        String[] endTimestamp = end.split(":");

        checkFormat(endTimestamp);
        checkFormat(startTimestamp);
        checkRange(endTimestamp);
        checkRange(startTimestamp);

        int startSeconds = calculateSeconds(startTimestamp);
        int endSeconds = calculateSeconds(endTimestamp);

        if(endSeconds > startSeconds) {
            return endSeconds - startSeconds;
        }
        return startSeconds - endSeconds;
    }

//    private static boolean invalidFormat(String timestamp) {
//        if(!timestamp.matches("\\d+:\\d+:\\d+")) return true;
//        return false;
//    }

    private static void checkFormat(String[] timestamp) throws InvalidTimeException {
        if(timestamp.length != 3) throw new InvalidTimeException("Timestamp must be in format HH:MM:SS");
    }

    private static void checkRange(String[] timestamp) throws InvalidTimeException{
        for(int i = 0; i < timestamp.length; i ++) {
            String unitOfTime = "";
            switch(i) {
                case 0 -> unitOfTime = "Hours";
                case 1 -> unitOfTime = "Minutes";
                case 2 -> unitOfTime = "Seconds";
            }

            int time = Integer.parseInt(timestamp[i]);

            if(time < 0) {
                throw new InvalidTimeException(unitOfTime + " must be greater than or equal to 0");
            }
            if(i == 0) {
                if(time > 24) {
                    throw new InvalidTimeException("Hours must be less than 24");
                }
            }
            else {
                if(time > 60) {
                    throw new InvalidTimeException(unitOfTime + " must be less than 60");
                }
            }
        }
    }

    private static int calculateSeconds(String[] timestamp) {
        int hoursInSeconds = Integer.parseInt(timestamp[0]) * 60 * 60;
        int minutesInSeconds = Integer.parseInt(timestamp[1]) * 60;
        int seconds = Integer.parseInt(timestamp[2]);
        return hoursInSeconds + minutesInSeconds + seconds;
    }


}