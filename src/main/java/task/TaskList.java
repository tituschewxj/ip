package task;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

/**
 * This class encapsulates a {@link Task} list.
 */
public class TaskList {
    /**
     * Stores the tasks.
     */
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Checks if the index is a valid index in this list.
     *
     * @param index the index of in the task list
     * @return true if the index is valid, otherwise false
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Add a to-do to this list.
     *
     * @param name the name of the to-do to add
     */
    public void addTodo(String name) {
        tasks.add(new ToDo(name));
    }

    /**
     * Add a deadline to this list.
     *
     * @param name the name of the to-do to add
     */
    public void addDeadline(String name, String by) {
        tasks.add(new Deadline(name, by));
    }

    /**
     * Add an event to this list.
     *
     * @param name the name of the to-do to add
     */
    public void addEvent(String name, String from, String to) {
        tasks.add(new Event(name, from, to));
    }

    /**
     * Mark the task as done.
     *
     * @param index the index of the task (0-indexed)
     */
    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).mark();
        }
    }

    /**
     * Mark the task as not done.
     *
     * @param index the index of the task (0-indexed)
     */
    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).unmark();
        }
    }

    /**
     * @param index The 0-indexed index.
     *
     * @return the index of the task (0-indexed)
     */
    public String getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index).toString();
        }
        return "";
    }

    /**
     * Gets the most recently added task to this list.
     *
     * @return the newest task as a string
     */
    public String getNewestTask() {
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Gets the number of tasks in this task list.
     *
     * @return the size as an int
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a human-readable description of this task list.
     *
     * @return this task list as a human-readable string
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            message.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return message.toString();
    }
}
