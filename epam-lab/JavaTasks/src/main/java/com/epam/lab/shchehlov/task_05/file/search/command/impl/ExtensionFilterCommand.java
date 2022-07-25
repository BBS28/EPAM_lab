package com.epam.lab.shchehlov.task_05.file.search.command.impl;

import com.epam.lab.shchehlov.task_05.file.search.command.Command;
import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;
import com.epam.lab.shchehlov.task_05.file.search.filter.BasicFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.filter.ExtensionFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.util.UserInput;

import java.util.Scanner;

/**
 * Implementation of interface Command to add a search filter by file extension to the filter chain
 *
 * @author B.Shchehlov
 */
public class ExtensionFilterCommand implements Command {

    @Override
    public BasicFileFilter execute(BasicFileFilter fileFilter, Scanner scanner) {
        System.out.println(Constant.SEARCH_BY_EXTENSION);

        if (UserInput.inputChoice(scanner) == Constant.CHOICE_YES) {
            System.out.println(Constant.ENTER_EXTENSION);
            String extension = UserInput.inputString(scanner);
            
            BasicFileFilter extensionFilter = new ExtensionFileFilter(extension);
            fileFilter.setNextFilter(extensionFilter);

            return extensionFilter;
        }
        return fileFilter;
    }
}
