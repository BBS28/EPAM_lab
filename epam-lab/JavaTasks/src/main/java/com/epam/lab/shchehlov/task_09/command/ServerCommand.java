package com.epam.lab.shchehlov.task_09.command;

/**
 * General interface for specific classes ServerCommand.
 *
 * @author B.Shchehlov
 */
public interface ServerCommand {

    String execute(String request);
}
