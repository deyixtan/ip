import duke.Duke;
import duke.command.CommandResponse;
import duke.command.StatsCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        displayGreeting();
    }

    public void displayGreeting() {
        String greeting = duke.welcomeUser();
        dialogContainer.getChildren().addAll(
                DukeTextDialogBox.getDialogBox(greeting, dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResponse response = duke.getResponse(input);

        dialogContainer.getChildren().add(UserTextDialogBox.getDialogBox(input, userImage));
        if (response.getCommandClass() == StatsCommand.class) {
            dialogContainer.getChildren().add(DukePieChartDialogBox.getDialogBox(this.duke.getTaskList(), userImage));
        } else {
            dialogContainer.getChildren().add(DukeTextDialogBox.getDialogBox(response.toString(), userImage));
        }
        userInput.clear();

        if (response.canExit()) {
            Platform.exit();
        }
    }
}