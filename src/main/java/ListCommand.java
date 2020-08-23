public class ListCommand extends Command{

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksString = tasks.listTasks();
        ui.showAction("\t Here are the tasks in your list:\n" + tasksString);
    }
}
