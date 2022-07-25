package com.epam.lab.shchehlov.task_05.file.search.filter;

import java.io.File;

/**
 * Specific chain element for initial initialization of the filter chain.
 *
 * @author B.Shchehlov
 */
public class NoFilter extends BasicFileFilter {

    @Override
    public boolean isAppropriate(File file) {
        return true;
    }
}
