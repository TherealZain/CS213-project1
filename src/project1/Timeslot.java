package project1;

/**
 * @ZainZulfiqar
 */
public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(2, 00),

    EVENING(6, 30);


    private final int Hour;
    private final int Minute;

    Timeslot(int Hour, int Minute) {
        this.Hour = Hour;
        this.Minute = Minute;
    }
}
