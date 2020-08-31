package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents a ListCommand where user wants to list all the tasks.
 */
public class ListCommand extends Command{

    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the command to list and display tasks in tasklist.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksString = tasks.listTasks();
        return ui.showAction("\t Here are the tasks in your list:\n" + tasksString);
    }
}
