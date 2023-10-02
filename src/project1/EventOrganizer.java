package project1;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * Takes user commands relating to scheduling events on the calendar
 * First checks if user command is valid
 * Contains separate functionality for commands starting with
 * A, R, P, PE, PC, PD, and Q
 * @author Zain Zulfiqar
 * @author Nicholas Yim
 */
public class EventOrganizer {
    private boolean isRunning;
    private EventCalendar calendar;

    /**
     * Instantiates new calendar for event and sets isRunning for
     * true to receive inputs
     */
    public EventOrganizer() {
        this.calendar = new EventCalendar();
        isRunning = true;
        System.out.println("Event Organizer running....");
    }

    /**
     * Initiates scanner and begins program
     * Continuously reads command lines from console until "Q" command is entered
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String command = scanner.nextLine();
            if (command.isEmpty()) {
                continue;
            }

            StringTokenizer tokenizer = new StringTokenizer(command);
            String firstToken = tokenizer.nextToken();
            switch (firstToken) {
                case "Q" -> isRunning = false;
                case "A" -> handleACommand(tokenizer);
                case "R" -> handleRCommand(tokenizer);
                case "P" -> handlePCommand(tokenizer);
                case "PE" -> handlePECommand(tokenizer);
                case "PC" -> handlePCCommand(tokenizer);
                case "PD" -> handlePDCommand(tokenizer);
                default -> System.out.println(firstToken + " is an " +
                        "invalid command!");
            }

        }

        System.out.println("Event Organizer terminated");
        scanner.close();
    }

    /**
     * Handles "A" command whether event is already on calendar or not
     * Prints success message if event is added to the calendar
     * Prints error message if event is already on the calendar
     *
     * @param tokenizer as StringTokenizer
     */
    private void handleACommand(StringTokenizer tokenizer) {
        Event event = createEventForACommand(tokenizer);

        if (!(eventNotOnCalendar(event))) {
            System.out.println("The event is already on the calendar.");
            return;
        }
        if (event == null) {
            return;
        }
        calendar.add(event);
        System.out.println("Event added to the calendar.");
    }

    /**
     * Handles "R" command whether event is in calendar or not
     * Prints success message if event is removed from calendar
     * Prints error message if event is not in the calendar
     *
     * @param tokenizer as StringTokenizer
     */
    private void handleRCommand(StringTokenizer tokenizer) {
        Event event = createEventForRCommand(tokenizer);
        if (event == null) {
            return;
        }
        if ((eventNotOnCalendar(event))) {
            System.out.println("Cannot remove; event is not in the calendar!");
            return;
        }
        calendar.remove(event);
        System.out.println("Event has been removed from the calendar!");

    }

