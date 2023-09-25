package project1;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;

public class EventOrganizer {
    private boolean isRunning;
    private EventCalendar calendar;

    public EventOrganizer() {
        this.calendar = new EventCalendar();
        isRunning = true;
        System.out.println("Event Organizer running....");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String command = scanner.nextLine();

            StringTokenizer tokenizer = new StringTokenizer(command);
            String firstToken = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                switch (firstToken) {
                    case "Q" -> isRunning = false;
                    case "A" -> handleACommand(tokenizer);
                    case "R" -> handleRCommand(tokenizer);
                    case "P" -> handlePCommand(tokenizer);
                    case "PE" -> handlePECommand(tokenizer);
                    case "PC" -> handlePCCommand(tokenizer);
                    case "PD" -> handlePDCommand(tokenizer);
                    default -> System.out.println(firstToken + " is an invalid command!");
                }
            } else {
                System.out.println("Empty command received. Please enter a valid command.");
            }
        }

        System.out.println("Event Organizer terminated");
        scanner.close();
    }

    /**
     * TODO: still need to add these checks
     * 2. An event date is not a future date.
     * 3. An event date is more than 6 months away from today’s date.
     * 8. Conflict of schedule - an event with the same date/timeslot/location is already on the calendar.
     *
     * @param tokenizer
     */
    private void handleACommand(StringTokenizer tokenizer) {
        Date date = null;
        Timeslot startTime = null;
        Location location = null;
        Contact contact = null;
        String stringDate = tokenizer.nextToken();
        String stringStartTime = tokenizer.nextToken();
        String stringLocation = tokenizer.nextToken();
        String stringDepartment = tokenizer.nextToken();
        String stringEmail = tokenizer.nextToken();
        int duration = Integer.parseInt(tokenizer.nextToken());

        date = parseDate(stringDate);
        if(!(date.isValid())){
            return;
        }
        if(isValidTimeslot(stringStartTime)){
            startTime = Timeslot.valueOf(stringStartTime);
        }else {return;}

        if(isValidLocation(stringLocation)){
            location = Location.valueOf(stringLocation);
        }else {return;}

        if(isValidDepartment(stringDepartment) && isValidEmail(stringEmail)){
            Department department = Department.valueOf(stringDepartment);
            contact = new Contact(department, stringEmail);
        } else {return;}

        Event event = new Event(date, startTime, location, contact, duration);
        calendar.add(event);
    }
    private void handleRCommand(StringTokenizer tokenizer) {
    }

    private void handlePCommand(StringTokenizer tokenizer) {
    }

    private void handlePECommand(StringTokenizer tokenizer) {
    }

    private void handlePCCommand(StringTokenizer tokenizer) {
    }

    private void handlePDCommand(StringTokenizer tokenizer) {
    }

    // helper method to parse date
    private Date parseDate(String dateString) {
        String[] dateComponents = dateString.split("/");
        if (dateComponents.length == 3) {
            int year = Integer.parseInt(dateComponents[2]);
            int month = Integer.parseInt(dateComponents[0]);
            int day = Integer.parseInt(dateComponents[1]);

            return new Date(year, month, day);
        }
        return null;
    }

    private boolean dateChecks(Date date) {
        if (date.getYear() < xx) {

        }
    }

    public static boolean isValidTimeslot(String timeslotString) {
        try {
            Timeslot.valueOf(timeslotString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public static boolean isValidLocation(String locationString) {
        try {
            Location.valueOf(locationString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public static boolean isValidDepartment(String departmentString) {
        try {
            Department.valueOf(departmentString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public static boolean isValidEmail(String emailString) {
        // Check if the email contains '@'
        int atIndex = emailString.indexOf('@');
        if (atIndex == -1) {
            return false;
        }

        String username = emailString.substring(0, atIndex);
        String domain = emailString.substring(atIndex + 1);

        if (username.isEmpty() || domain.isEmpty()) {
            return false;
        }

        if (!domain.equalsIgnoreCase("rutgers.edu")) {
            return false;
        }

        for (Department d : Department.values()) {
            if (username.equalsIgnoreCase(d.name())) {
                return true;
            }
        }

        return false;
    }

    public static boolean futureDateCheck(int year, int month, int day){

    }





    public static void main(String[] args) {
        EventOrganizer organizer = new EventOrganizer();
        organizer.run();
    }
}
