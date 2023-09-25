package project1;

public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;
    public static final int DAYS_IN_FEB_NON_LEAP = 28;
    public static final int DAYS_IN_FEB_LEAP = 29;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int MIN_DAYS_IN_MONTH = 1;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int year, int month, int day){
        this.year = year;
        this. month = month;
        this. day = day;
    }

    public int getYear() {
        return this.year;
    }

    public int compareTo(Date d){
        // Compare years
        if (this.year > d.year) {
            return 1;
        }
        if (this.year < d.year) {
            return -1;
        }

        // Compare months
        if (this.month > d.month) {
            return 1;
        }
        if (this.month < d.month) {
            return -1;
        }

        // Compare days
        if (this.day > d.day) {
            return 1;
        }
        if (this.day < d.day) {
            return -1;
        }

        return 0;  // Dates are the same
    }

    public boolean isValid(){
        // check if month is valid
        if (month < JAN || month > DEC) {
            return false;
        }

        // check if date is valid based on month
        switch (month) {
            case JAN, MAR, MAY, JUL, AUG, OCT, DEC -> {
                return day >= MIN_DAYS_IN_MONTH && day <= DAYS_IN_LONG_MONTH;
            }
            case APR, JUN, SEP, NOV -> {
                return day >= MIN_DAYS_IN_MONTH && day <= DAYS_IN_SHORT_MONTH;
            }
            case FEB -> {
                if (isLeapYear(year)) {
                    return day >= MIN_DAYS_IN_MONTH && day <= DAYS_IN_FEB_LEAP;
                }
                else {
                    return day >= MIN_DAYS_IN_MONTH && day <= DAYS_IN_FEB_NON_LEAP;
                }
            }
            default -> {
                return false;
            }
        }
    }

    public boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }


}
