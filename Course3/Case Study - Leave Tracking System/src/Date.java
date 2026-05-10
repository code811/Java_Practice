class Date {

    private int month;
    private int days;
    private int year;

    public Date(int month, int days, int year) {
        this.month = month;
        this.days = days;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDays() {
        return days;
    }

    public int getYear() {
        return year;
    }
}

class DateService {

    public static int getDuration(String start, String end) throws InvalidDateException {
        Date startDate = DateFactory.createDate(start);
        Date endDate = DateFactory.createDate(end);

        if(startDate.getYear() > endDate.getYear() ||
                (startDate.getMonth() > endDate.getMonth() &&
                        startDate.getYear() == endDate.getYear()) ||
                (startDate.getDays() > endDate.getDays()) &&
                        (startDate.getMonth() == endDate.getMonth() && startDate.getYear() == endDate.getYear())) {
            throw new InvalidDateException("The Start date must be before the End date!");
        }

        /*
        // "Walking algorithm" which walks up from the start date to the end date (instead of using a total day count referencing a specific point in time)
        int duration = 0;
        int month = startDate.getMonth();
        int year = startDate.getYear();

        if(month == endDate.getMonth() && year == endDate.getYear()) {    // Early Return Case
            return endDate.getDays() - startDate.getDays();
        } else {
            duration = DateFactory.getDaysFromMonth(month, year) - startDate.getDays();    // Get the Remaining Days left in the month
            month++;
        }

        while(year != endDate.getYear() || (year == endDate.getYear() && month != endDate.getMonth())) {    // Continues to iterate until month and year align with end date
            if(month > 12) {    // Wraps month back to January, and increases year to check if it's a leap year.
                month = 1;
                year++;
            }

            if(year == endDate.getYear() && month == endDate.getMonth()) {    // Checks in-case condition after wrapping meets statement
                break;
            }

            duration += DateFactory.getDaysFromMonth(month, year);

            month++;
        }

        duration += endDate.getDays();    // Adds remaining days to get to the end date
        return duration;

         */

        // Simpler Approach - Calculate total days and subtract
        int totalDaysStart = getTotalDaysSince(startDate);
        int totalDaysEnd = getTotalDaysSince(endDate);

        return totalDaysEnd - totalDaysStart;
    }

    private static int getTotalDaysSince(Date date) {
        int totalDays = date.getDays();

        for(int month = 1; month < date.getMonth(); month++) {
            totalDays += DateFactory.getDaysFromMonth(month, date.getYear());
        }

        for(int year = 1; year < date.getYear(); year++) {
            totalDays += (DateFactory.isLeapYear(year) ? 366 : 365);
        }

        return totalDays;
    }

    private static int getRemainingMonthsInDays(Date startDate) {    // Overloaded to find the amount of days left in the month
        return getRemainingMonthsInDays(startDate, 12);    // 12 represents the amount of months in a year
    }

    private static int getRemainingMonthsInDays(Date startDate, int endMonth) {    // Overloaded to find the amount of days left between the start and end month
        int duration = DateFactory.getDaysFromMonth(startDate.getMonth(), startDate.getYear()) - startDate.getDays();

        for(int month = startDate.getMonth() + 1; month <= endMonth; month++) {    // Already took into account this month with the equation above
            duration += DateFactory.getDaysFromMonth(month, startDate.getYear());
        }
        return duration;
    }
}

class DateFactory {

    public static Date createDate(String date) throws InvalidDateException {
        String[] parsedDate = getParsedDate(date);
        if(parsedDate.length != 3) {
            throw new InvalidDateException("The date has too many entries!");
        }

        int month, days, year;
        try {
            month = Integer.parseInt(parsedDate[0]);
            days = Integer.parseInt(parsedDate[1]);
            year = Integer.parseInt(parsedDate[2]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("The formatting of the date is incorrect!");
        }
        validateDate(month, days, year);

        return new Date(month, days, year);
    }

    public static int getDaysFromMonth(int month, int year) {
        return switch(month) {
            // January, March, May, July, August, October, December
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            // April, June, September, November
            case 4, 6, 9, 11 -> 30;
            // February
            case 2 -> {
                if(isLeapYear(year)) {
                    yield 29;
                } else {
                    yield 28;
                }
            }
            default -> 0;
        };
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    private static String[] getParsedDate(String date) {
        date = date.trim();
        return date.split(":");
    }

    private static void validateDate(int month, int days, int year) throws InvalidDateException {
        int daysInMonth = getDaysFromMonth(month, year);

        if(month < 0 || 12 < month) {
            throw new InvalidDateException("The month is not between 1-12");
        } else if(days < 0 || daysInMonth < days) {
            throw new InvalidDateException("The day is not between 1-" + daysInMonth);
        }
    }
}