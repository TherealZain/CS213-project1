package project1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class EventOrganizer {
    private boolean isRunning;
    private static final int A_COMMAND_TOKENS = 7;

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
                case "R" -> handleRCommand();
                case "P" -> handlePCommand();
                case "PE" -> handlePECommand();
                case "PC" -> handlePCCommand();
                case "PD" -> handlePDCommand();
            }
        }

        System.out.println("Event Organizer terminated");
        scanner.close();
    }

    private void handleACommand(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() == A_COMMAND_TOKENS) {
            String date = tokenizer.nextToken();
            String startTime = tokenizer.nextToken();
            String location = tokenizer.nextToken();
            String department = tokenizer.nextToken();
            String email = tokenizer.nextToken();
            int duration = Integer.parseInt(tokenizer.nextToken());

            Event event = new Event(date, startTime, location, contact, duration);
        }
    }
    private void handleRCommand() {
    }

    private void handlePCommand() {
    }

    private void handlePECommand() {
    }

    private void handlePCCommand() {
    }

    private void handlePDCommand() {
    }

    public static void main(String[] args) {
        EventOrganizer organizer = new EventOrganizer();
        organizer.run();
    }
}
