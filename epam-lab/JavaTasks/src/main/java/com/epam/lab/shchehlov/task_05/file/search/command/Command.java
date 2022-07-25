package com.epam.lab.shchehlov.task_05.file.search.command;

import com.epam.lab.shchehlov.task_05.file.search.filter.BasicFileFilter;

import java.util.Scanner;

/**
 * General interface for specific classes Command
 *
 * @author B.Shchehlov
 */
public interface Command {

    /**
     * The main command method that each specific command defines in its own way
     */
    BasicFileFilter execute(BasicFileFilter fileFilter, Scanner scanner);
}
