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
            if(command.isEmpty()){continue;}

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
                    default -> System.out.println(firstToken + " is an invalid command!");
                }

            }

        System.out.println("Event Organizer terminated");
        scanner.close();
    }

    private void handleACommand(StringTokenizer tokenizer) {
        System.out.println();
        Event event = parseAndCreateEvent(tokenizer);

        if (!(eventOnCalendar(event)) || event == null) {
            return;
        }
        calendar.add(event);
        System.out.println("Event added to the calendar.");
    }

    private void handleRCommand(StringTokenizer tokenizer) {
        System.out.println();
        String dateString = tokenizer.nextToken();
        String startTimeString = tokenizer.nextToken();
        String locationString = tokenizer.nextToken();
        Date date = parseDate(dateString);
        if(!(validateDate(date,dateString))){return;}
        if(!(isValidTimeslot(startTimeString))){return;}
        if(!(isValidLocation(locationString))){return;}
        Timeslot startTime = Timeslot.valueOf(startTimeString.toUpperCase());
        Location location = Location.valueOf(locationString.toUpperCase());
        Event event = new Event(date, startTime, location, null, 0);

        if (!(eventOnCalendar(event))) {
            System.out.println("Cannot remove; event is not in the calendar!");
        }
        calendar.remove(event);
        System.out.println("Event has been removed from the calendar!");
    }

    private void handlePCommand(StringTokenizer tokenizer) {

        if(calendar.isEmpty()){
            System.out.println("Event calendar is empty!");
        return;
        }
        calendar.print();
    }

    private void handlePECommand(StringTokenizer tokenizer) {
        if(calendar.isEmpty()){
            System.out.println("Event calendar is empty!");
            return;
        }
        calendar.printByDateAndTimeslot();
    }

    private void handlePCCommand(StringTokenizer tokenizer) {
        if(calendar.isEmpty()){
            System.out.println("Event calendar is empty!");
            return;
        }
        calendar.printByCampus();
    }

    private void handlePDCommand(StringTokenizer tokenizer) {
        if(calendar.isEmpty()){
            System.out.println("Event calendar is empty!");
            return;
        }
        calendar.printByDepartment();
    }

    private Event parseAndCreateEvent(StringTokenizer tokenizer) {
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

    private boolean validateDate(Date date, String dateString) {
        if(!(date.isValid())){
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
            System.out.println("Invalid time slot!");
            return false;
        }
    }

    public static boolean isValidLocation(String locationString) {
        try {
            Location.valueOf(locationString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid location!");
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
        int atIndex = emailString.indexOf('@');
        if (atIndex == Constants.NOT_FOUND) {
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

    private boolean isValidContact(String departmentString, String emailString) {
        if (isValidDepartment(departmentString) && isValidEmail(emailString)) {
            return true;
        }
        System.out.println("Invalid contact information!");
        return false;
    }

    private boolean isValidDuration(int duration){
        if (duration < Constants.MIN_DURATION || duration > Constants.MAX_DURATION) {
            System.out.println("Event duration must be at least " +
                    "30 minutes and at most 120 minutes");
            return false;
        }
        return true;
    }


    private boolean validateAllParams(String dateString, String startTimeString,
    String locationString, String departmentString, String emailString, Date date, int duration){
        if(!(validateDate(date,dateString))){return false;}
        if(!(isValidTimeslot(startTimeString))){return false;}
        if(!(isValidLocation(locationString))){return false;}
        if(!(isValidDepartment(departmentString))){return false;}
        if(!(isValidContact(departmentString, emailString))){return false;}
        if(!(isValidDuration(duration))){return false;}

        return true;
    }

    private boolean eventOnCalendar(Event event) {
        if(calendar.contains(event)){
            System.out.println("The event is already on the calendar.");
            return false;
        }
        return true;
    }

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

    private boolean sixMonthDateCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + Constants.MONTH_STANDARDIZER;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        int monthsDifference = (date.getYear() - currentYear) * Constants.MONTHS_IN_YEAR +
                (date.getMonth() - currentMonth);

        return monthsDifference <= Constants.MAX_BOOKING_MONTHS_AHEAD;
    }

}
