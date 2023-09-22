package project1;

/**
 * @ZainZulfiqar
 */
public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(14, 00),

    EVENING(18, 30);


    private final int Hour;
    private final int Minute;

    Timeslot(int Hour, int Minute) {
        this.Hour = Hour;
        this.Minute = Minute;
    }

    public int getHour() {
        return this.Hour;
    }

    public int getMinute() {
        return this.Minute;
    }
}
