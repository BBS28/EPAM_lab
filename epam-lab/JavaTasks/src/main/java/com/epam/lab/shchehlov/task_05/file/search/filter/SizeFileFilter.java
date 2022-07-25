package com.epam.lab.shchehlov.task_05.file.search.filter;

import java.io.File;

/**
 * Specific chain element filters by file size.
 *
 * @author B.Shchehlov
 */
public class SizeFileFilter extends BasicFileFilter {
    private final long minSize;
    private final long maxSize;

    public SizeFileFilter(long minSize, long maxSize) {
        if (!isValidParameters(minSize, maxSize)) {
            throw new IllegalArgumentException();
        }
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public boolean isAppropriate(File file) {
        long fileSize = file.length();
        return fileSize >= minSize && fileSize <= maxSize;
    }

    /**
     * Returns true if passed parameters are valid.
     *
     * @return true if passed parameters are valid.
     */
    private boolean isValidParameters(long minSize, long maxSize) {
        return minSize >= 0 && maxSize >= 0 && minSize <= maxSize;
    }
}
