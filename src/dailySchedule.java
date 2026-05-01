// this will store the users planned schedule for the day
// it will have a 2d array of tasks for each hour of the day and each day of the week
// it will also have a list of task categories for organization
// methods it will have: addTask, removeTask, getTask, getTotalTime

import java.util.ArrayList;
import java.util.List;

public class dailySchedule {
    private Task[][] schedule;
    private List<String> taskCategories;

    public dailySchedule() {
        this.schedule = new Task[7][24]; // 7 days, 24 hours
        this.taskCategories = new ArrayList<>();

    }
    // method to add a task at a specific day and hour
    public void addTaskAt(int day, int hour, Task task) {
        if ( day >= 0 && day < schedule.length &&
            hour >= 0 && hour < schedule[day].length) {
                schedule[day][hour] = task;
        }
    }
    // method to remove a task at a specific day and hour
    public void removeTaskAt(int day, int hour) {
        if ( day >= 0 && day < schedule.length &&
            hour >= 0 && hour < schedule[day].length) {
                schedule[day][hour] = null;
        }
    }

    // method to get a task at a specific day and hour
    public Task getTaskAt(int day, int hour) {
        if ( day >= 0 && day < schedule.length &&
            hour >= 0 && hour < schedule[day].length) {
            return schedule[day][hour];
        }
        return null;
    }

    public void addCategory(String category) {
        if (!taskCategories.contains(category)) {
            taskCategories.add(category);
        }
    }

    public List<String> getTaskCategories() {
        return taskCategories;
    }

    // method to get total time of tasks for the whole schedule
    public int getTotalTime() {
        int totalTime = 0;
        for (int day = 0; day < schedule.length; day++) {
            for (int hour = 0; hour < schedule[day].length; hour++) {
                Task task = schedule[day][hour];
                if (task != null) {
                    totalTime += task.getDuration();
                }
            }
        }
        return totalTime;
    }
}
