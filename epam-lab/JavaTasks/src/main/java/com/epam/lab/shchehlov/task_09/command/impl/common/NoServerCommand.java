package com.epam.lab.shchehlov.task_09.command.impl.common;

import com.epam.lab.shchehlov.task_09.command.ServerCommand;

import static com.epam.lab.shchehlov.task_09.constant.Constant.NO_COMMAND;

/**
 * Handles execution of an incorrectly specified command key.
 *
 * @author B.Shchehlov.
 */
public class NoServerCommand implements ServerCommand {

    @Override
    public String execute(String request) {
        return NO_COMMAND;
    }
}
