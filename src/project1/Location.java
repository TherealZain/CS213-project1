package project1;

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

}
