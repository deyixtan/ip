package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveTasksCommand extends Command {

    private static final Boolean toExit = false;

    public CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);

        String message = ui.getSaveTaskListSuccess();
        return new CommandResponse(message, SaveTasksCommand.toExit);
    }
}