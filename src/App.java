// this is the main class that will bring the classes together with user input
//
//scanner
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //scanner for user input
        Scanner scanner = new Scanner(System.in);
        //taskManager class to manage tasks and schedule
        taskManager manager = new taskManager();
        // welcome message
        System.out.println("Welcome to your Task Planner!");

        // Set up user schedule
        // ask about work, school, and sleep to put those times off limits for tasks
        System.out.print("Do you work? (y/n): ");
        String work = scanner.next();
        // if statement to set work hours as unavailable
        if (work.equalsIgnoreCase("y")) {
            String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
            //for loop to get work hours for each day
            for (int d = 0; d < 7; d++) {
                System.out.print("Enter work hours for " + days[d] + " (start end, e.g. 9 17, or 0 0 if none): ");
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                //if statement to set unavailable times for work hours
                if (start != 0 || end != 0) {
                    manager.setUnavailable(d, start, end);
                }
            }
        }
        //ask about school hours
        System.out.print("Do you have school? (y/n): ");
        String school = scanner.next();
        // if statement to set school hours as unavailable
        if (school.equalsIgnoreCase("y")) {
            String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
            // for loop to get school hours for each day
            for (int d = 0; d < 7; d++) {
                System.out.print("Enter school hours for " + days[d] + " (start end, e.g. 8 15, or 0 0 if none): ");
                // scanner to get start and end hours for school
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                // if statement to set unavailable times for school hours
                if (start != 0 || end != 0) {
                    manager.setUnavailable(d, start, end);
                }
            }
        }
        // ask about sleep hours
        System.out.print("What time do you usually go to sleep? (hour, e.g. 23): ");
        int sleepStart = scanner.nextInt();
        System.out.print("How many hours do you sleep? (e.g. 8): ");
        int sleepHours = scanner.nextInt();
        int sleepEnd = (sleepStart + sleepHours) % 24;
        // for loop to set unavailable times for sleep hours
        for (int d = 0; d < 7; d++) {
            if (sleepStart < sleepEnd) {
                manager.setUnavailable(d, sleepStart, sleepEnd);
            } else {
                manager.setUnavailable(d, sleepStart, 24);
                manager.setUnavailable(d, 0, sleepEnd);
            }
        }
        // while loop to iterate through the user menu everytime a user completes an option
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add task");
            System.out.println("2. View schedule");
            System.out.println("3. Assign task to specific time");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // new line
            // if statment for first menu choice
            if (choice == 1) {
                System.out.println("Enter task name :");
                String name = scanner.nextLine();

                System.out.println("Enter task duration in minutes:");
                int duration = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter category (school, personal, hygene, work, etc.):");
                String category = scanner.nextLine();
                // check if category is empty
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