    /**
     * Handles "P" command whether event calendar is empty or not
     * Prints entire calendar as is if calendar is not empty
     * Prints error message if event calendar is empty
     *
     * @param tokenizer as StringTokenizer
     */
    private void handlePCommand(StringTokenizer tokenizer) {
        if (calendar.isEmpty()) {
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar *");
        calendar.print();
        System.out.println("* end of event calendar *");
    }

    /**
     * Handles "PE" command whether event calendar is empty or not
     * Prints entire calendar sorted by event date and start time if
     * calendar is not empty
     * Prints error message if event calendar is empty
     *
     * @param tokenizer as StringTokenizer
     */
    private void handlePECommand(StringTokenizer tokenizer) {
        if (calendar.isEmpty()) {
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by event date and start time *");
        calendar.printByDateAndTimeslot();
        System.out.println("* end of event calendar *");
    }

    /**
     * Handles "PC" command whether event calendar is empty or not
     * Prints entire calendar sorted by campus and building if
     * calendar is not empty
     * Prints error message if event calendar is empty
     *
     * @param tokenizer as StringTokenizer
     */
    private void handlePCCommand(StringTokenizer tokenizer) {
        if (calendar.isEmpty()) {
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by campus and building *");
        calendar.printByCampus();
        System.out.println("* end of event calendar *");
    }

    /**
     * Handles "PD" command whether event calendar is empty or not
     * Prints entire calendar sorted by department if calendar is not empty
     * Prints error message if event calendar is empty
     *
     * @param tokenizer as StringTokenizer
     */
    private void handlePDCommand(StringTokenizer tokenizer) {
        if (calendar.isEmpty()) {
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by department *");
        calendar.printByDepartment();
        System.out.println("* end of event calendar *");
    }

    /**
     * Creates event specific for "A" command (7 total tokens including "A")
     *
     * @param tokenizer as StringTokenizer
     * @return created event as Event object, null if unable to validate
     * all parameters
     */
    private Event createEventForACommand(StringTokenizer tokenizer) {
        String dateString = tokenizer.nextToken();
        String startTimeString = tokenizer.nextToken();
        String locationString = tokenizer.nextToken();
        String departmentString = tokenizer.nextToken();
        String emailString = tokenizer.nextToken();
        int duration = Integer.parseInt(tokenizer.nextToken());

        Date date = parseDate(dateString);
        if (!(validateAllParams(dateString, startTimeString,
                locationString, departmentString, emailString, date, duration))) {
            return null;
        }

        Department department = Department.valueOf(departmentString.toUpperCase());
        Contact contact = new Contact(department, emailString);
        Timeslot startTime = Timeslot.valueOf(startTimeString.toUpperCase());
        Location location = Location.valueOf(locationString.toUpperCase());

        return new Event(date, startTime, location, contact, duration);
    }

    /**
     * Creates event specific for "R" command (5 total tokens including "R")
     *
     * @param tokenizer as StringTokenizer
     * @return created event as Event object, null if unable to validate parameters
     */
    private Event createEventForRCommand(StringTokenizer tokenizer) {
        String dateString = tokenizer.nextToken();
        String startTimeString = tokenizer.nextToken();
        String locationString = tokenizer.nextToken();
        Date date = parseDate(dateString);

        if (!(isValidDate(date, dateString))) {
            return null;
        }

        if (!(isValidTimeslot(startTimeString))) {
            return null;
        }

        if (!(isValidLocation(locationString))) {
            return null;
        }

        Timeslot startTime = Timeslot.valueOf(startTimeString.toUpperCase());
        Location location = Location.valueOf(locationString.toUpperCase());
        Contact randomContact = new Contact(Department.CS, "cs@rutgers.edu");

        return new Event(date, startTime, location, randomContact, 0);
    }

    /**
     * Checks if date is valid based on 3 conditions: valid calendar date,
     * future date, date within 6 months
     *
     * @param date       date of event as Date
     * @param dateString date of event as string
     * @return true if date passes all conditions, false otherwise
     */
    private boolean isValidDate(Date date, String dateString) {
        if (!(date.isValid())) {
            System.out.println(dateString + ": Invalid calendar date!");
            return false;
        }
        if (!futureDateCheck(date)) {
            System.out.println(dateString + ": Event date must be a future date!");
            return false;
        } else if (!sixMonthDateCheck(date)) {
            System.out.println(dateString + ": Event date must be within 6 months!");
            return false;
        }
        return true;
    }

    /**
     * Parses date string from command line and converts to Date object
     *
     * @param dateString date of event as String
     * @return created date as Date object
     */
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

    /**
     * Checks if timeslot input is valid based on Timeslot enum
     *
     * @param timeslotString timeslot as String
     * @return true if String is a valid timeslot, false if invalid
     */
    public static boolean isValidTimeslot(String timeslotString) {
        try {
            Timeslot.valueOf(timeslotString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid time slot!");
            return false;
        }
    }

    /**
     * Checks if location input is valid based on Location enum
     *
     * @param locationString location as String
     * @return true if String is a valid location, false if invalid
     */
    public static boolean isValidLocation(String locationString) {
        try {
            Location.valueOf(locationString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid location!");
            return false;
        }
    }

    /**
     * Checks if contact is valid based on department enum and email format
     *
     * @param departmentString department as String
     * @param emailString      email as String
     * @return true if String is a valid location, false if invalid
     */
    public static boolean isValidContact(String departmentString, String emailString) {
        try {
            Department department = Department.valueOf(departmentString.toUpperCase());
            Contact contact = new Contact(department, emailString);

            if (contact.isValid()) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            // invalid, results in error
        }

        System.out.println("Invalid contact information!");
        return false;
    }


    /**
     * Checks if duration of event is less than MIN_DURATION and
     * greater than MAX_DURATION
     *
     * @param duration of event as int
     * @return true if duration is valid, false if invalid
     */
    private boolean isValidDuration(int duration) {
        if (duration < Constants.MIN_DURATION || duration > Constants.MAX_DURATION) {
            System.out.println("Event duration must be at least " +
                    "30 minutes and at most 120 minutes");
            return false;
        }
        return true;
    }

    /**
     * Validates all parameters for event
     *
     * @param dateString       date as string
     * @param startTimeString  start time as string
     * @param locationString   location as string
     * @param departmentString department as string
     * @param emailString      email as string
     * @param date             date of event
     * @param duration         duration of event
     * @return true if all parameters are valid, false otherwise
     */
    private boolean validateAllParams(String dateString, String startTimeString,
                                      String locationString, String departmentString, String emailString, Date date, int duration) {
        if (!(isValidDate(date, dateString))) {
            return false;
        }
        if (!(isValidTimeslot(startTimeString))) {
            return false;
        }
        if (!(isValidLocation(locationString))) {
            return false;
        }
        if (!(isValidContact(departmentString, emailString))) {
            return false;
        }
        if (!(isValidDuration(duration))) {
            return false;
        }

        return true;
    }

    /**
     * Checks if event is not on calendar already
     *
     * @param event user is attempting to schedule
     * @return true if event is not on calendar, false if it is
     */
    private boolean eventNotOnCalendar(Event event) {
        if (calendar.contains(event)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if date user is inputting is in the future
     *
     * @param date of event
     * @return true if date is in the future, false otherwise
     */
    private boolean futureDateCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + Constants.MONTH_STANDARDIZER;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        if (date.getYear() > currentYear) {
            return true;
        } else if (date.getYear() == currentYear) {
            if (date.getMonth() > currentMonth) {
                return true;
            } else if (date.getMonth() == currentMonth) {
                if (date.getDay() > currentDay) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if date difference is more than MAX_BOOKING_MONTHS_AHEAD
     *
     * @param date of event
     * @return true if date difference is less than MAX_BOOKING_MONTHS_AHEAD,
     * false otherwise
     */
    private boolean sixMonthDateCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + Constants.MONTH_STANDARDIZER;
        int currentDay = calendar.get(Calendar.DATE);

        int monthsDifference = (date.getYear() - currentYear) * Constants.MONTHS_IN_YEAR + (date.getMonth() - currentMonth);

        if (monthsDifference < Constants.MAX_BOOKING_MONTHS_AHEAD) {
            return true;
        } else if (monthsDifference == Constants.MAX_BOOKING_MONTHS_AHEAD) {
            if (date.getMonth() != currentMonth) {
                return date.getDay() < currentDay;
            } else {
                return true;
            }
        }
        return false;
    }
}




