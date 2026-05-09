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

    private static void validateDate(int month, int days, int year) throws InvalidDateException {
        int daysInMonth = getDaysFromMonth(month, year);

        if(month < 0 || 12 < month) {
            throw new InvalidDateException("The month is not between 1-12");
        } else if(days < 0 || daysInMonth < days) {
            throw new InvalidDateException("The day is not between 1-" + daysInMonth);
        }
    }

    private static String[] getParsedDate(String date) {
        date = date.trim();
        return date.split(":");
    }

    private static int getDaysFromMonth(int month, int year) {
        return switch(month) {
            // January, March, May, July, August, October, December
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            // April, June, September, November
            case 4, 6, 9, 11 -> 30;
            // February
            case 2 -> {
                if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                    yield 29;
                } else {
                    yield 28;
                }
            }
            default -> 0;
        };
    }
}