package project1;

/**
 * Creates and represents a date of an event
 * Includes constants day, month, and year
 * Includes isValid and compareTo methods
 * @author Nicholas Yim
 * @author Zain Zulfiqar
 */
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

    /**
     * Constructs date based on day, month, and year
     * @param year of date as int
     * @param month of date as int
     * @param day of date as int
     */
    public Date(int year, int month, int day){
        this.year = year;
        this. month = month;
        this. day = day;
    }

    /**
     * Gets year of date instance
     * @return year as int
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets month of date instance
     * @return month as int
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Gets day of date instance
     * @return day as int
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Converts date to MM/DD/YYYY format
     * @return date as String in MM/DD/YY format
     */
    public String dateString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Compares if dates are before each other, ahead of each other or the same
     * @param d the Date to be compared
     * @return int -1 if Date d is less, 1 if greater, and 0 if same
     */
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

    /**
     * Checks if date is a valid calendar date
     * Also if leap year, checks if February has the right number of days
     * @return true if date is a valid calendar date, false otherwise
     */
    public boolean isValid(){
        if (month < JAN || month > DEC) {
            return false;
        }

        switch (month) {
            case JAN, MAR, MAY, JUL, AUG, OCT, DEC -> {
                return day >= MIN_DAYS_IN_MONTH && day <= DAYS_IN_LONG_MONTH;
            }
            case APR, JUN, SEP, NOV -> {
                return day >= MIN_DAYS_IN_MONTH && day <= DAYS_IN_SHORT_MONTH;
            }
            case FEB -> {
                if (isLeapYear(year)) {
                    return day >= MIN_DAYS_IN_MONTH && day <=
                            DAYS_IN_FEB_LEAP;
                }
                else {
                    return day >= MIN_DAYS_IN_MONTH && day <=
                            DAYS_IN_FEB_NON_LEAP;
                }
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Checks if year is a leap year
     * @param year as int
     * @return true if year is leap year, false otherwise
     */
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

    /**
     * Testbed Main
     * @param args command line arguments
     */
    public static void main(String[] args){
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        testMonth_OutOfRange();
        testDayInShortMonth_OutOfRange();
        testDayInLongMonth_InRange();
        testDayInMonth_GreaterThanOne();
    }
    /** Test case #1 */
    private static void testDaysInFeb_NonLeap(){
        Date date = new Date(2010, 2, 29);
        boolean expectedOutput = false;
        boolean actualOutput= date.isValid();
        System.out.println("**Test case #1: # of days in Feb. in a non-leap " +
                "year is 28");
        testResult(date, expectedOutput, actualOutput);
    }
    /** Test case #2 */
    private static void testDaysInFeb_Leap(){
        Date date = new Date(2012, 2, 29);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a leap " +
                "year is 29");
        testResult(date, expectedOutput, actualOutput);
    }
    /** Test case #3 */
    private static void testMonth_OutOfRange(){
        Date date = new Date(2012, 14, 29);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #3: # of months in a year is 12");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test case #4 */
    private static void testDayInShortMonth_OutOfRange(){
        Date date = new Date(2023, 11, 31);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #4: # of days in short month is 30");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test case #5 */
    private static void testDayInLongMonth_InRange() {
        Date date = new Date(2023,10,31);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #5: # of days in long month is 31");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test case #6*/
    private static void testDayInMonth_GreaterThanOne(){
        Date date = new Date(2023,10,-1);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #6: # of days in month is greater than 1");
        testResult(date, expectedOutput, actualOutput);
    }


    /** Check if a given test case results in PASS or FAIL...*/
    private static void testResult(Date date, boolean expectedOutput,
                                   boolean actualOutput){
        System.out.println("Date: " + date.dateString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);
        if (expectedOutput != actualOutput)
            System.out.println(" (FAIL) \n");
        else
            System.out.println(" (PASS) \n");
    }

}
