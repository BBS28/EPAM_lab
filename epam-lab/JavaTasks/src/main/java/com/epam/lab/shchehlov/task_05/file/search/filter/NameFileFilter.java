package com.epam.lab.shchehlov.task_05.file.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;

import java.io.File;

/**
 * Specific chain element filters by file name.
 *
 * @author B.Shchehlov
 */
public class NameFileFilter extends BasicFileFilter {
    private final String fileName;

    public NameFileFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean isAppropriate(File file) {
        return getFileBaseName(file).contains(fileName);
    }

    /**
     * Returns name of file without extension.
     *
     * @return String name of file without extension.
     */
    private String getFileBaseName(File file) {
        String name = file.getName();
        if (name.indexOf(Constant.POINT) > 0) {
            return name.substring(0, name.lastIndexOf(Constant.POINT));
        } else {
            return name;
        }
    }
}