package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an UnknownCommand where user input is unclear.
 */
public class UnknownCommand extends Command {

    private String gibberish;

    /**
     * Creates an UnknownCommand.
     *
     * @param gibberish Full input by the user.
     */
    public UnknownCommand(String gibberish) {
        this.gibberish = gibberish;
    }

    /**
     * Executes the command to inform user of unclear input.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @throws DukeException Always throws exception as input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(String.format(
                "I'm sorry, but I don't know what \"%s\" means :-(\n"
                + "\t type \"help\" for a list of commands!", gibberish));
    }
}
