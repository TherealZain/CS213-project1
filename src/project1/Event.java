package project1;

/**
 * Creates and Represents an event
 * includes information such as date, starttime, location, contact and duration
 * @author ZainZulfiqar
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

    @Override
    // needs to be worked on -nick
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event other = (Event) o;

            return (date != null ? date.equals(other.date) : other.date == null) &&
                    (startTime != null ? startTime.equals(other.startTime) : other.startTime == null) &&
                    (location != null ? location.equals(other.location) : other.location == null);
        }
        return false;

    }

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
     * MAKE SURE TO CHANGE END TIME!!!!
     * @return string representing event
     */
    @Override
    public String toString(){
        String endTime = getEndTime(this.startTime, this.duration);

        return "[Event Date: " + date + "]" + "[Start: " + startTime + "]"
                + "[End: " + endTime + "] "+ "@" + location.name() + "("
                + location.getRoomNumber() + "," + location.getCampus()
                + "[Contact: " + contact.getDepartment() + ","
                + contact.getEmail() +"]";
    }

    // needs testing -nick
    public static String getEndTime(Timeslot startTime, int duration) {
        int hours = duration / 60;
        int minutes = duration % 60;

        int endHour = startTime.getHour() + hours;
        int endMinute = startTime.getMinute() + minutes;

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
    public static void main(String[] args) {
        Timeslot testStart = Timeslot.MORNING;
        String testEnd = getEndTime(testStart, 150);

        System.out.println("End Time: " + testEnd);
    }
}