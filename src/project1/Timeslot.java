package project1;

/**
 * @author Zain Zulfiqar
 * @author Nicholas Yim
 */
public enum Timeslot {
    MORNING(10, "30"),
    AFTERNOON(14, "00"),

    EVENING(18, "30");

    private final int Hour;
    private final String Minute;

    /**
     * Constructs Timeslot based on Hour and Minute
     * @param Hour of Timeslot as int
     * @param Minute of Timeslot as String
     */
    Timeslot(int Hour, String Minute) {
        this.Hour = Hour;
        this.Minute = Minute;
    }

    /**
     * Gets hour value associated with timeslot
     * @return hour of Timeslot as int
     */
    public int getHour() {
        return this.Hour;
    }

    /**
     * Gets minute value associated with Timeslot
     * @return minute of Timeslot as String
     */
    public String getMinute() {
        return this.Minute;
    }
}
