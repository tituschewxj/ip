package chatbot.action.exception;

import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * UnexpectedArgumentValueException represents exceptions due to the presence of a value
 * in an argument that does not require a value.
 *
 * @author Titus Chew
 */
public final class UnexpectedArgumentValueException extends ActionException {
    private final Argument argument;
    private final Command command;

    /**
     * Constructor for this ActionException for unrecognized argument names.
     *
     * @param command the command
     * @param argument the argument
     */
    public UnexpectedArgumentValueException(Command command, Argument argument) {
        this.argument = argument;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I didn't expect <" + argument.getValue() + "> :-(\n"
                + "    Usage: `" + command.getUsageHint() + "`";
    }
}
