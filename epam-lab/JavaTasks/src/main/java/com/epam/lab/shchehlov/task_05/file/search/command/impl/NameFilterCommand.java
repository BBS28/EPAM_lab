package com.epam.lab.shchehlov.task_05.file.search.command.impl;

import com.epam.lab.shchehlov.task_05.file.search.command.Command;
import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;
import com.epam.lab.shchehlov.task_05.file.search.filter.BasicFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.filter.NameFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.util.UserInput;

import java.util.Scanner;

/**
 * Implementation of interface Command to add a search filter by file name to the filter chain
 *
 * @author B.Shchehlov
 */
public class NameFilterCommand implements Command {

    @Override
    public BasicFileFilter execute(BasicFileFilter fileFilter, Scanner scanner) {
        System.out.println(Constant.SEARCH_BY_NAME);

        if (UserInput.inputChoice(scanner) == Constant.CHOICE_YES) {
            System.out.println(Constant.ENTER_NAME);
            String name = UserInput.inputString(scanner);

            BasicFileFilter nameFilter = new NameFileFilter(name);
            fileFilter.setNextFilter(nameFilter);

            return nameFilter;
        }
        return fileFilter;
    }
}
