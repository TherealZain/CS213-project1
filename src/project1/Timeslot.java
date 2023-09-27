package project1;

/**
 * @ZainZulfiqar
 */
public enum Timeslot {
    MORNING(10, "30"),
    AFTERNOON(14, "00"),

    EVENING(18, "30");

    private final int Hour;
    private final String Minute;

    Timeslot(int Hour, String Minute) {
        this.Hour = Hour;
        this.Minute = Minute;
    }

    /**
     * Gets hour value associated with timeslot
     * @return hour of timeslot as int
     */
    public int getHour() {
        return this.Hour;
    }

    /**
     * Gets minute value associated with timeslot
     * @return minute of timeslot as string
     */
    public String getMinute() {return this.Minute;}
}
