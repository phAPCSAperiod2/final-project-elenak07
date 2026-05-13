//this class will coordinate the tasks and manage the task list
//it will store the users' dailySchedule and the list of task categories
//methods it will have: addTask, removeTask, assignTask, rankTask

public class taskManager {
    private dailySchedule schedule;

    public taskManager() {
        this.schedule = new dailySchedule();
        // this.taskCategories = schedule.getTaskCategories();
    }

    public void addTask(String name, int duration, String category) {
        int defaultImportance = 3; // default importance level

        Task newTask = new Task(name, duration, defaultImportance, category, false);
        int hoursNeeded = (int) Math.ceil(duration / 60.0);

        // Find consecutive free slots
        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour <= 24 - hoursNeeded; hour++) {
                boolean canAssign = true;
                for (int i = 0; i < hoursNeeded; i++) {
                    if (schedule.getTaskAt(day, hour + i) != null || !schedule.isAvailable(day, hour + i)) {
                        canAssign = false;
                        break;
                    }
                }
                if (canAssign) {
                    for (int i = 0; i < hoursNeeded; i++) {
                        schedule.addTaskAt(day, hour + i, newTask);
                    }
                    System.out.println("Task '" + name + "' assigned to " + getDayName(day) + " from " + hour
                            + ":00 to " + (hour + hoursNeeded) + ":00");
                    return;
                }
            }
        }
        System.out.println("No available time slot found for task '" + name + "'");
    }

    public void removeTask(int day, int hour) {
        schedule.removeTaskAt(day, hour);
    }

    public void assignTask(int day, int hour, Task task) {
        schedule.addTaskAt(day, hour, task);
    }

    public void setUnavailable(int day, int startHour, int endHour) {
        schedule.setUnavailable(day, startHour, endHour);
    }

    public int getTotalTime() {
        return schedule.getTotalTime();
    }

    private String getDayName(int day) {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        return days[day];
    }

    public void displaySchedule() {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        for (int day = 0; day < 7; day++) {
            System.out.println(days[day] + ":");
            for (int hour = 0; hour < 24; hour++) {
                Task task = schedule.getTaskAt(day, hour);
                if (task != null) {
                    System.out.println("  " + hour + ":00 - " + task.getName() + " (" + task.getDuration() + " min, "
                            + task.getCategory() + ")");
                } else if (!schedule.isAvailable(day, hour)) {
                    System.out.println("  " + hour + ":00 - Busy");
                }
            }
            System.out.println();
        }
    }
}
