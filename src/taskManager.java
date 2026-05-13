//this class will coordinate the tasks and manage the task list
//it will store the users' dailySchedule and the list of task categories
//methods it will have: addTask, removeTask, assignTask, rankTask

public class taskManager {
    private dailySchedule schedule;

    public taskManager() {
        // this will initialize the daily schedule and task categories
        this.schedule = new dailySchedule();
    }

    public void addTask(String name, int duration, String category) {
        // default importance level
        int defaultImportance = 3;
        // create a new task with the provided details
        Task newTask = new Task(name, duration, defaultImportance, category, false);
        int hoursNeeded = (int) Math.ceil(duration / 60.0);

        // find consecutive free slots
        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour <= 24 - hoursNeeded; hour++) {
                boolean canAssign = true;
                // check if the required consecutive hours are available
                for (int i = 0; i < hoursNeeded; i++) {
                    if (schedule.getTaskAt(day, hour + i) != null || !schedule.isAvailable(day, hour + i)) {
                        canAssign = false;
                        break;
                    }
                }
                // if we found a suitable time slot, assign the task
                if (canAssign) {
                    for (int i = 0; i < hoursNeeded; i++) {
                        schedule.addTaskAt(day, hour + i, newTask);
                    }
                    // print confirmation message
                    System.out.println("Task '" + name + "' assigned to " + getDayName(day) + " from " + hour
                            + ":00 to " + (hour + hoursNeeded) + ":00");
                    return;
                }
            }
        }
        System.out.println("No available time slot found for task '" + name + "'");
    }
    // method to get the name of the day based on the index
    public void removeTask(int day, int hour) {
        schedule.removeTaskAt(day, hour);
    }
    // method to get a task at a specific day and hour
    public void assignTask(int day, int hour, Task task) {
        schedule.addTaskAt(day, hour, task);
    }
    // method to set unavailable time slots for a specific day
    public void setUnavailable(int day, int startHour, int endHour) {
        schedule.setUnavailable(day, startHour, endHour);
    }
    // method to check if a specific time slot is available
    public int getTotalTime() {
        return schedule.getTotalTime();
    }
    // method to display the schedule
    private String getDayName(int day) {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        return days[day];
    }
    // method to display the schedule
    public void displaySchedule() {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        // for loop to iterate through each day and hour to display tasks and busy times
        for (int day = 0; day < 7; day++) {
            System.out.println(days[day] + ":");
            // for loop to iterate through each hour of the day
            for (int hour = 0; hour < 24; hour++) {
                Task task = schedule.getTaskAt(day, hour);
                // if statement to check if there is a task at the current time slot and print it, otherwise check if it's busy
                if (task != null) {
                    System.out.println("  " + hour + ":00 - " + task.getName() + " (" + task.getDuration() + " min, "
                            + task.getCategory() + ")");
                }
                // if statement to check if the time slot is unavailable and print busy``
                else if (!schedule.isAvailable(day, hour)) {
                    System.out.println("  " + hour + ":00 - Busy");
                }
            }
            System.out.println();
        }
    }
}
