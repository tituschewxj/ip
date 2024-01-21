import java.util.Scanner;

/**
 * ChatBot encapsulates the behaviour of a Chatbot.
 *
 * @author Titus Chew
 */
public class ChatBot {
    private final String chatBotName, INDENT = "    ";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Stores the user's tasks
     */
    TaskList userList = new TaskList();

    /**
     * Class constructor.
     * @param chatBotName the name of the chatbot
     */
    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * Greets the user when entering the application.
     */
    private void greet() {
        printMessage(String.format(
                "Hello! I'm %s!\n" +
                "What can I do for you?",
                chatBotName));
    }

    /**
     * Greets the user when exiting the application.
     */
    private void exit() {
        printMessage("Bye! Hope to see you again soon!");
    }

    /**
     * Inserts a horizontal line.
     */
    private void insertLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Prints an indented message to the console.
     * @param message the message to print in the console
     */
    private void printMessage(String message) {
        insertLine();
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(INDENT + line);
        }
        insertLine();
    }

    /**
     * Reads and parses user input for commands.
     */
    public void run() {
        greet();

        String[] parsedInput = parseInput(scanner.nextLine());
        Command userCommand = parseCommand(parsedInput[0]);
        while (userCommand != Command.BYE) {
            executeCommand(userCommand, parsedInput[1]);

            parsedInput = parseInput(scanner.nextLine());
            userCommand = parseCommand(parsedInput[0]);
        }

        exit();
    }

    /**
     * Execute the command with the supplied arguments.
     * @param command the command
     * @param argument the argument
     */
    private void executeCommand(Command command, String argument) {
        switch (command) {
            case ADD:
                addTask(argument);
                break;
            case LIST:
                listTasks();
                break;
            case MARK:
                markTask(Integer.parseInt(argument) - 1);
                break;
            case UNMARK:
                unmarkTask(Integer.parseInt(argument) - 1);
                break;
        }
    }

    /**
     * Add a task to the user's list
     */
    private void addTask(String name) {
        userList.addTask(name);
        printMessage("added: " + name);
    }

    /**
     * Prints the user's list.
     */
    private void listTasks() {
        printMessage("Here are the tasks in your list:\n" + userList.toString());
    }

    /**
     * Marks and prints the task.
     * @param index the 0-indexed index of the task in the user's list.
     */
    private void markTask(int index) {
        userList.markTask(index);
        printMessage("Nice! I've marked this task as done:\n  " + userList.getTask(index));
    }

    /**
     * Unmarks and prints the task.
     * @param index the 0-indexed index of the task in the user's list.
     */
    private void unmarkTask(int index) {
        userList.unmarkTask(index);
        printMessage("OK, I've marked this task as not done yet:\n  " + userList.getTask(index));
    }
}
