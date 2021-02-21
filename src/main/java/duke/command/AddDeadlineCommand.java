package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

    private static final Boolean toExit = false;

    private Deadline deadline;

    public AddDeadlineCommand(String desc, LocalDateTime dateTime) {
        this.deadline = new Deadline(desc, dateTime);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.deadline);

        String message = ui.getAddTaskSuccess(this.deadline);
        return new CommandResponse(message, AddDeadlineCommand.toExit);
    }
}