package com.epam.lab.shchehlov.task_05.file.search.filter;

import java.io.File;

/**
 * The base class of the chain.
 *
 * @author B.Shchehlov
 */
public abstract class BasicFileFilter {
    private BasicFileFilter nextFileFilter;

    public BasicFileFilter() {
    }

    /**
     * Helps to build a chain of check objects.
     *
     * @return next file filter
     */
    public BasicFileFilter setNextFilter(BasicFileFilter nextFileFilter) {
        this.nextFileFilter = nextFileFilter;
        return nextFileFilter;
    }


    public boolean search(File file) {
        boolean result = isAppropriate(file);

        if (result && nextFileFilter != null) {
            return nextFileFilter.search(file);
        }

        return result;
    }

    /**
     * Subclasses implement concrete checks in this method.
     *
     * @return true if the file matches the given parameters
     */
    public abstract boolean isAppropriate(File file);
}
