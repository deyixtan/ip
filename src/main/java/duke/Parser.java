package duke;

import duke.command.*;

import java.time.LocalDate;

/**
 * Represents the parser used by the Duke chat bot.
 * It parses raw user inputs, and convert them into
 * a suitable Command format for execution.
 */
public class Parser {

    /**
     * Processes the raw user input and converts it
     * into a friendly Command data type for the
     * Duke chat bot to execute.
     *
     * @param input Raw user input.
     * @return User input in a Command format.
     * @throws DukeException If command arguments are misused.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        String taskType = inputArr[0].toLowerCase();
        String taskDesc = "";
        String taskArg = "";

        // Get description
        int i;
        for (i = 1; i < inputArr.length; i++) {
            if (taskType.equals("todo") || (!inputArr[i].equalsIgnoreCase("/by")
                    && !inputArr[i].equalsIgnoreCase("/at"))) {
                if (!taskDesc.equals("")) {
                    taskDesc += " ";
                }
                taskDesc += inputArr[i];
            } else {
                // Ensure no misuse of arguments
                if (!inputArr[i].equalsIgnoreCase("/by") && taskType.equals("deadline")) {
                    throw new DukeException("You're confusing me with parameters from other commands...");
                } else if (!inputArr[i].equalsIgnoreCase("/at") && taskType.equals("event")) {
                    throw new DukeException("You're confusing me with parameters from other commands...");
                }
                break;
            }
        }

        // Get argument
        for (i = i + 1; i < inputArr.length; i++) {
            if (!taskArg.equals("")) {
                taskArg += " ";
            }
            taskArg += inputArr[i];
        }

        switch(taskType.toUpperCase()) {
        case "TODO":
            return new AddTodoCommand(taskDesc);
        case "DEADLINE":
            return new AddDeadlineCommand(taskDesc, LocalDate.parse(taskArg));
        case "EVENT":
            return new AddEventCommand(taskDesc, LocalDate.parse(taskArg));
        case "DELETE":
            return new DeleteTaskCommand(Integer.parseInt(taskDesc));
        case "DONE":
            return new MarkTaskAsDoneCommand(Integer.parseInt(taskDesc));
        case "FIND":
            return new FindTasksCommand(taskDesc);
        case "LIST":
            return new ListTasksCommand();
        case "SAVE":
            return new SaveTasksCommand();
        case "USAGE":
            return new UsageCommand();
        case "BYE":
            return new ByeCommand();
        default:
            return null;
            //throws DukeException("!1!!!!!!!!!!");
        }
    }
}
