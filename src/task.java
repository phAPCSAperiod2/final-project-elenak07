//this class represents a single task that the user enters
//name, duration, importance, category, completion status

public class Task {
    //constructors, getters, setters
    private String name;
    private int duration; //in mins
    private int importance; //1-5
    private String category; //work, school, personal, etc
    private boolean isCompleted;

    public Task (String name, int duration, int importance, String category, boolean isCompleted) {
        this.name = name;
        this.duration = duration;
        this.importance = importance;
        this.category = category;
        this.isCompleted = false;
    }
        //getters and setters
        public String getName() {
            return name;
        }

        public int getDuration() {
            return duration;
        }

        public int getImportance() {
            return importance;
        }

        public String getCategory() {
            return category;
        }

        public boolean isCompleted() {
            return isCompleted;
        }
        public void markComplete() {
            isCompleted = true;
        }
    }
