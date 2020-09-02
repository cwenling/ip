package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a DoneCommand where user wants to mark a task as done.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DoneCommand.
     *
     * @param taskNumber Index of task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     * @throws DukeException If command is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.markTask(taskNumber);
            storage.save(tasks);
            return ui.showAction(String.format("  Nice! I've marked this task as done:\n"
                    + "    %s\n", t));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't complete a task that does not exist.");
        }
    }
}
