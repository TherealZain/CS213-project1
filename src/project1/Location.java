package project1;

/**
 * Represents location, includes all constants of where an event could take place
 * Hill Center, Allison Road Classroom, Beck Hall, Tillett Hall, Academic Building and Murray Hall
 * @author Zain Zulfiqar
 * @author Nicholas Yim
 */
public enum Location {

    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225( "Academic Building"," College Avenue"),
    MU302( "Murray Hall"," College Avenue");



    private final String roomNumber;
    private final String campus;

    /**
     * Constructs Location based on roomNumber and campus
     * @param roomNumber as String
     * @param campus as String
     */
    Location(String roomNumber, String campus){
        this.roomNumber = roomNumber;
        this.campus = campus;
    }

    /**
     * Gets the room number of the location
     * @return room number of location
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Gets the campus of the location
     * @return campus of location
     */
    public String getCampus() {
        return campus;
    }



}
