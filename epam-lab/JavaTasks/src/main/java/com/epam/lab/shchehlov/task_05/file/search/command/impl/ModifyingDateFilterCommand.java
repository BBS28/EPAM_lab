package com.epam.lab.shchehlov.task_05.file.search.command.impl;

import com.epam.lab.shchehlov.task_05.file.search.command.Command;
import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;
import com.epam.lab.shchehlov.task_05.file.search.filter.BasicFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.filter.ModificationDateFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.util.UserInput;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Implementation of interface Command to add a search filter by last date of modifying to the filter chain
 *
 * @author B.Shchehlov
 */
public class ModifyingDateFilterCommand implements Command {

    @Override
    public BasicFileFilter execute(BasicFileFilter fileFilter, Scanner scanner) {
        System.out.println(Constant.SEARCH_BY_DATE);

        if (UserInput.inputChoice(scanner) == Constant.CHOICE_YES) {
            do {
                try {
                    System.out.println(Constant.ENTER_DATE_MIN);
                    LocalDateTime minDate = UserInput.inputDate(scanner);
                    System.out.println(Constant.ENTER_DATE_MAX);

                    LocalDateTime maxDate = UserInput.inputDate(scanner);
                    BasicFileFilter dateFilter = new ModificationDateFileFilter(minDate, maxDate);
                    fileFilter.setNextFilter(dateFilter);

                    return dateFilter;
                } catch (IllegalArgumentException e) {
                    System.out.println(Constant.WARNING_DATE_INPUT);
                }
            } while (true);
        }
        return fileFilter;
    }
}
