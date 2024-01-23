package action;

import action.exception.ActionException;
import action.exception.MissingArgumentValueException;
import task.TaskList;

/**
 * AddTodoCommand encapsulates the behaviour of adding a to-do.
 *
 * @author Titus Chew
 */
public class AddTodoAction extends Action {
    /**
     * Constructor for this add to-do action.
     *
     * @param arguments the arguments supplied with the command
     * @throws ActionException If the action fails has unrecognizable or missing arguments.
     */
    public AddTodoAction(Argument[] arguments) throws ActionException {
        super(Command.ADD_TODO, arguments);
    }

    /**
     * Add a to-do to the user's list.
     *
     * @param taskList the taskList to modify
     * @throws ActionException If the action fails certain validation checks due to invalid input.
     */
    @Override
    public void execute(TaskList taskList) throws ActionException {
        String name = findDefaultArgument();

        // Validate arguments
        if (name == null) {
            throw new MissingArgumentValueException(getCommand(), "name");
        }

        // Perform behaviour
        taskList.addTodo(name);
        handleAddSuccess(taskList);
    }
}
