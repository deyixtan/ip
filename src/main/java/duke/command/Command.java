package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
