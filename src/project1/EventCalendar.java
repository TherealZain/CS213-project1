package project1;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH_INCREMENT = 4;
    private static final int NOT_FOUND = -1;

    // constructor
    public EventCalendar() {
        events = new Event[INITIAL_CAPACITY];
        numEvents = 0;
    }

    private int find(Event event) { //search an event in the list
        for (int i = 0; i < numEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    private void grow() { //increase the capacity by 4
        Event[] newEvents = new Event[events.length + GROWTH_INCREMENT];
        for (int i = 0; i < numEvents; i++) {
            newEvents[i] = events[i];
        }
        events = newEvents;
    }
    public boolean add(Event event) {
        if (numEvents == events.length) {
            grow();
        }
        events[numEvents++] = event;
        return true;
    }
    public boolean remove(Event event) {
        int removeIndex = find(event);
        if (removeIndex != NOT_FOUND) {
            // need to implement remove action here
            return true;
        }
        return false;
    }
    public boolean contains(Event event) {
        return find(event) != NOT_FOUND;
    }
    public void print() { //print the array as is
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i]);
        }
    }
    public void printByDate() { //ordered by date and timeslot
    }
    public void printByCampus() { //ordered by campus and building/room
    }
    public void printByDepartment() { //ordered by department
    }
}
