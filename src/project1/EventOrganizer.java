package project1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class EventOrganizer {
    private boolean isRunning;

    public EventOrganizer() {
        isRunning = true;
        System.out.println("Event Organizer running....");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String command = scanner.nextLine();

            StringTokenizer tokenizer = new StringTokenizer(command);

            switch (command) {
                case "Q" -> isRunning = false;
                case "A" -> handleACommand(tokenizer);
                case "R" -> handleRCommand(tokenizer);
                case "P" -> handlePCommand(tokenizer);
                case "PE" -> handlePECommand(tokenizer);
                case "PC" -> handlePCCommand(tokenizer);
                case "PD" -> handlePDCommand(tokenizer);
            }
        }

        System.out.println("Event Organizer terminated");
        scanner.close();
    }

    private void handleACommand(StringTokenizer tokenizer) {
        String stringDate = tokenizer.nextToken();
        String stringStartTime = tokenizer.nextToken();
        String stringLocation = tokenizer.nextToken();
        String stringDepartment = tokenizer.nextToken();
        String stringEmail = tokenizer.nextToken();
        int duration = Integer.parseInt(tokenizer.nextToken());

        Date date = parseDate(stringDate);
        Timeslot startTime = ;
        Location location = new Location(stringLocation);
        Contact contact = new Contact(stringDepartment, stringEmail);

        Event event = new Event(date, startTime, location, contact, duration);
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

    private Timeslot parseTimeslot(String stringTimeslot) {

    }

    public static void main(String[] args) {
        EventOrganizer organizer = new EventOrganizer();
        organizer.run();
    }
}
