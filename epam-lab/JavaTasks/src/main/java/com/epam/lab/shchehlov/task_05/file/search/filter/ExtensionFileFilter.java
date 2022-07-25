package com.epam.lab.shchehlov.task_05.file.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;

import java.io.File;

/**
 * Specific chain element filters by file extension.
 *
 * @author B.Shchehlov
 */
public class ExtensionFileFilter extends BasicFileFilter {
    private final String extension;

    public ExtensionFileFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean isAppropriate(File file) {
        return getFileExtension(file).equals(extension);
    }

    /**
     * Returns the extension of the file being checked
     *
     * @return String extension of the file being checked
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        int pointIndex = name.lastIndexOf(Constant.POINT);
        if (pointIndex > 0) {
            return name.substring(pointIndex + 1);
        }
        return "";
    }
}
