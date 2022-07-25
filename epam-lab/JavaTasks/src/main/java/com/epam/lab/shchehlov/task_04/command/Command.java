package com.epam.lab.shchehlov.task_04.command;

/**
 * General interface for specific classes Command
 *
 * @author B.Shchehlov
 */
public interface Command {

    /**
     * The main command method that each specific command defines in its own way
     */
    void execute();

    /**
     * Returns name of the command for UI
     *
     * @return name of the command
     */
    String getDescription();
}
