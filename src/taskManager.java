//this class will coordinate the tasks and manage the task list
//it will store the users' dailySchedule and the list of task categories
//methods it will have: addTask, removeTask, assignTask, rankTask

public class taskManager {
    private dailySchedule schedule;

    public taskManager() {
        this.schedule = new dailySchedule();
        //this.taskCategories = schedule.getTaskCategories();
    }

    public void addTask(String name, int duration, String category) {
        int defaultImportance = 3; // default importance level

        Task newTask = new Task(name, duration, defaultImportance, category, false);
        // for simplicity, we will add the task to the first available slot in the schedule
        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                if (schedule.getTaskAt(day, hour) == null) {
                    schedule.addTaskAt(day, hour, newTask);
                    return;
                }
            }
        }
    }

    public void removeTask(int day, int hour) {
        schedule.removeTaskAt(day, hour);
    }

    public void assignTask(int day, int hour, Task task) {
        schedule.addTaskAt(day, hour, task);
    }

    public Task getTask(int day, int hour) {
        return schedule.getTaskAt(day, hour);
    }

    public int getTotalTime() {
        return schedule.getTotalTime();
    }
}
