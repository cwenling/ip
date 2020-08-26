package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the Parser used to make sense of user commands.
 */
public class Parser {
    /**
     * Parses user input into executable Commands.
     *
     * @param fullCommand Entire user input.
     * @return Duke Command corresponding to input.
     * @throws DukeException If task is not successfully parsed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command command;

        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else if (fullCommand.startsWith("done")) {
            command = new DoneCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        } else if (fullCommand.startsWith("delete")) {
            command = new DeleteCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        } else if (fullCommand.startsWith("todo")) {
            command = new AddCommand(parseTask("todo", fullCommand));
        } else if (fullCommand.startsWith("deadline")) {
            command = new AddCommand(parseTask("deadline", fullCommand));
        } else if (fullCommand.startsWith("event")) {
            command = new AddCommand(parseTask("event", fullCommand));
        } else if (fullCommand.startsWith("find")) {
            command = new FindCommand(fullCommand.split(" ")[1]);
        } else {
            command = new UnknownCommand(fullCommand);
        }
        return command;
    }

    /**
     * Parses a task input into Task objects.
     *
     * @param type Type of task (todo, deadline, or event).
     * @param task The full input of the task.
     * @return Task obtained from the input.
     * @throws DukeException If task is not successfully parsed.
     */
    public static Task parseTask(String type, String task) throws DukeException {
        Task t;
        switch (type) {
        case "todo":
            if (task.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            t = new ToDo(task.substring(5));
            break;
        case "deadline":
            if (task.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] taskArr = task.substring(9).split(" /by ");
            try {
                t = new Deadline(taskArr[0], taskArr[1]);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of a deadline.");
            }
            break;
        case "event":
            if (task.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] taskArr2 = task.substring(6).split(" /at ");
            try {
                t = new Event(taskArr2[0], taskArr2[1]);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of an event.");
            }
            break;
        default:
            throw new DukeException("Unexpected value: " + type);
        }
        return t;
    }
}
