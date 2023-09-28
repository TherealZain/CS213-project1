package project1;

/**
 * The EventCalendar class manages an array of Event objects.
 * It provides functionalities to add, remove, and search for events.
 * @author Nicholas Yim
 * @author Zain Zulfiqar
 */
public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    /**
     * Constructs an events array with size of INITIAL_CAPACITY, sets number of events to 0
     */
    public EventCalendar() {
        events = new Event[Constants.INITIAL_CAPACITY];
        numEvents = 0;
    }

    /**
     * Finds an event on the calendar
     * @param event to be compared with .equals to see if it exists on calendar
     * @return index of event on calendar, NOT_FOUND if not found
     */
    private int find(Event event) { //search an event in the list
        for (int i = 0; i < numEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    /**
     * Increases events array by GROWTH_INCREMENT
     * This method creates a new array with increased capacity, copies the existing events into it, and then replaces the old array with the new one.
     */
    private void grow() { //increase the capacity by 4
        Event[] newEvents = new Event[events.length + Constants.GROWTH_INCREMENT];
        for (int i = 0; i < numEvents; i++) {
            newEvents[i] = events[i];
        }
        events = newEvents;
    }

    /**
     * Adds new event to calendar
     * Checks if events array is at capacity first; if so, calls grow(), then continues to add
     * @param event to be added to calendar
     */
    public void add(Event event) {
        if (numEvents == events.length) {
            grow();
        }
        events[numEvents++] = event;
    }

    /**
     * Removes event from calendar
     * First checks if event is in calendar; if so, then removes
     * @param event to be removed
     */
    public void remove(Event event) {
        int removeIndex = find(event);
        if (removeIndex != Constants.NOT_FOUND) {
            for (int i = removeIndex; i < numEvents - 1; i++) {
                events[i] = events[i + 1];
            }
            numEvents--;
            events[numEvents] = null;
        }
    }

    /**
     * Checks if calendar contains an event
     * @param event The event to be checked for existence in calendar.
     * @return true if calendar contains event, false otherwise
     */
    public boolean contains(Event event) {
        return find(event) != Constants.NOT_FOUND;
    }

    /**
     * Prints the events array as is
     */
    public void print() {
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }

    /**
     * Selection sort algorithm to sort the events array by date and timeslot
     */
    private void selectionSortDateAndTimeslot() {
        for (int i = 0; i < numEvents - 1; i++) {
            int currentMinIndex = i;
            for (int j = i + 1; j < numEvents; j++) {
                if (events[j].compareTo(events[currentMinIndex]) < 0) {
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                Event temp = events[i];
                events[i] = events[currentMinIndex];
                events[currentMinIndex] = temp;
            }
        }
    }

    /**
     * Selection sort algorithm to sort the events array by location (campus then room number)
     */
    private void selectionSortLocation() {
        for (int i = 0; i < numEvents - 1; i++) {
            int currentMinIndex = i;

            for (int j = i + 1; j < numEvents; j++) {
                String campusA = events[currentMinIndex].getLocation().getCampus().trim();
                String campusB = events[j].getLocation().getCampus().trim();
                int campusComparison = campusA.compareTo(campusB);

                if (campusComparison > 0) {
                    currentMinIndex = j;
                } else if (campusComparison == 0) {
                    String roomA = events[currentMinIndex].getLocation().getRoomNumber().trim();
                    String roomB = events[j].getLocation().getRoomNumber().trim();

                    int roomComparison = roomA.compareTo(roomB);

                    if (roomComparison > 0) {
                        currentMinIndex = j;
                    }
                }
            }

            if (currentMinIndex != i) {
                Event temp = events[i];
                events[i] = events[currentMinIndex];
                events[currentMinIndex] = temp;
            }
        }
    }

    /**
     * Selection sort algorithm to sort the events array by department
     */
    private void selectionSortDepartment(){
        for(int i = 0; i< numEvents-1; i++){
            int currentMinIndex = i;
            for(int j = i+1; j< numEvents; j++){
                String deptA = events[currentMinIndex].getContact().getDepartment();
                String deptB = events[j].getContact().getDepartment();

                int deptComparison = deptA.compareTo(deptB);
                if(deptComparison > 0){
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                Event temp = events[i];
                events[i] = events[currentMinIndex];
                events[currentMinIndex] = temp;
            }
        }
    }

    /**
     * Prints sorted events array ordered by date and timeslot
     */
    public void printByDateAndTimeslot() {
        selectionSortDateAndTimeslot();
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }

    /**
     * Prints sorted events array ordered by campus and room number
     */
    public void printByCampus() {
        selectionSortLocation();
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }

    }

    /**
     * Prints sorted events array ordered by department
     */
    public void printByDepartment () {
        selectionSortDepartment();
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }

    /**
     * Checks if there are no events in the calendar
     * @return true if there are no events, false otherwise
     */
    public boolean isEmpty() {
        return numEvents == 0;
    }

}
