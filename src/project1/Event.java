package project1;

/**
 * Creates and Represents an event
 * includes information such as date, starttime, location, contact and duration
 * @author Zain Zulfiqar
 * @author Nicholas Yim
 *
 */
public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Gets location of event
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets contact information of person associated with event
     * @return contact
     */
    public Contact getContact(){
        return contact;
    }

    /**
     * Checks if object is a event if so checks if date, timeslot and location are the same
     * @param o object being compared if event
     * @return true if date, timeslot and location are same
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event other = (Event) o;

            return (date != null ? date.compareTo(other.date) == 0 : other.date == null) &&
                    (startTime != null ? startTime.equals(other.startTime) : other.startTime == null) &&
                    (location != null ? location.equals(other.location) : other.location == null);
        }
        return false;

    }

    /**
     * Compares dates and timeslots
     * @param o event being compared
     * @return -1 if date is before, 1 if date is ahead, 0 if dates are the same
     */
    @Override
    // compare dates first, then timeslots if the dates are the same
    public int compareTo(Event o) {
        if(this.date.compareTo(o.date) > 0){
            return 1;
        }
        if(this.date.compareTo(o.date) < 0){
            return -1;
        }
        if (this.startTime.compareTo(o.startTime) > 0) {
            return 1;
        }
        if (this.startTime.compareTo(o.startTime) < 0) {
            return -1;
        }

        return 0;
    }

    /**
     * Returns information about the Event in format
     * @return string representing event
     */
    @Override
    public String toString(){
        String endTime = getEndTime(this.startTime, this.duration);
        String startTimeAmPm = convertToAmPm(startTime.getHour(), startTime.getMinute());
        return "[Event Date: " + date.dateString() + "] " + "[Start: " + startTimeAmPm
                + "] " + "[End: " + endTime + "] " + "@" + location.name() +
                " (" + location.getRoomNumber() + ", " + location.getCampus()
                + ") " + "[Contact: " + contact.getDepartment() + ", " + contact.getEmail() +"]";
    }

    /**
     * Converts military time, for timeslot enum to am or pm format
     * @param hour
     * @param minute
     * @return time as string in am or pm format
     */
    private static String convertToAmPm(int hour, String minute) {
        String amOrPm = (hour < 12) ? "am" : "pm";
        if (hour > 12) {
            hour -= 12;
        } else if (hour == 0) {
            hour = 12;
        }
        return hour + ":" + minute + amOrPm;
    }

    /**
     * Calculates end time of event
     * @param startTime of event
     * @param duration of event
     * @return end time as string in am or pm format
     */
    private static String getEndTime(Timeslot startTime, int duration) {
        int hours = duration / 60;
        int minutes = duration % 60;

        int endHour = startTime.getHour() + hours;
        int endMinute = Integer.parseInt(startTime.getMinute()) + minutes;

        if (endMinute >= 60) {
            endHour += 1;
            endMinute %= 60;
        }
        if (endHour >= 24) {
            endHour %= 24;
        }

        // determine AM or PM
        String amOrPm = (endHour < 12) ? "am" : "pm";

        // format time components
        String hourPart = (endHour % 12 == 0) ? "12" : Integer.toString(endHour % 12);
        String minutePart = String.format("%02d", endMinute);

        return hourPart + ":" + minutePart + amOrPm;
    }


    /**
     * Testbed Main
     *
     */
    public static void main(String[] args) {
        testDateTimeLocation_same();
        testDateTimeLocation_differentDate();
        testDateTimeLocation_differentTime();

    }
    /** Test Case #1 */
    private static void testDateTimeLocation_same(){
        Date date = new Date(2023, 5, 21);
        Timeslot time = Timeslot.AFTERNOON;
        Location loc = Location.HLL114;
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Contact contact2 = new Contact(Department.MATH, "math@rutgers.edu");
        int duration = 30;
        Event event1 = new Event(date, time, loc, contact1, duration);
        Event event2 = new Event(date, time, loc, contact2, duration);
        System.out.println("Test case #1: Events with same date, time and location should be equal");
        boolean expectedOutput = true;
        boolean actualOutput = event1.equals(event2);
        testResult(event1, event2, expectedOutput, actualOutput);
    }
    /** Test Case #2 */
    private static void testDateTimeLocation_differentDate(){
        Date date1 = new Date(2023, 5, 21);
        Date date2 = new Date(2023, 6, 21);
        Timeslot time = Timeslot.AFTERNOON;
        Location loc = Location.HLL114;
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Contact contact2 = new Contact(Department.MATH, "math@rutgers.edu");
        int duration = 30;
        System.out.println("Test case #2: events with different date should not be equal");
        Event event1 = new Event(date1, time, loc, contact1, duration);
        Event event2 = new Event(date2, time, loc, contact2, duration);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        testResult(event1, event2, expectedOutput, actualOutput);
    }

    /** Test Case #3 */
    private static void testDateTimeLocation_differentTime(){
        Date date = new Date(2023, 5, 21);
        Timeslot time1 = Timeslot.AFTERNOON;
        Timeslot time2 = Timeslot.MORNING;
        Location loc = Location.HLL114;
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Contact contact2 = new Contact(Department.MATH, "math@rutgers.edu");
        int duration = 30;
        Event event1 = new Event(date, time1, loc, contact1, duration);
        Event event2 = new Event(date, time2, loc, contact2, duration);
        System.out.println("Test case #1: Events with different timeslot should not be equal");
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        testResult(event1, event2, expectedOutput, actualOutput);
    }
    private static void testResult(Event event1, Event event2,
                                      boolean expectedOutput, boolean actualOutput){
        System.out.println("Event 1: " + event1.toString());
        System.out.println("Event 2: " + event2.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);
    }
}