package project1;

/**
 * Creates and Represents an event
 * includes information such as date, starttime, location, contact and duration
 * @ZainZulfiqar
 *
 */
public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    @Override
    public int compareTo(Event o) {
        return 0;
    }

    /**
     * Returns information about the Event in format
     * MAKE SURE TO CHANGE END TIME!!!!
     * @return string representing event
     */
    @Override
    public String toString(){
        return "[Event Date: " + date + "]" + "[Start: " + startTime + "]"
                + "[End: " + "end time" + "] "+ "@" + location.name() + "("
                + location.getRoomNumber() + "," + location.getCampus()
                + "[Contact: " + contact.getDepartment() + ","
                + contact.getEmail() +"]";
    }
}
