package com.epam.lab.shchehlov.task_05.file.search.command.impl;

import com.epam.lab.shchehlov.task_05.file.search.command.Command;
import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;
import com.epam.lab.shchehlov.task_05.file.search.filter.BasicFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.filter.SizeFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.util.UserInput;

import java.util.Scanner;

/**
 * Implementation of interface Command to add a search filter by file size to the filter chain
 *
 * @author B.Shchehlov
 */
public class SizeFilterCommand implements Command {

    @Override
    public BasicFileFilter execute(BasicFileFilter fileFilter, Scanner scanner) {
        System.out.println(Constant.SEARCH_BY_SIZE);

        if (UserInput.inputChoice(scanner) == Constant.CHOICE_YES) {
            do {
                try {
                    System.out.println(Constant.ENTER_SIZE_MIN);
                    long minSize = UserInput.inputLong(scanner);
                    System.out.println(Constant.ENTER_SIZE_MAX);
                    long maxSize = UserInput.inputLong(scanner);

                    BasicFileFilter sizeFilter = new SizeFileFilter(minSize, maxSize);
                    fileFilter.setNextFilter(sizeFilter);

                    return sizeFilter;
                } catch (IllegalArgumentException e) {
                    System.out.println(Constant.WARNING_MAXIMUM_SIZE);
                }
            } while (true);
        }
        return fileFilter;
    }
}
