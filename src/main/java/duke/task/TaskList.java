package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList already containing Tasks.
     * This is used if Storage is able to load data from the text file.
     *
     * @param taskStrings List of lines in the text file, representing Tasks.
     */
    public TaskList(ArrayList<String> taskStrings) {
        tasks = new ArrayList<>();
        for (String taskString : taskStrings) {
            this.tasks.add(lineToTask(taskString));
        }
    }

    /**
     * Converts a line of text into a Task.
     *
     * @param taskString Line of text from text file.
     * @return Task which is specified by the line of text
     */
    public Task lineToTask(String taskString) {
        String[] taskLine = taskString.split("~");
        Task task = null;
        switch (taskLine[0]) {
        case "T":
            task = new ToDo(taskLine[2]);
            break;
        case "D":
            task = new Deadline(taskLine[2], taskLine[3]);
            break;
        case "E":
            task = new Event(taskLine[2], taskLine[3]);
            break;
        default:
            break;
        }
        if (taskLine[1].equals("1")) {
            assert task != null;
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns a String listing out all the tasks in the tasklist.
     *
     * @return String representation of list of tasks.
     */
    public String listTasks() {
        String tasks = "";
        Task t;
        for (int i = 0; i < this.tasks.size(); i++) {
            t = this.tasks.get(i);
            tasks += String.format("  %d.%s%n", i + 1, t);
        }
        return tasks;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void addTasks(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Number of items in TaskList.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Converts the TaskList into lines of text.
     *
     * @return List containing lines of text.
     */
    public ArrayList<String> tasksToText() {
        ArrayList<String> strings = new ArrayList<>();
        for (Task task : tasks) {
            strings.add(task.toData());
        }
        return strings;
    }

    /**
     * Returns a String listing out all the tasks that match the keyword.
     *
     * @return String representation of list of tasks.
     */
    public String findTasks(String keyword) {
        String tasks = "";
        Task t;
        int numMatch = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            t = this.tasks.get(i);
            if (t.hasKeyword(keyword)) {
                numMatch++;
                tasks += String.format("  %d.%s%n", numMatch, t);
            }
        }
        return tasks;
    }

    /**
     * Deletes and returns the task at a position in the list.
     *
     * @param idx Position of the Task in the list of tasks.
     * @return Task which was deleted.
     */
    public Task deleteTask(int idx) {
        Task t = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        return t;
    }

    /**
     * Marks a tast in the list as done.
     *
     * @param idx Position of Task in the list of tasks.
     * @return Task which was marked as done.
     */
    public Task markTask(int idx) {
        Task t = tasks.get(idx - 1);
        t.markAsDone();
        return t;
    }
}
