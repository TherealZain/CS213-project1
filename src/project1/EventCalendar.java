package project1;

public class EventCalendar {
    private Event[] events; //the array holding the list of events
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
            for (int i = removeIndex; i < numEvents - 1; i++) {
                events[i] = events[i + 1];
            }
            numEvents--;
            events[numEvents] = null;
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

    private void selectionSortDate() {
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

    public void printByDate() { //ordered by date and timeslot
        selectionSortDate();
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

}
