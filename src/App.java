// this is the main class that will bring the classes together with user input
//

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        taskManager manager = new taskManager();

        System.out.println("Welcome to your Task Planner!");

        while (true){
            System.out.println("Enter task name: (press enter when done adding tasks)");
            String name = scanner.nextLine();

            if (name.equals(" ")){
                break;
            }

            System.out.println("Enter task duration in minutes:");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter category ");
            String category = scanner.nextLine();

            if (category.equals(" ")){
                category = "uncategorized";
            }

            manager.addTask(name, duration, category);
            System.out.println("Task added!");
        }

        System.out.println("Total time: " + manager.getTotalTime() + "minutes");
        scanner.close();
    }
}
