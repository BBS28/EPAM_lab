package com.epam.lab.shchehlov.task_05.file.search.console;

import com.epam.lab.shchehlov.task_05.file.search.app.Searcher;
import com.epam.lab.shchehlov.task_05.file.search.util.UserInput;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.BYE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.CHOICE_YES;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.CONTINUE_SEARCHING;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.ENTER_FOLDER_PATH;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.FORMAT_TOTAL_FOUND;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.NO_FILES_FOUND;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.RESULTS;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.TITLE;

/**
 * Class that implements user interface that processes incoming commands
 *
 * @author B.Shchehlov
 */
public class ConsoleMenu {

    /**
     * Alternately offers a choice of filters and displays the search result.
     */
    public void run() {
        System.out.println(TITLE);
        Scanner scanner = new Scanner(System.in);
        boolean isContinue;
        do {
            Searcher searcher = new Searcher();
            System.out.println(ENTER_FOLDER_PATH);
            List<File> list = searcher.process(scanner);

            if (list.isEmpty()) {
                System.out.println(NO_FILES_FOUND);
            }

            System.out.println(RESULTS);
            for (File file : list) {
                System.out.println(file.getAbsolutePath());
            }

            System.out.printf(FORMAT_TOTAL_FOUND, list.size());
            System.out.println(CONTINUE_SEARCHING);
            int choice = UserInput.inputChoice(scanner);
            isContinue = (choice == CHOICE_YES);
        } while (isContinue);

        System.out.println(BYE);
    }
}
