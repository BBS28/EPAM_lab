package com.epam.lab.shchehlov.task_05.file.search.app;

import com.epam.lab.shchehlov.task_05.file.search.command.Command;
import com.epam.lab.shchehlov.task_05.file.search.command.CommandContainer;
import com.epam.lab.shchehlov.task_05.file.search.filter.BasicFileFilter;
import com.epam.lab.shchehlov.task_05.file.search.filter.NoFilter;
import com.epam.lab.shchehlov.task_05.file.search.util.UserInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console searcher for files application class
 *
 * @author B.Shchehlov
 */
public class Searcher {
    private List<File> fileList;
    private BasicFileFilter fileFilter;

    /**
     * Initializes parameters for searching files and makes up a filter chain
     *
     * @return list of files
     */
    public List<File> process(Scanner scanner) {
        CommandContainer commandContainer = new CommandContainer();
        fileFilter = new NoFilter();
        fileList = new ArrayList<>();
        File directory = UserInput.inputDirectory(scanner);
        BasicFileFilter nextFilter = fileFilter;
        for (Command command : commandContainer) {
            nextFilter = command.execute(nextFilter, scanner);
        }
        return getFiles(directory);
    }

    /**
     * gets all files in a given directory that match filter conditions
     *
     * @return list of files
     */
    private List<File> getFiles(File file) {

        File[] folderFiles = file.listFiles();
        if (folderFiles == null) {
            return fileList;
        }
        for (File folderFile : folderFiles) {
            if (folderFile.isDirectory()) {
                getFiles(folderFile);
            } else if (fileFilter.search(folderFile)) {
                fileList.add(folderFile);
            }
        }
        return fileList;
    }
}
