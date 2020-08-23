public class ExitCommand extends Command{

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
        storage.save(tasks.tasks);
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
