package project1;

import java.util.Scanner;

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

            switch (command) {
                case "Q" -> isRunning = false;
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
