package project1;

/**
 * Initializes constants for use in Event and EventCalendar classes
 * @author Nicholas Yim
 * @author Zain Zulfiqar
 */
public class Constants {

    // Event class defined constants
    public static final int MIDNIGHT_HOUR = 0;
    public static final int NO_REMAINDER = 0;
    public static final int NOON_HOUR = 12;
    public static final int HOURS_IN_DAY = 24;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int MONTH_STANDARDIZER = 1;
    public static final int MONTHS_IN_YEAR = 12;
    public static final int MAX_BOOKING_MONTHS_AHEAD = 6;
    public static final int MIN_DURATION = 30;
    public static final int MAX_DURATION = 120;

    // EventCalendar class defined constants
    public static final int INITIAL_CAPACITY = 4;
    public static final int GROWTH_INCREMENT = 4;
    public static final int NOT_FOUND = -1; // also used in Event class

}
