package project1;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;

public class EventOrganizer {
    private boolean isRunning;
    private EventCalendar calendar;

    private final int MONTH_STANDARDIZER = 1;
    private final int MONTHS_IN_YEAR = 12;

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
        Date date = parseAndValidateDate(tokenizer.nextToken());

        Timeslot startTime = null;
        Location location = null;
        Contact contact = null;
        String startTimeString = tokenizer.nextToken();
        String locationString = tokenizer.nextToken();
        String departmentString = tokenizer.nextToken();
        String emailString = tokenizer.nextToken();
        int duration = Integer.parseInt(tokenizer.nextToken());

        if(isValidTimeslot(startTimeString)){
            startTime = Timeslot.valueOf(startTimeString);
        }else {return;}

        if(isValidLocation(locationString)){
            location = Location.valueOf(locationString);
        }else {return;}

        if(isValidDepartment(departmentString) && isValidEmail(emailString)){
            Department department = Department.valueOf(departmentString);
            contact = new Contact(department, emailString);
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

    private Date parseAndValidateDate(String dateString) {
        Date date = parseDate(dateString);
        if(!(date.isValid())){
            System.out.println(dateString + ": Invalid calendar date!");
        }
        if (!futureDateCheck(date)) {
            System.out.println(dateString + ": Event date must be a future date!");
        } else if (!sixMonthDateCheck(date)) {
            System.out.println(dateString + ": Event date must be within 6 months!");
        }
        return date;
    }
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

    private boolean eventOnCalendar() {

    }
    private boolean futureDateCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + MONTH_STANDARDIZER;
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
     * Do we need to consider day difference,
     * or just month and year?
     *
     * do 12 and 6 need to be changed from magic numbers?
     */
    private boolean sixMonthDateCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + MONTH_STANDARDIZER;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        int monthsDifference = (date.getYear() - currentYear) * MONTHS_IN_YEAR + (date.getMonth() - currentMonth);

        return monthsDifference <= 6;
    }


    public static void main(String[] args) {
        EventOrganizer organizer = new EventOrganizer();
        organizer.run();
    }
}
