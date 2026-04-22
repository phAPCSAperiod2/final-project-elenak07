//this class groups tasks into categories for organization
// schoolwork, sleep, work, hobbies, etc.

//add/remove tasks
//get all tasks in category
//calculate total time for catorgy

public class taskCategory {
    //create the 2d array for tasks

    //private String categoryName;
    private Task[][] taskGrid;  // 2D array for tasks

    //
    public taskCategory(String categoryName) {
        this.categoryName = categoryName;
        this.taskGrid = new Task[2][3];  // Example: 2 rows, 3 columns
}

    // method to add a task at a specific position
    public void addTaskAt(int row, int col, Task task) {
        if (row < taskGrid.length && col < taskGrid[row].length) {
            taskGrid[row][col] = task;
        }
}

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getTotalTime() {
        int totalTime = 0;
        for (Task task : tasks){
            totalTime+= task.getDuration();
        }
        return totalTime;
    }
}
