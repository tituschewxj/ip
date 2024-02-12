package chatbot.ui;

import chatbot.ChatBot;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This encapsulates the JavaFX components used for the main stage in the main window of the chatbot.
 *
 * @author Titus Chew
 */
public class MainWindow {
    /** Stores the window width of the JavaFX stage. */
    private static final int WINDOW_WIDTH = 400;

    /** Stores the window height of the JavaFX stage */
    private static final int WINDOW_HEIGHT = 600;

    /** Scroll pane for the JavaFX stage. */
    private ScrollPane scrollPane;

    /** Dialog container for the JavaFX stage. */
    private VBox dialogContainer;

    /** User input text field for the JavaFX stage. */
    private TextField userInput;

    /** Main layout for the JavaFX stage. */
    private AnchorPane mainLayout;

    /** Send button for the JavaFX stage. */
    private Button sendButton;

    /**
     * Sets up the required components used in the stage.
     *
     * @param stage The stage that contains the components.
     */
    public void setUpStage(Stage stage) {
        // the container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Formats the window to look as expected.
     *
     * @param stage The stage that contains the components.
     */
    public void formatWindow(Stage stage, String chatBotName) {
        stage.setTitle(chatBotName);
        stage.setResizable(false);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);

        mainLayout.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Handles updates from the user.
     *
     * @param chatBot The chatbot that is currently used to accept user input.
     */
    public void handleUserUpdate(ChatBot chatBot) {
        // handle user input
        sendButton.setOnMouseClicked(event -> handleUserInput(chatBot));
        userInput.setOnAction(event -> handleUserInput(chatBot));

        // scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     * <p>
     * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html}.
     *
     * @param chatBot The chatbot that is currently used to accept user input.
     */
    private void handleUserInput(ChatBot chatBot) {
        String userText = userInput.getText();
        String chatBotText = chatBot.getResponseMessage(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText),
                DialogBox.getChatBotDialog(chatBotText)
        );

        userInput.clear();
    }

    /**
     * Gets the messages from the chatbot,
     * that are stored in the {@link PrintFormatter},
     * and removes them from the {@link PrintFormatter}.
     */
    public void getChatBotMessages() {
        String chatBotText = PrintFormatter.formatMessages();
        dialogContainer.getChildren().addAll(
                DialogBox.getChatBotDialog(chatBotText)
        );
    }
}
