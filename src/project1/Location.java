package project1;

/**
 * Represents location, includes all constants of where a event could take place
 * Hill Center, Allison Road Classroom, Beck Hall, Tillet Hall, Academic Building and Murray Hall
 * @ZainZulfiqar
 *
 */
public enum Location {

    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("BECK HALL", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225( "Academic Building"," College Avenue"),
    MU302( "Murray Hall"," College Avenue");



    private final String roomNumber;
    private final String campus;

    Location(String roomNumber, String campus){
        this.roomNumber = roomNumber;
        this.campus = campus;
    }

    /**
     * Gives the room number of the location
     * @return room number of location
     *
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Gives the campus of the location
     * @return campus of location
     */
    public String getCampus() {
        return campus;
    }



}
