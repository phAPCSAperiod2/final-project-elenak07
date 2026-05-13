//this class groups tasks into categories for organization
// schoolwork, sleep, work, hobbies, etc.

//add and remove tasks
//get all tasks in category
//calculate total time for catorgy

import java.util.ArrayList;
import java.util.List;

//create the 2d array for tasks
public class taskCategory {
    private String categoryName;
    private Task[][] taskGrid;
    private List<Task> tasks; // list to hold tasks in the category

    //constructor for category name and initializing the task grid and task list
    public taskCategory(String categoryName) {
        this.categoryName = categoryName;
        this.taskGrid = new Task[2][3];  // Example: 2 rows, 3 columns
        this.tasks = new ArrayList<>(); //initialize the task list
}

public String getCategoryName() {
    return categoryName;

}

    // method to add a task at a specific position
    public void addTaskAt(int row, int col, Task task) {
        if (row < taskGrid.length && col < taskGrid[row].length) {
            taskGrid[row][col] = task;
        }
}
    // add task
    public void addTask(Task task) {
        tasks.add(task);
    }
    // remove task
    public void removeTask(Task task) {
        tasks.remove(task);
    }
    // array list to return tasks
    public List<Task> getTasks() {
        return tasks;
    }
    // calculating total time for category
    public int getTotalTime() {
        int totalTime = 0;
        // for loop to iterate through tasks
        for (Task task : tasks){
            totalTime+= task.getDuration();
        }
        return totalTime;
    }
}
