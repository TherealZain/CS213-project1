package project1;

public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    // constructor
    public EventCalendar() {
        events = new Event[Constants.INITIAL_CAPACITY];
        numEvents = 0;
    }

    private int find(Event event) { //search an event in the list
        for (int i = 0; i < numEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    private void grow() { //increase the capacity by 4
        Event[] newEvents = new Event[events.length + Constants.GROWTH_INCREMENT];
        for (int i = 0; i < numEvents; i++) {
            newEvents[i] = events[i];
        }
        events = newEvents;
    }

    public void add(Event event) {
        if (numEvents == events.length) {
            grow();
        }
        events[numEvents++] = event;
    }

    public void remove(Event event) {
        int removeIndex = findRemove(event);
        if (removeIndex != Constants.NOT_FOUND) {
            // Shift the events to remove the specified event
            for (int i = removeIndex; i < numEvents - 1; i++) {
                events[i] = events[i + 1];
            }

            // Set the last element to null and decrement the count
            events[numEvents - 1] = null;
            numEvents--;
        }
    }

    public int findRemove(Event event) {
        for (int i = 0; i < numEvents; i++) {
            String eventString = events[i].toString();
            String dummyString = event.removeToString();

            if (eventString.startsWith(dummyString)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public boolean contains(Event event) {
        return find(event) != Constants.NOT_FOUND;
    }

    public void print() { //print the array as is
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }

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

    private void selectionSortLocation() {
        for (int i = 0; i < numEvents - 1; i++) {
            int currentMinIndex = i;

            for (int j = i + 1; j < numEvents; j++) {
                String campusA = events[currentMinIndex].getLocation().getCampus();
                String campusB = events[j].getLocation().getCampus();

                int campusComparison = campusA.compareTo(campusB);
                if (campusComparison > 0) {
                    currentMinIndex = j;
                } else if (campusComparison == 0) {
                    String roomA = events[currentMinIndex].getLocation().getRoomNumber();
                    String roomB = events[j].getLocation().getRoomNumber();

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

    public void printByDateAndTimeslot() { //ordered by date and timeslot
        selectionSortDateAndTimeslot();
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }

    public void printByCampus() { //ordered by campus and building/room
        selectionSortLocation();
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }

    }

    public void printByDepartment () { //ordered by department
        selectionSortDepartment();
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i].toString());
        }
    }
    public boolean isEmpty() {
        return numEvents == 0;
    }

}
