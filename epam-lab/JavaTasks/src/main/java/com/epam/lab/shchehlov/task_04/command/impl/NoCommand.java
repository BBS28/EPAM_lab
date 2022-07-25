package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;

/**
 * Handles execution of an incorrectly specified command key
 *
 * @author B.Shchehlov
 */
public class NoCommand implements Command {
    private static final String COMMAND_NAME = "No command entered";
    private static final String NO_COMMAND_ENTERED = "No command number was entered. Enter correct number!";

    @Override
    public void execute() {
        System.out.println(NO_COMMAND_ENTERED);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
