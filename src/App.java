// this is the main class that will bring the classes together with user input
//

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        taskManager manager = new taskManager();
        System.out.println("Welcome to your Task Planner!");

        // Set up user's schedule
        System.out.print("Do you work? (y/n): ");
        String work = scanner.next();
        if (work.equalsIgnoreCase("y")) {
            String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
            for (int d = 0; d < 7; d++) {
                System.out.print("Enter work hours for " + days[d] + " (start end, e.g. 9 17, or 0 0 if none): ");
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                if (start != 0 || end != 0) {
                    manager.setUnavailable(d, start, end);
                }
            }
        }
        System.out.print("Do you have school? (y/n): ");
        String school = scanner.next();
        if (school.equalsIgnoreCase("y")) {
            String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
            for (int d = 0; d < 7; d++) {
                System.out.print("Enter school hours for " + days[d] + " (start end, e.g. 8 15, or 0 0 if none): ");
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                if (start != 0 || end != 0) {
                    manager.setUnavailable(d, start, end);
                }
            }
        }
        System.out.print("What time do you usually go to sleep? (hour, e.g. 23): ");
        int sleepStart = scanner.nextInt();
        System.out.print("How many hours do you sleep? (e.g. 8): ");
        int sleepHours = scanner.nextInt();
        int sleepEnd = (sleepStart + sleepHours) % 24;
        for (int d = 0; d < 7; d++) {
            if (sleepStart < sleepEnd) {
                manager.setUnavailable(d, sleepStart, sleepEnd);
            } else {
                manager.setUnavailable(d, sleepStart, 24);
                manager.setUnavailable(d, 0, sleepEnd);
            }
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add task");
            System.out.println("2. View schedule");
            System.out.println("3. Assign task to specific time");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // new line

            if (choice == 1) {
                System.out.println("Enter task name :");
                String name = scanner.nextLine();

                System.out.println("Enter task duration in minutes:");
                int duration = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter category (school, personal, hygene, work, etc.):");
                String category = scanner.nextLine();

                if (category.trim().isEmpty()) {
                    category = "uncategorized";
                }

                manager.addTask(name, duration, category);
                System.out.println("Task added!");
            }

            else if (choice == 2) {
                manager.displaySchedule();
            }

            else if (choice == 3) {
                System.out.println("Enter day (0-6, 0=Monday):");
                int day = scanner.nextInt();
                System.out.println("Enter hour (0-23):");
                int hour = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter task name:");
                String name = scanner.nextLine();
                System.out.println("Enter duration:");
                int duration = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter category:");
                String category = scanner.nextLine();
                Task task = new Task(name, duration, 3, category, false);
                manager.assignTask(day, hour, task);
                System.out.println("Task assigned!");
            }

            else if (choice == 4) {
                break;
            }

            else {
                System.out.println("Invalid option.");
            }
        }

        System.out.println("Total time: " + manager.getTotalTime() + " minutes");
        scanner.close();
    }
}
