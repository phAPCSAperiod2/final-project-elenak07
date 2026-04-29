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
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j <schedule[i].length; j++) {
                if (i == day && j == hour) {
                    schedule[i][j] = task;
                }
            }
        }
    }
    // method to remove a task at a specific day and hour
    public void removeTaskAt(int day, int hour, Task task) {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule.length; j++) {
                if (i == day && j == hour) {
                    schedule [i][j] = null;
                }
            }
        }
    }

    // method to get a task at a specific day and hour
    public void getTaskAt(int day, int hour, Task task) {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule.length; j++) {
                if (i == day && j == hour) {
                    task = schedule[i][j];
                }
            }
        }
    }

    // method to get total time of tasks for a specific day
    public void getTotalTime(int day, int hour, Task task) {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule.length; j++) {
                if (i == day && j == hour) {
                    int totalTime = 0;
                    if (schedule[i][j] != null) {
                        totalTime += schedule[i][j].getDuration();
                    }
                }
            }
        }
    }

    
    }
